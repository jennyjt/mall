package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.controller.ContentManageController;
import com.zsbatech.baasKettleManager.service.JobExcuteService;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/27
 */
@Service
public class JobExcuteServiceImpl implements JobExcuteService {

    private static Logger logger = LoggerFactory.getLogger(ContentManageController.class);

    public void excute(String fileName) {
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta(fileName, null);
        } catch (KettleException e) {
            e.printStackTrace();
        }
        Job job = new Job(null, jobMeta);
        job.start();
        job.waitUntilFinished();
        if (job.getErrors() != 0) {
            logger.info("job运行异常");
        }
    }

    public void stop(String fileName) {
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta("C:\\Users\\de\\Desktop\\job.kjb", null);
        } catch (KettleException e) {
            e.printStackTrace();
        }
        Job job = new Job(null, jobMeta);
        job.stopAll();
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
