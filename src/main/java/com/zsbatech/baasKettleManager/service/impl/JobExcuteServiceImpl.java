package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.controller.ContentManageController;
import com.zsbatech.baasKettleManager.service.JobExcuteService;
import com.zsbatech.baasKettleManager.util.StopJobUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.JobStoppedException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleJobException;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobListener;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        String uuid = UUID.randomUUID().toString();
        logger.info("JobExcuteServiceImpl.excute:"+uuid+" {fileName:"+fileName+"}");
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta(fileName, null);
        } catch (KettleException e) {
            logger.error("JobExcuteServiceImpl.excute:"+uuid+" Error:"+ ExceptionUtils.getFullStackTrace(e));
        }
        Job job = new Job(null, jobMeta);
        job.start();
        job.waitUntilFinished();

        if (job.getErrors() != 0) {
            logger.error("JobExcuteServiceImpl.excute:"+uuid+" failure! Error："+job.getErrors());
            String[] errMsgList = KettleLogStore.getAppender().getBuffer(job.getLogChannelId(), false).toString().split("\n\r\n");
            String errMsg=errMsgList[0];
            logger.error(errMsg);
        }else {
            logger.error("JobExcuteServiceImpl.excute:"+uuid+" success!");
        }
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
