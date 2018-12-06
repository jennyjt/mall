package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.util.StopJobUtil;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/5
 */
@Service
public class JobManageServiceImpl implements JobManageService {

    private static Logger logger = LoggerFactory.getLogger(JobManageServiceImpl.class);



    public int executeJob(String jobFile){
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta(jobFile, null);
        } catch (KettleException e) {
            e.printStackTrace();
            return 1;
        }
        Job job = new Job(null, jobMeta);
        job.start();
        if (job.getErrors() != 0) {
            logger.info(job.getName());
        }
        return job.getErrors();
    }

    public void stop(String transMeta,String carteObjectId) {
        try {
            new StopJobUtil().stopJob(transMeta,carteObjectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
//        while (currentGroup.getParent() != null) {
//            currentGroup = currentGroup.getParent();
//        }
//        currentGroup.interrupt();
//        int noThreads = currentGroup.activeCount();
//        Thread[] lstThreads = new Thread[noThreads];
//        currentGroup.enumerate(lstThreads);
//        for (Thread thread : lstThreads) {
//            if (thread.getName().equals(fileName)) {
//                thread.interrupt();
//
//            }
//        }
    }

    public void stopAll() {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        Job job = new Job(null, jobMeta);
        job.stopAll();
        job.waitUntilFinished();
        if (job.getErrors() != 0) {
            logger.info("job运行异常");
        }
    }
}
