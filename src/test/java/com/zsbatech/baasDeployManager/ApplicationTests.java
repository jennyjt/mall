package com.zsbatech.baasDeployManager;

import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.baasKettleManager.service.impl.SaveJobMetaServiceImpl;
import com.zsbatech.baasKettleManager.service.impl.SaveTransMetaServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

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
        TransMeta transMeta = new TransMeta("C:\\Users\\zhang\\Desktop\\jdbc.ktr");
        Trans trans = new Trans(transMeta);
        trans.prepareExecution(null);
        trans.startThreads();
        trans.waitUntilFinished();
        if(trans.getErrors()!=0)
        {
            System.out.println("Error encountered");
        }
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
        new SaveJobMetaServiceImpl().save(jobMeta, "C:\\Users\\zhang\\Desktop\\cads.kjb", true);
    }
    @Test
    public void testJob() throws Exception{
        KettleEnvironment.init();
        //filename为作业的文件路径
        JobMeta jobMeta = new JobMeta("C:\\Users\\zhang\\Desktop\\cads.kjb",null);
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
}
