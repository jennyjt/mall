package com.zsbatech.baasKettleManager.service.impl;


import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.JobLog;
import com.zsbatech.baasKettleManager.model.JobMetaExample;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.JobLogService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 15:06
 */

@Service
public class DBMigrationServiceImpl implements DBMigrationService {

    @Autowired
    private DbManagementMapper dbManagementMapper;
    @Autowired
    private JobMetaMapper jobMetaMapper;
    @Autowired
    SaveTransMetaService saveTransMetaService;
    @Autowired
    SaveJobMetaService saveJobMetaService;
    @Autowired
    JobLogService jobLogService;

    private String DbMigTransUrl = ConfigUtil.getPropertyValue("dbmig.transMetaUrl");
    private String DbMigJobUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");
    private String ktrpath;


    public ResponseData<String> createMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {

            DbManagement srcdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
            DbManagement dstdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());


            KettleEnvironment.init();
            org.pentaho.di.trans.TransMeta transMeta = new TransMeta();

            //设置转换名称
            transMeta.setName(dataMig.getTransName());

        //添加转换数据库源连接
            DatabaseMeta databaseMeta = new DatabaseMeta();

            databaseMeta.setDatabaseType(srcdbManagement.getDbType());
            databaseMeta.setDBName(srcdbManagement.getDbName());
            databaseMeta.setHostname(srcdbManagement.getDbHost());
            databaseMeta.setDBPort(srcdbManagement.getDbPort());
            databaseMeta.setUsername(srcdbManagement.getDbUser());
            databaseMeta.setPassword(srcdbManagement.getDbPassword());
            databaseMeta.setName(srcdbManagement.getLinkName());
//只读
            databaseMeta.setReadOnly(true);

            transMeta.addDatabase(databaseMeta);

            //添加转换数据库目标连接
            DatabaseMeta dstdatabaseMeta = new DatabaseMeta();
            dstdatabaseMeta.setDatabaseType(dstdbManagement.getDbType());
            dstdatabaseMeta.setDBName(dstdbManagement.getDbName());
            dstdatabaseMeta.setHostname(dstdbManagement.getDbHost());
            dstdatabaseMeta.setDBPort(dstdbManagement.getDbPort());
            dstdatabaseMeta.setUsername(dstdbManagement.getDbUser());
            dstdatabaseMeta.setPassword(dstdbManagement.getDbPassword());
            dstdatabaseMeta.setName(dstdbManagement.getLinkName());
//只读
            dstdatabaseMeta.setReadOnly(false);
            transMeta.addDatabase(dstdatabaseMeta);

            PluginRegistry registry = PluginRegistry.getInstance();
            //第一个表输入步骤(TableInputMeta)
            TableInputMeta tableInputMeta = new TableInputMeta();
            String tableInputPluginId = registry.getPluginId(StepPluginType.class,tableInputMeta);

            //给表输入添加一个DatabaseMeta连接数据库
            DatabaseMeta database_in = transMeta.findDatabase(srcdbManagement.getLinkName());
            tableInputMeta.setDatabaseMeta(database_in);
            String select_sql = "SELECT * FROM " + dataMig.getSrcTable();//表名必须传
            tableInputMeta.setSQL(select_sql);

            //添加TableInputMeta到转换中

            StepMeta tableInputMetaStep = new StepMeta(tableInputPluginId,"table_input",tableInputMeta);
            transMeta.addStep(tableInputMetaStep);

            //第二个步骤插入与更新

            TableOutputMeta tableOutputMeta = new TableOutputMeta();

            String tableOutputPluginId = registry.getPluginId(StepPluginType.class,tableOutputMeta);
            //添加数据库连接
            DatabaseMeta database_out = transMeta.findDatabase(dstdbManagement.getLinkName());
            tableOutputMeta.setDatabaseMeta(database_out);
            //设置操作的表
            tableOutputMeta.setTableName(dataMig.getDstTable());//

