package com.zsbatech.baasTestManager;

import com.zsbatech.baasKettleManager.service.*;
import com.zsbatech.baasKettleManager.service.impl.FtpSourceManageServiceImpl;
import com.zsbatech.baasKettleManager.service.impl.SaveTransMetaServiceImpl;
import com.zsbatech.baasKettleManager.util.FTPUtil;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FtpcatalogNode;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.utils.JWTUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private FtpSourceManageServiceImpl ftpSourceManageService;

    @Autowired
    private CatalogManageService catalogManageService;

    @Autowired
    private JobManageService jobExcuteService;

    @Autowired
    private SaveJobMetaService saveJobMetaService;
    @Autowired
    private CatalogManageService contentManageService;

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @Autowired
    private SaveTransMetaService saveTransMetaService;

    @Test
    public void save() throws Exception {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta();
        //设置转换的名称
        transMeta.setName("aaa");
        //添加转换的数据库连接
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setDatabaseType("MySQL");
        databaseMeta.setDBName("kettle");
        databaseMeta.setHostname("localhost");
        databaseMeta.setDBPort("3306");
        databaseMeta.setUsername("root");
        databaseMeta.setPassword("root");
        databaseMeta.setName("sample");
        transMeta.addDatabase(databaseMeta);
        //registry是给每个步骤生成一个标识Id用
        PluginRegistry registry = PluginRegistry.getInstance();
        //第一个表输入步骤(TableInputMeta)
        TableInputMeta tableInput = new TableInputMeta();
        String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInput);
        //给表输入添加一个DatabaseMeta连接数据库
        DatabaseMeta database_bjdt = transMeta.findDatabase(databaseMeta.getName());
        tableInput.setDatabaseMeta(database_bjdt);
        String select_sql = "SELECT * FROM " + "users";
        tableInput.setSQL(select_sql);
        //添加TableInputMeta到转换中
        StepMeta tableInputMetaStep = new StepMeta(tableInputPluginId, "步骤名", tableInput);
        //给步骤添加在spoon工具中的显示位置
        tableInputMetaStep.setDraw(true);
        tableInputMetaStep.setLocation(100, 100);
        transMeta.addStep(tableInputMetaStep);
        //第二个步骤插入与更新
        InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
        String insertUpdateMetaPluginId = registry.getPluginId(StepPluginType.class, insertUpdateMeta);
        //添加数据库连接
        DatabaseMeta database_kettle = transMeta.findDatabase(databaseMeta.getName());
        insertUpdateMeta.setDatabaseMeta(database_kettle);
        //设置操作的表
        insertUpdateMeta.setTableName("countuse");
        //设置用来查询的关键字
        insertUpdateMeta.setKeyLookup(new String[]{"ID"});
        insertUpdateMeta.setKeyStream(new String[]{"ID"});
        insertUpdateMeta.setKeyStream2(new String[]{""});//一定要加上
        insertUpdateMeta.setKeyCondition(new String[]{"="});
        //设置要更新的字段
        String[] updatelookup = {"ID", "user"};
        String[] updateStream = {"id", "user"};
        Boolean[] updateOrNot = {false, true};
        insertUpdateMeta.setUpdateLookup(updatelookup);
        insertUpdateMeta.setUpdateStream(updateStream);
        insertUpdateMeta.setUpdate(updateOrNot);
        String[] lookup = insertUpdateMeta.getUpdateLookup();
        //添加步骤到转换中
        StepMeta insertUpdateStep = new StepMeta(insertUpdateMetaPluginId, "insert_update", insertUpdateMeta);
        insertUpdateStep.setDraw(true);
        insertUpdateStep.setLocation(250, 100);
        transMeta.addStep(insertUpdateStep);
        //添加hop把两个步骤关联起来
        transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, insertUpdateStep));

        new SaveTransMetaServiceImpl().save(transMeta, "C:\\Users\\zhang\\Desktop\\aaa.ktr", true);
        saveTransMetaService.saveTransData("C:\\Users\\zhang\\Desktop\\aaa.ktr",8,8);
    }

    @Test
    public void test() throws Exception {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\aaa.ktr");
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();
        if (trans.getErrors() != 0) {
            System.out.println("Error encountered");
        }
        TableInputMeta tableInputMeta = new TableInputMeta();
    }

    @Test
    public void deTest() throws Exception {
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta();
        jobMeta.setName("job");
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("start");
        jobEntrySpecial.setRepeat(false);
        jobEntrySpecial.setSchedulerType(1);
        jobEntrySpecial.setIntervalMinutes(1);
        jobEntrySpecial.setStart(true);
        JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
        specialCopy.setLocation(30, 20);
        specialCopy.setDrawn(true);
        jobMeta.addJobEntry(specialCopy);
        JobEntryTrans jobEntryTrans = new JobEntryTrans("job");
        jobEntryTrans.setFileName("C:\\Users\\zhang\\Desktop\\transjob.ktr");
        JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
        transJob.setLocation(200, 20);
        transJob.setDrawn(true);
        jobMeta.addJobEntry(transJob);
        JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        saveJobMetaService.save(jobMeta, "C:\\Users\\zhang\\Desktop\\cads.kjb", true);
        saveJobMetaService.saveTransJobData("C:\\Users\\zhang\\Desktop\\cads.kjb");
    }

    @Test
    public void testJob() throws Exception {
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta("C:\\Users\\zhang\\Desktop\\aaa.kjb", null);
        Job job = new Job(null, jobMeta);
        job.start();
        Thread.currentThread().setName("cads");
        job.waitUntilFinished();
        if (job.getErrors() != 0) {
            System.out.println("Error encountered");
        }
    }

    @Test
    public void testmaet() {
//        saveTransMetaService.saveTransData("C:\\Users\\zhang\\Desktop\\jdbc.ktr",8,8);
        saveJobMetaService.saveTransJobData("C:\\Users\\zhang\\Desktop\\cads.kjb");
    }

    @Test
    public void testSaveByDB() {
        saveTransMetaService.saveByDB("aaa", new String[]{"id", "name"});
//        saveJobMetaService.saveTransJobData("C:\\Users\\zhang\\Desktop\\cads.kjb");
    }

    @Test
    public void testCreateDownloadJobMeta() {
        JobStartStepVO jobStartStepVO = new JobStartStepVO();
        jobStartStepVO.setTimingType((short) 0);
        jobStartStepVO.setIsRepeat((short) 0);
        FTPDownLoadStepVO ftpDownLoadStepVO = new FTPDownLoadStepVO();
        ftpDownLoadStepVO.setServerName("106.75.17.46");
        ftpDownLoadStepVO.setPort("21");
        ftpDownLoadStepVO.setUserName("kettletest");
        ftpDownLoadStepVO.setPassword("test123456");
        ftpDownLoadStepVO.setBinaryMode((short) 1);
        ftpDownLoadStepVO.setControlEncoding("UTF-8");
        ftpDownLoadStepVO.setFtpDirectory("");
        ftpDownLoadStepVO.setTargetDirectory("C:\\Users\\zhang\\Desktop\\新建文件夹");
        fileSyncJobService.createDownloadJobMeta(jobStartStepVO, ftpDownLoadStepVO, "aaa");
    }

    @Test
    public void TestFile() throws Exception {
//        FTPClient ftpClient = FTPUtil.loginFTP("106.75.17.46", 21, "test", "test123456");
//        ftpClient.makeDirectory("files/pento/dataSour");
    }

    @Test
    public void testStop() throws Exception{
//        new StopJobUtil().stopJob("C:\\Users\\zhang\\Desktop\\aaa.kjb","C:\\Users\\zhang\\Desktop\\aaa.kjb");
        List<FtpcatalogNode> stringLIst =catalogManageService.getFtpCatalog("test");
        for(FtpcatalogNode s:stringLIst){
            System.out.println(s.getParentName());
        }
    }

    @Test
    public void testFileCatalog(){
        fileSyncJobService.saveFileInfo(4,"00000000",null,null);
        JWTUtils.validateToken(RequestField.TOKEN).getUsername();
    }
}
