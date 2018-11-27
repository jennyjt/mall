package com.zsbatech.baasDeployManager;

import com.zsbatech.baasKettleManager.service.ContentManageService;
import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.service.impl.ContentManageServiceImpl;
import com.zsbatech.baasKettleManager.service.impl.SaveJobMetaServiceImpl;
import com.zsbatech.baasKettleManager.service.impl.SaveTransMetaServiceImpl;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.annotations.JobEntry;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private SaveJobMetaService saveJobMetaService;
    @Autowired
    private ContentManageService contentManageService;

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @Autowired
    private SaveTransMetaService saveTransMetaService;

    @Test
    public void save() throws Exception {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta();
        //设置转换的名称
        transMeta.setName("转换名称");
        //添加转换的数据库连接
        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setDatabaseType("MySQL");
        databaseMeta.setDBName("kettle");
        databaseMeta.setHostname("localhost");
        databaseMeta.setDBPort("3306");
        databaseMeta.setUsername("root");
        databaseMeta.setPassword("root");
        databaseMeta.setName("sample");
        //registry是给每个步骤生成一个标识Id用
        PluginRegistry registry = PluginRegistry.getInstance();
        //第一个表输入步骤(TableInputMeta)
        TableInputMeta tableInput = new TableInputMeta();
        String tableInputPluginId = registry.getPluginId(StepPluginType.class, tableInput);
        //给表输入添加一个DatabaseMeta连接数据库
        DatabaseMeta database_bjdt = transMeta.findDatabase("bjdt");
        tableInput.setDatabaseMeta(database_bjdt);
        String select_sql = "SELECT * FROM " + "user";
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
        DatabaseMeta database_kettle = transMeta.findDatabase("kettle");
        insertUpdateMeta.setDatabaseMeta(database_kettle);
        //设置操作的表
        insertUpdateMeta.setTableName("countuse");
        //设置用来查询的关键字
        insertUpdateMeta.setKeyLookup(new String[]{"ID"});
        insertUpdateMeta.setKeyStream(new String[]{"ID"});
        insertUpdateMeta.setKeyStream2(new String[]{""});//一定要加上
        insertUpdateMeta.setKeyCondition(new String[]{"="});
        //设置要更新的字段
        String[] updatelookup = {"ID", "dept_no", "dept_name", "dept_sex", "dept_addr"};
        String[] updateStream = {"id", "dept_no", "dept_name", "dept_sex", "dept_addr"};
        Boolean[] updateOrNot = {false, true, true, true, true, true, true};
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
    }

    @Test
    public void test() throws Exception {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\jdee.ktr");
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
        jobEntryTrans.setFileName("C:\\Users\\zhang\\Desktop\\jdbc.ktr");
        JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
        transJob.setLocation(200, 20);
        transJob.setDrawn(true);
        jobMeta.addJobEntry(transJob);
//        JobEntryDeleteFile jobEntryDeleteFile = new JobEntryDeleteFile();
//        jobEntryDeleteFile.setName("作业名称项");
//        jobEntryDeleteFile.setFilename("C:\\Users\\zhang\\Desktop\\file.txt");
//        JobEntryCopy deleteFileCopy = new JobEntryCopy(jobEntryDeleteFile);
//        deleteFileCopy.setLocation(80,20);
//        jobMeta.addJobEntry(deleteFileCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        saveJobMetaService.save(jobMeta, "C:\\Users\\zhang\\Desktop\\cads.kjb", true);
    }

    @Test
    public void testJob() throws Exception {
        KettleEnvironment.init();
        //filename为作业的文件路径
        JobMeta jobMeta = new JobMeta("C:\\Users\\zhang\\Desktop\\cads.kjb", null);
        Job job = new Job(null, jobMeta);
//    启动一个作业
        job.start();
        job.waitUntilFinished();
        //检查作业中是否有错误产生
        if (job.getErrors() != 0) {
            System.out.println("Error encountered");
        }
    }

    @Test
    public void testmaet() {
        saveTransMetaService.saveTransData("C:\\Users\\zhang\\Desktop\\jdbc.ktr");
    }

    @Test
    public void testSaveByDB() {
        saveTransMetaService.saveByDB("jdbc", new String[]{"id", "name"});
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
    public void TestFile() {
        List<String> list = new ArrayList<>();
        list.add("ccc");
        list.add("ddd");
        contentManageService.queryFiles(list);
    }
}