            tableOutputMeta.setParentStepMeta(tableInputMetaStep);


            //添加输出步骤到转换中

            StepMeta tableOutputMetaStep = new StepMeta(tableOutputPluginId,"table_output",tableOutputMeta);
            transMeta.addStep(tableOutputMetaStep);

            //hop
            transMeta.addTransHop(new TransHopMeta(tableInputMetaStep,tableOutputMetaStep));

            if (saveTransMetaService.save(transMeta,DbMigTransUrl + dataMig.getTransName()+ ".ktr",true)) {
               saveTransMetaService.saveTransData(DbMigTransUrl + dataMig.getTransName()+ ".ktr",dataMig.getSrcDbconnId(),dataMig.getDstDbconnId());

            TransMeta transMeta1 = new TransMeta(DbMigTransUrl + dataMig.getTransName()+ ".ktr");
            Trans trans = new Trans(transMeta1);
            trans.prepareExecution(null);
            trans.startThreads();
            trans.waitUntilFinished();
            if (trans.getErrors()!= 0 ){
                responseData.setError(500,"同步失败，请尝试增量同步","fail");
            }else {
                responseData.setOK(200,"success",DbMigTransUrl + dataMig.getTransName()+ ".ktr");
            }

            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

        return responseData;
    }

    public ResponseData<String> cycleMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        JobLog jobLog = new JobLog();
        try {

            KettleEnvironment.init();
            ktrpath = DbMigTransUrl+dataMig.getKtrString() ;

            JobMeta jobMeta = new JobMeta();
            jobMeta.setName(dataMig.getJobName());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName("start");
            jobEntrySpecial.setRepeat(true);


            switch (dataMig.getSchedulerType()){
                case 2:
                    jobEntrySpecial.setSchedulerType(2);
                    jobEntrySpecial.setHour(12);
                case 3:
                    jobEntrySpecial.setSchedulerType(3);
                    jobEntrySpecial.setWeekDay(1);
                case 4:
                    jobEntrySpecial.setSchedulerType(4);
                    jobEntrySpecial.setDayOfMonth(1);
                    default:
                        jobEntrySpecial.setSchedulerType(1);
                        jobEntrySpecial.setIntervalSeconds(10);
                        jobEntrySpecial.setIntervalMinutes(0);
            }


            jobEntrySpecial.setStart(true);
            JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
            specialCopy.setLocation(30,30);
            specialCopy.setDrawn(true);
            jobMeta.addJobEntry(specialCopy);
            JobEntryTrans jobEntryTrans = new JobEntryTrans(dataMig.getJobName());
            jobEntryTrans.setFileName(ktrpath);
            JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
            transJob.setLocation(200,20);
            transJob.setDrawn(true);
            jobMeta.addJobEntry(transJob);

            JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
            jobHopMeta.setUnconditional(true);
            jobMeta.addJobHop(jobHopMeta);

            saveJobMetaService.save(jobMeta,DbMigJobUrl+dataMig.getJobName()+".kjb",true);
            saveJobMetaService.saveTransJobData(DbMigJobUrl+dataMig.getJobName()+".kjb");
            JobMeta jobMeta1 = new JobMeta(DbMigJobUrl+dataMig.getJobName()+".kjb",null);
            Job job = new Job(null,jobMeta1);
            jobLog.setStartTime(new Date());
            job.start();
            Thread.currentThread().setName("aaa");
//            job.waitUntilFinished();
            jobLog.setEndTime(new Date());
            jobLog.setJobName(dataMig.getJobName());
            jobLog.setStatus((byte)2);

            Thread.sleep(11000);
            if (job.getErrors() > 0 ){
                jobLog.setStatus((byte)3);
                jobLog.setErrors(job.getErrors());
                jobLog.setLogField("执行失败");
                responseData.setError(500,"任务失败，请确认输入参数是否正确","失败任务："+DbMigJobUrl+dataMig.getJobName()+".kjb");
            }else {
                jobLog.setStatus((byte)3);
                jobLog.setLogField("success");
                responseData.setOK(200,"success",DbMigJobUrl+dataMig.getJobName()+".kjb");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jobLog.setLogField("执行失败");
            jobLog.setLogField(e.toString());
            responseData.setError(500,"任务异常，请确认输入参数是否正确","失败任务："+DbMigJobUrl+dataMig.getJobName()+".kjb");
        }

        jobLogService.addJobLog(jobLog);
        return responseData;
    }

