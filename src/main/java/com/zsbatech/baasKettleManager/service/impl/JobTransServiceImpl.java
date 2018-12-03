package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobTransServiceImpl implements JobTransService{

    @Autowired
    private DbManagementMapper dbManagementMapper;
    @Autowired
    SaveTransMetaService saveTransMetaService;
    @Autowired
    SaveJobMetaService saveJobMetaService;
    @Autowired
    JobTransService jobTransService;

    private String DbMigTransUrl = ConfigUtil.getPropertyValue("dbmig.transMetaUrl");
    private String DbMigJobUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");
    private String ktrpath;

    public ResponseData<String> modifyTrans(DataMig dataMig) {
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

                responseData.setOK(200,"success","success");

            }else {
                responseData.setError(500,"fail","fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

        return responseData;
    }


    public ResponseData<String> modifyJob(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {

            KettleEnvironment.init();
            ktrpath = DbMigTransUrl + dataMig.getKtrString() ;

            JobMeta jobMeta = new JobMeta();
            jobMeta.setName(dataMig.getKtrString());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName("start");
            jobEntrySpecial.setRepeat(true);
            jobEntrySpecial.setSchedulerType(1);
            jobEntrySpecial.setIntervalSeconds(10);
            jobEntrySpecial.setIntervalMinutes(0);
//            jobEntrySpecial.setWeekDay(1);
//            jobEntrySpecial.setDayOfMonth(1);
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

            if(saveJobMetaService.save(jobMeta,DbMigJobUrl+"job.kjb",true)){
                responseData.setOK(200,"success","success");
            }


        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

        return responseData;
    }

}

