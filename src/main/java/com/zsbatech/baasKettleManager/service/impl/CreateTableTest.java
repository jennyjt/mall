package com.zsbatech.baasKettleManager.service.impl;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;


public class CreateTableTest {
    /**
     * 两个库中的表名
     */
    public static String sourceDb_tablename = "user_tables";
    public static String kettle_tablename = "user_tables";
    public static String kettle_log = "user_tables";

    /**
     * 数据库连接信息,适用于DatabaseMeta其中 一个构造器DatabaseMeta(String xml)
     */
    public static final String[] databasesXML = {
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>sourceDb</name>" +
                    "<server>172.16.0.181</server>" +
                    "<type>ORACLE</type>" +
                    "<access>Native</access>" +
                    "<database>orcl</database>" +
                    "<port>1521</port>" +
                    "<username>system</username>" +
                    "<password>123456</password>" +
                    "</connection>",
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<connection>" +
                    "<name>destDb</name>" +
                    "<server>117.50.10.62</server>" +
                    "<type>MYSQL</type>" +
                    "<access>Native</access>" +
                    "<database>switch_clearing</database>" +
                    "<port>3306</port>" +
                    "<username>root</username>" +
                    "<password>Encrypted 2bec2dcaa0be79f9db808a2228ec3f790</password>" +
                    "</connection>"
    };

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            KettleEnvironment.init();

            TransMeta transMeta = new TransMeta();
            //设置转化的名称
            transMeta.setName("转换名称");

            //添加转换的数据库连接
            for (int i=0;i<databasesXML.length;i++){
                DatabaseMeta databaseMeta = new DatabaseMeta(databasesXML[i]);
                transMeta.addDatabase(databaseMeta);
            }
            VariableSpace space = new Variables();
            //将step日志数据库配置名加入到变量集中
            space.setVariable("kettle_log","sourceDb");
            space.initializeVariablesFrom(null);
            StepLogTable stepLogTable = StepLogTable.getDefault(space,transMeta);
            //StepLogTable使用的数据库连接名（上面配置的变量名）。
            stepLogTable.setConnectionName("sourceDb");
            //设置Step日志的表名
            //stepLogTable.setTableName(kettle_log);
            //设置TransMeta的StepLogTable
            transMeta.setStepLogTable(stepLogTable);

            //registry是给每个步骤生成一个标识Id用
            PluginRegistry registry = PluginRegistry.getInstance();

            //******************************************************************

            //第一个表输入步骤(TableInputMeta)
            TableInputMeta tableInput = new TableInputMeta();
            String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInput);
            //给表输入添加一个DatabaseMeta连接数据库
            DatabaseMeta database_sourceDb = transMeta.findDatabase("sourceDb");
            tableInput.setDatabaseMeta(database_sourceDb);
            String select_sql = "SELECT * FROM "+sourceDb_tablename;
            tableInput.setSQL(select_sql);



            //添加TableInputMeta到转换中
            StepMeta tableInputMetaStep = new StepMeta("INPUTTABLE_"+sourceDb_tablename,tableInput);
            //给步骤添加在spoon工具中的显示位置
            tableInputMetaStep.setDraw(true);
            tableInputMetaStep.setLocation(100, 100);
            transMeta.addStep(tableInputMetaStep);
            tableInputMetaStep.getRowDistribution();
            //******************************************************************

            //******************************************************************
            //第二个步骤插入与更新

            InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
            insertUpdateMeta.setCommitSize(10000); //性能设置
            String insertUpdateMetaPluginId = registry.getPluginId(StepPluginType.class,insertUpdateMeta);
            //添加数据库连接
            DatabaseMeta database_kettle = transMeta.findDatabase("destDb");

            database_kettle.getDatabaseInterface();
            Database db = new Database(database_sourceDb);
            db.connect();
            RowMetaInterface rowMetaInterface = db.getQueryFields(select_sql,false);
            db = new Database(database_kettle);
            String sqlddl = db.getDDLCreationTable(kettle_tablename,rowMetaInterface);
            System.out.println(sqlddl);
            java.util.List<RowMetaInterface> indexes = stepLogTable.getRecommendedIndexes();
            StringBuilder ddl =  new StringBuilder();
            for ( int i = 0; i < indexes.size(); i++ ) {
                RowMetaInterface index = indexes.get( i );
                if ( !index.isEmpty() ) {
                    String createIndex =
                            db.getCreateIndexStatement( kettle_tablename, "IDX_" + kettle_tablename + "_" + ( i + 1 ), index
                                    .getFieldNames(), false, false, false, true );
                    if ( !Utils.isEmpty( createIndex ) ) {
                        ddl.append( createIndex );
                    }
                }
            }

            insertUpdateMeta.setDatabaseMeta(database_kettle);
            //设置操作的表
            insertUpdateMeta.setTableName(kettle_tablename);

            //设置用来查询的关键字
            insertUpdateMeta.setKeyLookup(new String[]{"ID"});
            insertUpdateMeta.setKeyStream(new String[]{"ID"});
            insertUpdateMeta.setKeyStream2(new String[]{""});//一定要加上
            insertUpdateMeta.setKeyCondition(new String[]{"="});

            //设置要更新的字段
            String[] updatelookup = {"ID"} ;
            String [] updateStream = {"ID"};
            Boolean[] updateOrNot = {false};
            insertUpdateMeta.setUpdateLookup(updatelookup);
            insertUpdateMeta.setUpdateStream(updateStream);
            insertUpdateMeta.setUpdate(updateOrNot);
            String[] lookup = insertUpdateMeta.getUpdateLookup();
            //System.out.println("******:"+lookup[1]);
            //System.out.println("insertUpdateMetaXMl:"+insertUpdateMeta.getXML());
            //添加步骤到转换中
            StepMeta insertUpdateStep = new StepMeta("INSERTUPDATE_"+kettle_tablename,insertUpdateMeta);
            insertUpdateStep.setDraw(true);
            insertUpdateStep.setLocation(250,100);
            transMeta.addStep(insertUpdateStep);
            //******************************************************************


            //******************************************************************
            //添加hop把两个步骤关联起来
            transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, insertUpdateStep));

            Trans trans = new Trans(transMeta);

            trans.execute(null); // You can pass arguments instead of null.
            trans.waitUntilFinished();
            if ( trans.getErrors() > 0 )
            {
                throw new RuntimeException( "There were errors during transformation execution." );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }


}