    public ResponseData<String> insertupdateMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {

            DbManagement srcdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
            DbManagement dstdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());


            KettleEnvironment.init();

            DatabaseMeta sourceDbMeta = new DatabaseMeta(TableUtil.getXmlByDbManagement(srcdbManagement));

            Database db = new Database(sourceDbMeta);

            db.connect();
            String selectsql = "SELECT * FROM "+dataMig.getSrcTable();
            RowMetaInterface rowMetaInterface = db.getQueryFields(selectsql,false);
            String[] fields = rowMetaInterface.getFieldNames();

            db.disconnect();

            org.pentaho.di.trans.TransMeta transMeta = new TransMeta();

            //设置转换名称
            transMeta.setName(dataMig.getTransName());

            //添加转换数据库源连接
            DatabaseMeta databaseMeta = new DatabaseMeta();

            databaseMeta.setDatabaseType(srcdbManagement.getDbType());
            databaseMeta.setDBName(srcdbManagement.getDbName());
            databaseMeta.setHostname(srcdbManagement.getDbHost());
            databaseMeta.setDBPort(srcdbManagement.getDbPort());
            databaseMeta.setUsername(srcdbManagement.getDbUser());
            databaseMeta.setPassword(srcdbManagement.getDbPassword());
            databaseMeta.setName(srcdbManagement.getLinkName());
//只读
            databaseMeta.setReadOnly(true);

            transMeta.addDatabase(databaseMeta);

            //添加转换数据库目标连接
            DatabaseMeta dstdatabaseMeta = new DatabaseMeta();
            dstdatabaseMeta.setDatabaseType(dstdbManagement.getDbType());
            dstdatabaseMeta.setDBName(dstdbManagement.getDbName());
            dstdatabaseMeta.setHostname(dstdbManagement.getDbHost());
            dstdatabaseMeta.setDBPort(dstdbManagement.getDbPort());
            dstdatabaseMeta.setUsername(dstdbManagement.getDbUser());
            dstdatabaseMeta.setPassword(dstdbManagement.getDbPassword());
            dstdatabaseMeta.setName(dstdbManagement.getLinkName());
//只读
            dstdatabaseMeta.setReadOnly(false);
            transMeta.addDatabase(dstdatabaseMeta);

            PluginRegistry registry = PluginRegistry.getInstance();
            //第一个表输入步骤(TableInputMeta)
            TableInputMeta tableInputMeta = new TableInputMeta();
            String tableInputPluginId = registry.getPluginId(StepPluginType.class,tableInputMeta);

            //给表输入添加一个DatabaseMeta连接数据库
            DatabaseMeta database_in = transMeta.findDatabase(srcdbManagement.getLinkName());
            tableInputMeta.setDatabaseMeta(database_in);
            String  select_sql = "SELECT * FROM " + dataMig.getSrcTable()+ " where "+ dataMig.getTimeStamp() + " > " +"\""+dataMig.getUpdatetime()+"\"";

            tableInputMeta.setSQL(select_sql);

            //添加TableInputMeta到转换中

            StepMeta tableInputMetaStep = new StepMeta(tableInputPluginId,"table_input",tableInputMeta);
            tableInputMetaStep.setLocation(400,50);
            transMeta.addStep(tableInputMetaStep);

            //第二个步骤插入与更新

            InsertUpdateMeta tableOutputMeta = new InsertUpdateMeta();

