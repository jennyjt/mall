package com.zsbatech.baasKettleManager.service.impl;


import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.deletefile.JobEntryDeleteFile;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    SaveTransMetaService saveTransMetaService;
    @Autowired
    SaveJobMetaService saveJobMetaService;


    private String path = "C:\\Users\\de\\Desktop\\";
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

            if (saveTransMetaService.save(transMeta,path + dataMig.getTransName()+ ".ktr",true)) {
                saveTransMetaService.saveTransData(path + dataMig.getTransName()+ ".ktr");

            TransMeta transMeta1 = new TransMeta(path + dataMig.getTransName()+ ".ktr");
            Trans trans = new Trans(transMeta1);
            trans.prepareExecution(null);
            trans.startThreads();
            trans.waitUntilFinished();
            responseData.setOK(200,"success","success");

            }else {
                responseData.setError(500,"fail","fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

//        Boolean bool = saveTransMetaService.saveTransData("C:\\Users\\de\\Desktop\\"+dataMig.getTransName()+ ".ktr");
//        System.out.println(bool);

        return responseData;
    }



    public ResponseData<String> cycleMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {

            KettleEnvironment.init();
//            ktrpath = dataMig.getKtrString();
            ktrpath = "C:\\Users\\de\\Desktop\\transjob.ktr" ;

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
            JobEntryTrans jobEntryTrans = new JobEntryTrans("job");
            jobEntryTrans.setFileName(ktrpath);
            JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
            transJob.setLocation(200,20);
            transJob.setDrawn(true);
            jobMeta.addJobEntry(transJob);

            JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
            jobHopMeta.setUnconditional(true);
            jobMeta.addJobHop(jobHopMeta);

            saveJobMetaService.save(jobMeta,path+"job.kjb",true);
            JobMeta jobMeta1 = new JobMeta(path+"job.kjb",null);
            Job job = new Job(null,jobMeta1);
            job.start();
            Thread.currentThread().setName("aaa");
            job.waitUntilFinished();

            if(job.getErrors() != 0){

            }

            responseData.setOK(200,"success","success");

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

        return responseData;
    }

    public ResponseData<String> insertupdateMigration(DataMig dataMig) {
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
            String select_sql = "SELECT * FROM " + dataMig.getSrcTable()+ " where "+ dataMig.getTimeStamp() + " > " +"\""+dataMig.getUpdatetime()+"\"";

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

            //update字段
//            String[] updatelookup = {"id","name","age","sex","create_time","update_time"} ;
//            String [] updateStream = {"id","name","age","sex","create_time","update_time"};
//            Boolean[] updateOrNot = {false,true,true,true,true,true};
            List<String> list = dataMig.getUpdateLookup();
            String[] updatelookup = list.toArray(new String[list.size()]);
            String[] updateStream = updatelookup;
            Boolean[] updateOrNot =new Boolean[list.size()];
            updateOrNot[0] = false;
            for (int i=1;i<list.size();i++){
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
            System.out.println(path + dataMig.getTransName()+ ".ktr");

            if (saveTransMetaService.save(transMeta,path + dataMig.getTransName()+ ".ktr",true)) {
                org.pentaho.di.trans.TransMeta transMeta1 = new TransMeta(path + dataMig.getTransName()+ ".ktr");
                Trans trans = new Trans(transMeta1);
                trans.prepareExecution(null);
                trans.startThreads();
                trans.waitUntilFinished();
                responseData.setOK(200,"success","success");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"fail","fail");
        }

//        Boolean bool = saveTransMetaService.saveTransData("C:\\Users\\de\\Desktop\\"+dataMig.getTransName()+ ".ktr");
//        System.out.println(bool);

        return responseData;
    }

}
