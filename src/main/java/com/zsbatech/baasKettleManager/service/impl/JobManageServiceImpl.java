package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.ExceptionLogVOMapper;
import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.vo.ExceptionLogVO;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.gui.JobTracker;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.www.SlaveServerJobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/5
 */
@Service
public class JobManageServiceImpl implements JobManageService {

    private static Logger logger = LoggerFactory.getLogger(JobManageServiceImpl.class);

    @Autowired
    private ExceptionLogVOMapper exceptionLogVOMapper;

    public int executeJob(String jobFile) {
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
            String[] errMsgList = KettleLogStore.getAppender().getBuffer(job.getLogChannelId(), false).toString().split("\n\r\n");
            ExceptionLogVO exceptionLogVO = new ExceptionLogVO();
            exceptionLogVO.setExceptionInfo(errMsgList[0]);
            exceptionLogVO.setJobName(job.getName());
            exceptionLogVO.setOccurTime(new Date());
            exceptionLogVOMapper.insert(exceptionLogVO);
            String errMsg = errMsgList[0];

        }
        return job.getErrors();
    }

    public void stop(String jobName) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < lstThreads.length; i++) {
            String name = lstThreads[i].toString();
            if (name.equals(jobName)) {
                System.out.println(lstThreads[i + 1].getName());
                lstThreads[i + 1].interrupt();
                lstThreads[i + 1].stop();
                lstThreads[i].interrupt();
                System.out.println("===================================");
                break;

            }
        }
    }

    public void stopAll() {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        int index = 0;
        for (int i = 0; i < lstThreads.length; i++) {
            if (lstThreads[i].getName().equals("Timer-0")) {
                index = i;
                break;
            }
        }
        for (int j = index; j < lstThreads.length; j++) {
            lstThreads[j].interrupt();
        }
    }
}
