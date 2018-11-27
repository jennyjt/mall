package com.zsbatech.baasKettleManager.service.impl;


import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.entries.deletefile.JobEntryDeleteFile;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
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



    public ResponseData<String> createMigration(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        try {
            Date date = new Date();
            System.out.println(date);
            /*
                入参：
                srcDbconnId:
                dstDbconnId:
                //类型
                databaseSrcType:  string
                databaseDstType:  string
                //表
                srcTable:
                dstTable:

                //table
             */
            System.out.println("transName 1: " + dataMig.getTransName());
            DbManagement srcdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
            DbManagement dstdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());
            System.out.println("transName 2: " + dataMig.getTransName());


            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta();
            System.out.println("transName 3: " + dataMig.getTransName());
            //设置转换名称
            transMeta.setName(dataMig.getTransName());
            System.out.println("transName 4: " + dataMig.getTransName());

        //添加转换数据库源连接
            DatabaseMeta databaseMeta = new DatabaseMeta();

            databaseMeta.setDatabaseType(dataMig.getDatabaseSrcType());
            System.out.println("srctype: " + dataMig.getDatabaseSrcType());
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
            dstdatabaseMeta.setDatabaseType(dataMig.getDatabaseDstType());
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
            System.out.println("tableInputPluginId: " + tableInputPluginId);
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
//            return transMeta;

            if (saveTransMetaService.save(transMeta,"C:\\Users\\de\\Desktop\\"+dataMig.getTransName()+ ".ktr",true)) {
                responseData.setOK(200,"success","success");
            }
            TransMeta transMeta1 = new TransMeta("C:\\Users\\de\\Desktop\\"+dataMig.getTransName()+ ".ktr");
            Trans trans = new Trans(transMeta1);
            trans.prepareExecution(null);
            trans.startThreads();
            trans.waitUntilFinished();



            if(trans.getErrors() != 0){
                System.out.println("gqfkjffqwhkq");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("fail!");
        }

//        Boolean bool = saveTransMetaService.saveTransData("C:\\Users\\de\\Desktop\\"+dataMig.getTransName()+ ".ktr");
//        System.out.println(bool);

        return responseData;
    }

}
