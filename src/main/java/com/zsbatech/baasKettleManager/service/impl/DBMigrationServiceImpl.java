package com.zsbatech.baasKettleManager.service.impl;


import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.dao.JobMetaMapper;
import com.zsbatech.baasKettleManager.model.*;
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

import java.sql.*;
import java.util.ArrayList;
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
    @Autowired
    JobLogService jobLogService;
    @Autowired
    JobMetaMapper jobMetaMapper;

    private String DbMigTransUrl = ConfigUtil.getPropertyValue("dbmig.transMetaUrl");
    private String DbMigJobUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");
    private String ktrpath;

    @Override
    public ResponseData<String> cycleMigration(DataMig dataMig) {


        ResponseData<String> responseData = new ResponseData<>();
        try {

            //查询job名称，如果存在，则返回。

            ktrpath = generateKtr(dataMig);
            KettleEnvironment.init();


            JobMeta jobMeta = new JobMeta();
            jobMeta.setName(dataMig.getJobName());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName("start");
            jobEntrySpecial.setRepeat(true);


            switch (dataMig.getSchedulerType()){
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

           boolean savektr = saveJobMetaService.save(jobMeta,DbMigJobUrl+dataMig.getJobName()+".kjb",true);
           boolean savejob = saveJobMetaService.saveTransJobData(DbMigJobUrl+dataMig.getJobName()+".kjb");
           if (savektr&&savejob){
               responseData.set(200,"success",DbMigJobUrl+dataMig.getJobName()+".kjb");
           }


    } catch (Exception e) {
        e.printStackTrace();
        responseData.setError(500,"创建任务异常，请确认输入参数是否正确",DbMigJobUrl+dataMig.getJobName()+".kjb");
    }

        return responseData;
    }

    @Override
    public String generateKtr(DataMig dataMig) {

        try {

            DbManagement srcdbManagement = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
            DbManagement dstdbManagement = dbManagementMapper.selectByPrimaryKey(13);


            KettleEnvironment.init();

            DatabaseMeta sourceDbMeta = new DatabaseMeta(TableUtil.getXmlByDbManagement(srcdbManagement));

            Database db = new Database(sourceDbMeta);

            db.connect();
            String selectsql = "SELECT * FROM "+dataMig.getSrcTable();
            RowMetaInterface rowMetaInterface = db.getQueryFields(selectsql,false);
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
            databaseMeta.addExtraOption(srcdbManagement.getDbType(),"characterEncoding","utf-8");
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
            dstdatabaseMeta.addExtraOption(dstdbManagement.getDbType(),"characterEncoding","utf-8");
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

            String  select_sql ="";
            if(dataMig.getTimeStamp()==null || dataMig.getTimeStamp()==""){
                select_sql = "SELECT * FROM " + dataMig.getSrcTable();

            }else{
                select_sql = "SELECT * FROM " + dataMig.getSrcTable()+ " where "+ dataMig.getTimeStamp() + " > " +"\"0\"";
            }

            if(dataMig.getSqlString() != null){
                select_sql = dataMig.getSqlString();
            }
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
            tableOutputMeta.setTableName(dataMig.getSrcTable());//
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

            if (saveTransMetaService.save(transMeta,DbMigTransUrl + dataMig.getJobName()+ ".ktr",true) &&
                    saveTransMetaService.saveTransData(DbMigTransUrl + dataMig.getJobName()+ ".ktr",dataMig.getSrcDbconnId(),13)){
              return DbMigTransUrl + dataMig.getJobName()+ ".ktr";

            } else {
                return "";
                }


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }

    @Override
    public DbJobInfo getJobDetail (String jobName) {

        DbJobInfo dbJobInfo = new DbJobInfo();

        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.75.73.34:8306/kettle_manager?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "Zsba@mysql2018*";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();

            String sql = "select a.job_name,a.updatetime,a.createtime,a.execute_status,a.job_type,b.exc_sql ,b.db_connection_name," +
                    "c.target_table,c.update_lookup ,d.create_user from job_meta a,tableinput_step b,insert_update_step c, " +
                    "db_management d where a.trans_meta_id = b.trans_meta_id and b.trans_meta_id= c.trans_meta_id and " +
                    "d.id = b.db_management_id and a.job_name = "+"\""+jobName+"\"";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                dbJobInfo = new DbJobInfo();
                rs.getString("job_name");
                dbJobInfo.setJobName(rs.getString("job_name"));
                dbJobInfo.setLinkName(rs.getString("db_connection_name"));
                dbJobInfo.setPathStr("job");
                if (rs.getString("job_type").trim().equals("0")){

                    dbJobInfo.setJobType("单次任务");
                }else{
                    dbJobInfo.setJobType("周期任务");
                }

                if(rs.getInt("execute_status")==1){
                    dbJobInfo.setExecuteStatus("执行中");
                }else{
                    dbJobInfo.setExecuteStatus("未执行");
                }

                dbJobInfo.setCreateUser(rs.getString("create_user"));
                dbJobInfo.setCreatetime(rs.getDate("createtime"));
                dbJobInfo.setUpdatetime(rs.getDate("updatetime"));
                dbJobInfo.setTableName(rs.getString("target_table"));
                dbJobInfo.setUpdateType("增量更新");
                dbJobInfo.setExecuteSql(rs.getString("exc_sql"));
                if (rs.getString("update_lookup")!=null && rs.getString("update_lookup")!=""){
                    dbJobInfo.setLookup(rs.getString("update_lookup"));
                }

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbJobInfo;
    }

    @Override
    public Pagination<DbResponse> getDbList(Integer currPage, Integer pageSize) {
        PageMethod.startPage(currPage, pageSize);
        List<DbResponse> dbResponses = new ArrayList<DbResponse>();
        DbResponse dbResponse = null;

        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.75.73.34:8306/kettle_manager?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "Zsba@mysql2018*";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();

            String sql_count = "select target_table,max(update_time) as updatetime ,count(*) from insert_update_step group by target_table;";

            ResultSet rs = stmt.executeQuery(sql_count);

            while(rs.next()){
                dbResponse = new DbResponse();

                dbResponse.setLinkName(rs.getString("target_table"));
                dbResponse.setUpdateTime(rs.getString("updatetime"));
                dbResponse.setJobNum(rs.getLong(3));


                dbResponses.add(dbResponse);
            }

            for (int i=0;i<dbResponses.size();i++){


                String sql = "select count(*) from "+dbResponses.get(i).getLinkName();
                Statement statement = conn.prepareStatement(sql);
                ResultSet rs2 = statement.executeQuery(sql);
                int rowCount=0;
                if (rs2.next()){
                    rowCount = rs2.getInt(1);
                }
                dbResponses.get(i).setLinesSuccessed((long)rowCount);
                statement.close();
                rs2.close();
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Pagination<DbResponse> dbResponsePagination = new Pagination<DbResponse>(dbResponses);
        return dbResponsePagination;

    }



    @Override
    public Pagination<DbJobInfo> getJobList(Integer currPage, Integer pageSize) {
        PageMethod.startPage(currPage, pageSize);
        List<DbJobInfo> dbJobInfoList = new ArrayList<DbJobInfo>();
        DbJobInfo dbJobInfo = null;

        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.75.73.34:8306/kettle_manager?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "Zsba@mysql2018*";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();


            String sql = "select a.job_name,a.updatetime,a.createtime,a.execute_status,a.job_type,b.exc_sql ,b.db_connection_name," +
                    "c.target_table,c.update_lookup from job_meta a,tableinput_step b,insert_update_step c where " +
                    "a.trans_meta_id = b.trans_meta_id and b.trans_meta_id= c.trans_meta_id and a.trans_meta_id>0";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                dbJobInfo = new DbJobInfo();
                rs.getString("job_name");
                dbJobInfo.setJobName(rs.getString("job_name"));
                dbJobInfo.setLinkName(rs.getString("db_connection_name"));

                if ("0".equals(rs.getString("job_type").trim())){

                    dbJobInfo.setJobType("单次任务");
                }else{
                    dbJobInfo.setJobType("周期任务");
                }

                if(rs.getInt("execute_status")==1){
                    dbJobInfo.setExecuteStatus("执行中");
                }else{
                    dbJobInfo.setExecuteStatus("未执行");
                }


                dbJobInfo.setUpdatetime(rs.getDate("updatetime"));
                dbJobInfo.setTableName(rs.getString("target_table"));

                dbJobInfoList.add(dbJobInfo);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Pagination<DbJobInfo> dbJobInfoPagination = new Pagination<DbJobInfo>(dbJobInfoList);
        return dbJobInfoPagination;

    }


    @Override
    public boolean checkUniqueJobName(String jobName) {
        JobMetaExample jobMetaExample = new JobMetaExample();
        JobMetaExample.Criteria criteria = jobMetaExample.createCriteria();
        criteria.andJobNameEqualTo(jobName);
        List<com.zsbatech.baasKettleManager.model.JobMeta> jobMetaList = jobMetaMapper.selectByExample(jobMetaExample);
        if (jobMetaList == null || jobMetaList.isEmpty()){
            return true;
        }
        return false;
    }
}
