package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class JobTransServiceImpl implements JobTransService {

    @Autowired
    private DbManagementMapper dbManagementMapper;
    @Autowired
    SaveTransMetaService saveTransMetaService;
    @Autowired
    SaveJobMetaService saveJobMetaService;
    @Autowired
    JobTransService jobTransService;
    @Autowired
    DBMigrationService dbMigrationService;
    @Autowired
    private TransMetaMapper transMetaMapper;
    @Autowired
    private TableinputStepMapper tableinputStepMapper;
    @Autowired
    private com.zsbatech.baasKettleManager.dao.InsertUpdateStepMapper insertUpdateStepMapper;
    @Autowired
    private JobMetaMapper jobMetaMapper;

    private String DbMigTransUrl = ConfigUtil.getPropertyValue("dbmig.transMetaUrl");
    private String DbMigJobUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");
    private String ktrpath;


    public ResponseData<String> modifyJob(DataMig dataMig) {


        ResponseData<String> responseData = new ResponseData<>();
        JobMetaExample jobMetaExample = new JobMetaExample();
        JobMetaExample.Criteria jobCriteria = jobMetaExample.createCriteria();
        jobCriteria.andJobNameEqualTo(dataMig.getJobName());
        List<com.zsbatech.baasKettleManager.model.JobMeta> jobMetaList = jobMetaMapper.selectByExample(jobMetaExample);
        if (jobMetaList.size()<1){
            responseData.setError("任务名称不允许修改！");
            return responseData;
        }
        try {

            ktrpath = modifyKtr(dataMig);
            KettleEnvironment.init();


            JobMeta jobMeta = new JobMeta();
            jobMeta.setName(dataMig.getJobName());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName("start");
            jobEntrySpecial.setRepeat(true);


            switch (dataMig.getSchedulerType()) {
                case 1:
                    jobEntrySpecial.setSchedulerType(1);
                    jobEntrySpecial.setIntervalSeconds(10);
                    jobEntrySpecial.setIntervalMinutes(0);
                    break;


                case 2:
                    jobEntrySpecial.setSchedulerType(2);
                    jobEntrySpecial.setHour(12);
                    break;
                case 3:
                    jobEntrySpecial.setSchedulerType(3);
                    jobEntrySpecial.setWeekDay(1);
                    break;
                case 4:
                    jobEntrySpecial.setSchedulerType(4);
                    jobEntrySpecial.setDayOfMonth(1);
                    break;
                default:
                    jobEntrySpecial.setSchedulerType(0);
                    jobEntrySpecial.setRepeat(false);
                    break;

            }

            jobEntrySpecial.setStart(true);
            JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
            specialCopy.setLocation(30, 30);
            specialCopy.setDrawn(true);
            jobMeta.addJobEntry(specialCopy);
            JobEntryTrans jobEntryTrans = new JobEntryTrans(dataMig.getJobName());
            jobEntryTrans.setFileName(ktrpath);
            JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
            transJob.setLocation(200, 20);
            transJob.setDrawn(true);
            jobMeta.addJobEntry(transJob);

            JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
            jobHopMeta.setUnconditional(true);
            jobMeta.addJobHop(jobHopMeta);

            boolean savektr = saveJobMetaService.save(jobMeta, DbMigJobUrl + dataMig.getJobName() + ".kjb", true);

            jobMetaList.get(0).setUpdatetime(new Date());
            jobMetaList.get(0).setJobType(dataMig.getSchedulerType().toString());
            jobMetaMapper.updateByExample(jobMetaList.get(0),jobMetaExample);

            if (savektr) {
                responseData.set(200, "success", DbMigJobUrl + dataMig.getJobName() + ".kjb");
            }


        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500, "创建任务异常，请确认输入参数是否正确", DbMigJobUrl + dataMig.getJobName() + ".kjb");
        }

        return responseData;
    }


    public String modifyKtr(DataMig dataMig) {

        TransMetaExample transMetaExample = new TransMetaExample();
        TransMetaExample.Criteria criteria = transMetaExample.createCriteria();
        criteria.andTransNameEqualTo(dataMig.getJobName());
        List<com.zsbatech.baasKettleManager.model.TransMeta> transMetaList = transMetaMapper.selectByExample(transMetaExample);


        if (transMetaList.size()<1){
            return "";
        }


        try {

            DbManagement srcdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
            DbManagement dstdbManagement = dbManagementMapper.selectByPrimaryKey(13);


            KettleEnvironment.init();

            DatabaseMeta sourceDbMeta = new DatabaseMeta(TableUtil.getXmlByDbManagement(srcdbManagement));

            Database db = new Database(sourceDbMeta);

            db.connect();
            String selectsql = "SELECT * FROM " + dataMig.getSrcTable();
            RowMetaInterface rowMetaInterface = db.getQueryFields(selectsql, false);
            String[] fields = rowMetaInterface.getFieldNames();

            db.disconnect();

            TransMeta transMeta = new TransMeta();

            //设置转换名称
            transMeta.setName(dataMig.getJobName());

            //添加转换数据库源连接
            DatabaseMeta databaseMeta = new DatabaseMeta();

            databaseMeta.setDatabaseType(srcdbManagement.getDbType());
            databaseMeta.setDBName(srcdbManagement.getDbName());
            databaseMeta.setHostname(srcdbManagement.getDbHost());
            databaseMeta.setDBPort(srcdbManagement.getDbPort());
            databaseMeta.setUsername(srcdbManagement.getDbUser());
            databaseMeta.setPassword(srcdbManagement.getDbPassword());
            databaseMeta.setName(srcdbManagement.getLinkName());
            databaseMeta.addExtraOption(srcdbManagement.getDbType(), "characterEncoding", "utf-8");
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
            dstdatabaseMeta.addExtraOption(dstdbManagement.getDbType(), "characterEncoding", "utf-8");
//只读
            dstdatabaseMeta.setReadOnly(false);
            transMeta.addDatabase(dstdatabaseMeta);

            PluginRegistry registry = PluginRegistry.getInstance();
            //第一个表输入步骤(TableInputMeta)
            TableInputMeta tableInputMeta = new TableInputMeta();
            String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInputMeta);

            //给表输入添加一个DatabaseMeta连接数据库
            DatabaseMeta database_in = transMeta.findDatabase(srcdbManagement.getLinkName());
            tableInputMeta.setDatabaseMeta(database_in);

            String select_sql = "";
            if (dataMig.getTimeStamp() == null || dataMig.getTimeStamp() == "") {
                select_sql = "SELECT * FROM " + dataMig.getSrcTable();

            } else {
                select_sql = "SELECT * FROM " + dataMig.getSrcTable() + " where " + dataMig.getTimeStamp() + " > " + "\"0\"";
            }

            if (dataMig.getSqlString() != null) {
                select_sql = dataMig.getSqlString();
            }
            tableInputMeta.setSQL(select_sql);

            //添加TableInputMeta到转换中

            StepMeta tableInputMetaStep = new StepMeta(tableInputPluginId, "table_input", tableInputMeta);
            tableInputMetaStep.setLocation(400, 50);
            transMeta.addStep(tableInputMetaStep);

            //第二个步骤插入与更新

            InsertUpdateMeta tableOutputMeta = new InsertUpdateMeta();

            String tableOutputPluginId = registry.getPluginId(StepPluginType.class, tableOutputMeta);
            //添加数据库连接
            DatabaseMeta database_out = transMeta.findDatabase(dstdbManagement.getLinkName());
            tableOutputMeta.setDatabaseMeta(database_out);
            //设置操作的表
            tableOutputMeta.setTableName(dataMig.getSrcTable());//
            //设置用来查询的关键字
            tableOutputMeta.setKeyLookup(new String[]{"ID"});
            tableOutputMeta.setKeyStream(new String[]{"ID"});
            tableOutputMeta.setKeyStream2(new String[]{""});//一定要加上
            tableOutputMeta.setKeyCondition(new String[]{"="});


            String[] updatelookup = new String[fields.length];

            List<String> list = dataMig.getUpdateLookup();
            if (list == null || list.size() == 0) {
                updatelookup = fields;
            } else {
                updatelookup = list.toArray(new String[list.size()]);
            }

            String[] updateStream = updatelookup;
            Boolean[] updateOrNot = new Boolean[updatelookup.length];
            updateOrNot[0] = false;
            for (int i = 1; i < updatelookup.length; i++) {
                updateOrNot[i] = true;
            }

            tableOutputMeta.setUpdateLookup(updatelookup);
            tableOutputMeta.setUpdateStream(updateStream);
            tableOutputMeta.setUpdate(updateOrNot);
            String[] lookup = tableOutputMeta.getUpdateLookup();

            tableOutputMeta.setParentStepMeta(tableInputMetaStep);


            //添加输出步骤到转换中

            StepMeta tableOutputMetaStep = new StepMeta(tableOutputPluginId, "table_output", tableOutputMeta);
            tableOutputMetaStep.setLocation(100, 50);
            transMeta.addStep(tableOutputMetaStep);

            //hop
            transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, tableOutputMetaStep));

            if (saveTransMetaService.save(transMeta, DbMigTransUrl + dataMig.getJobName() + ".ktr", true)) {



                com.zsbatech.baasKettleManager.model.TransMeta dbTransMeta = new com.zsbatech.baasKettleManager.model.TransMeta();

               int transMetaId = transMetaList.get(0).getId();
                transMetaList.get(0).setUpdatetime(new Date());
                transMetaMapper.updateByPrimaryKey(transMetaList.get(0));

//
                TableinputStepExample tableinputStepExample = new TableinputStepExample();
                TableinputStepExample.Criteria tableCriteria = tableinputStepExample.createCriteria();
                tableCriteria.andTransMetaIdEqualTo(transMetaId);
                List<TableinputStep> tableinputStepList = tableinputStepMapper.selectByExample(tableinputStepExample);
                TableinputStep tableinputStep = tableinputStepList.get(0);
                tableinputStep.setDbConnectionName(srcdbManagement.getLinkName());
                tableinputStep.setDbManagementId(dataMig.getSrcDbconnId());
                tableinputStep.setExcSql(dataMig.getSqlString());
                tableinputStep.setUpdatetime(new Date());
                tableinputStepMapper.updateByExample(tableinputStep,tableinputStepExample);

                InsertUpdateStepExample insertUpdateStepExample = new InsertUpdateStepExample();
                InsertUpdateStepExample.Criteria insertCriteria = insertUpdateStepExample.createCriteria();
                insertCriteria.andTransMetaIdEqualTo(transMetaId);
                List<InsertUpdateStep> insertUpdateStepList = insertUpdateStepMapper.selectByExample(insertUpdateStepExample);

                InsertUpdateStep insertUpdateStep = insertUpdateStepList.get(0);
                insertUpdateStep.setDbManagementId(dataMig.getDstDbconnId());
                insertUpdateStep.setTargetTable(dataMig.getSrcTable());

                insertUpdateStepMapper.updateByExample(insertUpdateStep,insertUpdateStepExample);


                return DbMigTransUrl + dataMig.getJobName() + ".ktr";

            } else {
                return "";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