            String tableOutputPluginId = registry.getPluginId(StepPluginType.class,tableOutputMeta);
            //添加数据库连接
            DatabaseMeta database_out = transMeta.findDatabase(dstdbManagement.getLinkName());
            tableOutputMeta.setDatabaseMeta(database_out);
            //设置操作的表
            tableOutputMeta.setTableName(dataMig.getDstTable());//
            //设置用来查询的关键字
            tableOutputMeta.setKeyLookup(new String[]{"ID"});
            tableOutputMeta.setKeyStream(new String[]{"ID"});
            tableOutputMeta.setKeyStream2(new String[]{""});//一定要加上
            tableOutputMeta.setKeyCondition(new String[]{"="});


            String[] updatelookup = new String[fields.length];

            List<String> list = dataMig.getUpdateLookup();
            if (list == null || list.size() == 0){
                 updatelookup =  fields;
            }else{
                 updatelookup = list.toArray(new String[list.size()]);
            }

            String[] updateStream = updatelookup;
            Boolean[] updateOrNot =new Boolean[updatelookup.length];
            updateOrNot[0] = false;
            for (int i=1;i<updatelookup.length;i++){
                updateOrNot[i]= true;
            }

            tableOutputMeta.setUpdateLookup(updatelookup);
            tableOutputMeta.setUpdateStream(updateStream);
            tableOutputMeta.setUpdate(updateOrNot);
            String[] lookup = tableOutputMeta.getUpdateLookup();

            tableOutputMeta.setParentStepMeta(tableInputMetaStep);


            //添加输出步骤到转换中

            StepMeta tableOutputMetaStep = new StepMeta(tableOutputPluginId,"table_output",tableOutputMeta);
            tableOutputMetaStep.setLocation(100,50);
            transMeta.addStep(tableOutputMetaStep);

            //hop
            transMeta.addTransHop(new TransHopMeta(tableInputMetaStep,tableOutputMetaStep));

            if (saveTransMetaService.save(transMeta,DbMigTransUrl + dataMig.getTransName()+ ".ktr",true)) {
                saveTransMetaService.saveTransData(DbMigTransUrl + dataMig.getTransName()+ ".ktr",dataMig.getSrcDbconnId(),dataMig.getDstDbconnId());
                org.pentaho.di.trans.TransMeta transMeta1 = new TransMeta(DbMigTransUrl + dataMig.getTransName()+ ".ktr");
                Trans trans = new Trans(transMeta1);
                trans.prepareExecution(null);
                trans.startThreads();
                trans.waitUntilFinished();
//                trans.getCounters();
                if (trans.getErrors()!= 0 ){
                    responseData.setError(500,"同步失败，请确认输入是否正确","fail");
                }else {
                    responseData.setOK(200,"success",DbMigTransUrl + dataMig.getTransName()+ ".ktr");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

        return responseData;
    }

    public Pagination<com.zsbatech.baasKettleManager.model.JobMeta> getJobList(Integer currPage, Integer pageSize) {
        PageMethod.startPage(currPage, pageSize);
        JobMetaExample jobMetaExample = new JobMetaExample();
        JobMetaExample.Criteria criteria = jobMetaExample.createCriteria();
        criteria.andIdIsNotNull();
        List<com.zsbatech.baasKettleManager.model.JobMeta> jobMetaList = jobMetaMapper.selectByExample(jobMetaExample);
        Pagination<com.zsbatech.baasKettleManager.model.JobMeta> jobMetaPagination = new Pagination<com.zsbatech.baasKettleManager.model.JobMeta>(jobMetaList);
        return jobMetaPagination;
    }

    public com.zsbatech.baasKettleManager.model.JobMeta getJobDetail (Integer jobId) {

        com.zsbatech.baasKettleManager.model.JobMeta jobMeta = jobMetaMapper.selectByPrimaryKey(jobId);
        return jobMeta;
    }

}
