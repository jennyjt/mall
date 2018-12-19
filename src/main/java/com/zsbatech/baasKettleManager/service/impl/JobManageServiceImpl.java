package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.*;
import com.zsbatech.baasKettleManager.vo.FTPSyncSetp;
import com.zsbatech.baasKettleManager.vo.JobInfo;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/5
 */
@Service
public class JobManageServiceImpl implements JobManageService {

    private static Logger logger = LoggerFactory.getLogger(JobManageServiceImpl.class);

    private static Map<String, Job> jobMap = new HashMap<>();

    @Autowired
    private JobLogService jobLogService;

    @Autowired
    private JobStartStepDOMapper jobStartStepDOMapper;

    @Autowired
    private FTPDownLoadStepDOMapper ftpDownLoadStepDOMapper;

    @Autowired
    private FTPPutStepDOMapper ftpPutStepDOMapper;

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @Autowired
    private SaveJobMetaService saveJobMetaService;

    @Autowired
    private JobMetaDOMapper jobMetaDOMapper;

    @Autowired
    private ExceptionLogDOMapper exceptionLogDOMapper;

    public int executeJob(String jobFile) {
        if (!new File(jobFile).exists()) {
            saveJobMetaService.saveIncrJobByDB("job2");
        }
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta(jobFile, null);
        } catch (KettleException e) {
            e.printStackTrace();
            return 1;
        }
        Job job = new Job(null, jobMeta);
        jobMap.put(jobMeta.getName(), job);
        job.start();
        job.getJobTracker().clear();
        if (job.getErrors() != 0) {
            logger.info(job.getName());
            String[] errMsgList = KettleLogStore.getAppender().getBuffer(job.getLogChannelId(), false).toString().split("\n\r\n");
            ExceptionLogDO exceptionLogDO = new ExceptionLogDO();
            exceptionLogDO.setExceptionInfo(errMsgList[0]);
            exceptionLogDO.setJobName(job.getName());
            exceptionLogDO.setOccurTime(new Date());
            exceptionLogDOMapper.insert(exceptionLogDO);
            String errMsg = errMsgList[0];

        }
        return job.getErrors();
    }

    public boolean stop(String jobName) {
        JobMetaDO jobMetaDO = new JobMetaDO();
        jobMetaDO.setJobName(jobName);
        boolean isStopped = false;
        Job job = jobMap.get(jobName);
        if (job == null) {
            return false;
        }
        job.stopAll();
        if (job.isStopped()) {
            jobMap.remove(jobName);
            jobMetaDO.setUpdateTime(new Date());
            jobMetaDO.setExecuteStatus((short) 0);
            if (jobMetaDOMapper.updateByJobName(jobMetaDO) > 0) {
                isStopped = true;
            }
        }
        return isStopped;
    }


    public boolean stopJobs(List<String> jobNames) {
        int jobMapStartSize = jobMap.size();
        boolean isStopped = false;
        for (String jobName : jobNames) {
            Job job = jobMap.get(jobName);
            job.stopAll();
            jobMap.remove(jobName);
        }
        int jobMapEndSize = jobMap.size();
        if (jobMapStartSize == jobMapEndSize + jobNames.size()) {
            isStopped = true;

        }
        return isStopped;
    }

    public boolean modifyJob(FTPSyncSetp ftpSyncSetp) {
        boolean isSuccess = false;
        JobMetaDO jobMetaDO = jobMetaDOMapper.selectByJobName(ftpSyncSetp.getJobName());
        if (jobMetaDO != null) {
        }
        if (jobMetaDO.getTransMetaId() == 0) {
            if (ftpSyncSetp.getFtpDownLoadStepDO() != null && ftpSyncSetp.getFtpPutStepDO() != null) {
                String fileName = fileSyncJobService.fileSyncFtpToFtpJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(), ftpSyncSetp.getSrcNickName(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getDstNickName(), ftpSyncSetp.getJobName());
                if (fileName != null) {
                    isSuccess = true;
                }
            } else if (ftpSyncSetp.getFtpDownLoadStepDO() != null) {
                String fileName = fileSyncJobService.createDownloadJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getJobName(), ftpSyncSetp.getSrcNickName());
                if (fileName != null) {
                    isSuccess = true;
                }
            } else {
                String fileName = fileSyncJobService.createPutJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(), ftpSyncSetp.getJobName(), ftpSyncSetp.getDstNickName());
                if (fileName != null) {
                    isSuccess = true;
                }
            }
        }
        return isSuccess;
    }

    public List<JobMetaDO> queryJob(String jobType){
//        jobMetaDOMapper.selectByJobName();
        return null;
    }
}
