package com.zsbatech.baasDeployManager;

import com.zsbatech.baasKettleManager.service.ContentManageService;
import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.service.impl.ContentManageServiceImpl;
import com.zsbatech.baasKettleManager.service.impl.SaveTransMetaServiceImpl;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.deletefile.JobEntryDeleteFile;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
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
    private ContentManageService contentManageService;

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @Autowired
    private SaveTransMetaService saveTransMetaService;

    @Test
    public void save() {
        TransMeta transMeta = null;
        try {
            KettleEnvironment.init();
            transMeta = new TransMeta();
            transMeta.setName("转换名称");
        } catch (KettleException e) {
            e.printStackTrace();
        }
        new SaveTransMetaServiceImpl().save(transMeta, "C:\\Users\\zhang\\Desktop\\aaa.ktr", true);
    }

    @Test
    public void test() throws  Exception{
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\jdee.ktr");
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();
        if(trans.getErrors()!=0)
        {
            System.out.println("Error encountered");
        }
        TableInputMeta tableInputMeta = new TableInputMeta();
    }

    @Test
    public void deTest() throws Exception{
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta();
        jobMeta.setName("删除文件");
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("start");
        jobEntrySpecial.setRepeat(false);
        jobEntrySpecial.setSchedulerType(0);
        jobEntrySpecial.setWeekDay(1);
        jobEntrySpecial.setDayOfMonth(1);
        jobEntrySpecial.setStart(true);
        JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
        specialCopy.setLocation(30,20);
        jobMeta.addJobEntry(specialCopy);
        JobEntryDeleteFile jobEntryDeleteFile = new JobEntryDeleteFile();
        jobEntryDeleteFile.setName("作业名称项");
        jobEntryDeleteFile.setFilename("C:\\Users\\zhang\\Desktop\\file.txt");
        JobEntryCopy deleteFileCopy = new JobEntryCopy(jobEntryDeleteFile);
        deleteFileCopy.setLocation(80,20);
        jobMeta.addJobEntry(deleteFileCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, deleteFileCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        new SaveTransMetaServiceImpl().save(jobMeta, "C:\\Users\\zhang\\Desktop\\cads.kjb", true);
    }
    @Test
    public void testJob() throws Exception{
        KettleEnvironment.init();
        //filename为作业的文件路径
        JobMeta jobMeta = new JobMeta("C:\\Users\\zhang\\Desktop\\ftp.kjb",null);
        Job job = new Job(null,jobMeta);
//    启动一个作业
        job.start();
        job.waitUntilFinished();
        //检查作业中是否有错误产生
        if(job.getErrors()!=0){
            System.out.println("Error encountered");
        }
    }

    @Test
    public void testmaet(){
        saveTransMetaService.saveTransData("C:\\Users\\zhang\\Desktop\\jdbc.ktr");
    }

    @Test
    public void testSaveByDB(){
        saveTransMetaService.saveByDB("jdbc",new String[]{"id","name"});
    }
    @Test
    public void testCreateDownloadJobMeta(){
        JobStartStepVO jobStartStepVO = new JobStartStepVO();
        jobStartStepVO.setTimingType((short)0);
        jobStartStepVO.setIsRepeat((short)0);
        FTPDownLoadStepVO ftpDownLoadStepVO = new  FTPDownLoadStepVO();
        ftpDownLoadStepVO.setServerName("106.75.17.46");
        ftpDownLoadStepVO.setPort("21");
        ftpDownLoadStepVO.setUserName("kettletest");
        ftpDownLoadStepVO.setPassword("test123456");
        ftpDownLoadStepVO.setBinaryMode((short)1);
        ftpDownLoadStepVO.setControlEncoding("UTF-8");
        ftpDownLoadStepVO.setFtpDirectory("");
        ftpDownLoadStepVO.setTargetDirectory("C:\\Users\\zhang\\Desktop\\新建文件夹");
        fileSyncJobService.createDownloadJobMeta(jobStartStepVO,ftpDownLoadStepVO,"aaa");
    }

    @Test
    public void TestFile(){
        List<String> list = new ArrayList<>();
        list.add("ccc");
        list.add("ddd");
        contentManageService.queryFiles(list);
    }
}
