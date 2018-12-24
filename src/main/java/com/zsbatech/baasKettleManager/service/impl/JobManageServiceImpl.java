package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.*;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.JdbcUtil;
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
import org.springframework.transaction.annotation.Transactional;

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

    private String ftpJobUrl = ConfigUtil.getPropertyValue("file.ftpJobUrl");

    @Autowired
    private CatalogManageService catalogManageService;

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


    /**
     * 启动job
     *
     * @param jobFile
     * @return
     */
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
        job.waitUntilFinished(10000);
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

    /**
     * 停止job
     *
     * @param jobName
     * @return
     */
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

    /**
     * 修改job
     *
     * @param ftpSyncSetp
     * @return
     */
    @Transactional
    public boolean modifyJob(FTPSyncSetp ftpSyncSetp) {
        boolean isSuccess = false;
        JobMetaDO jobMetaDO = jobMetaDOMapper.selectByJobName(ftpSyncSetp.getJobName());
        int jobMetaId = 0;
        int executeStatus = 0;
        if (jobMetaDO != null) {
            jobMetaId = jobMetaDO.getTransMetaId();
            executeStatus = jobMetaDO.getExecuteStatus();
        }
        if (jobMetaId != 0 && executeStatus == 0) {
            if (ftpSyncSetp.getFtpDownLoadStepDO() != null && ftpSyncSetp.getFtpPutStepDO() != null) {
                String fileName = fileSyncJobService.fileSyncFtpToFtpJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(), ftpSyncSetp.getSrcId(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getDstId(), ftpSyncSetp.getJobName());
                if (fileName == null) return false;
                if (ftpDownLoadStepDOMapper.updateByJobId(ftpSyncSetp.getFtpDownLoadStepDO()) > 0 &&
                        ftpPutStepDOMapper.updateByJobId(ftpSyncSetp.getFtpPutStepDO()) > 0 &&
                        jobStartStepDOMapper.updateByJobId(ftpSyncSetp.getJobStartStepDO()) > 0) {
                    isSuccess = true;

                }
            } else if (ftpSyncSetp.getFtpDownLoadStepDO() != null) {
                String fileName = fileSyncJobService.createDownloadJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getJobName(), ftpSyncSetp.getSrcId());
                if (fileName == null) return false;
                if (ftpDownLoadStepDOMapper.updateByJobId(ftpSyncSetp.getFtpDownLoadStepDO()) > 0 &&
                        jobStartStepDOMapper.updateByJobId(ftpSyncSetp.getJobStartStepDO()) > 0) {
                    isSuccess = true;
                }
            } else {
                String fileName = fileSyncJobService.createPutJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(), ftpSyncSetp.getJobName(), ftpSyncSetp.getDstId());
                if (fileName == null) return false;
                if (ftpPutStepDOMapper.updateByJobId(ftpSyncSetp.getFtpPutStepDO()) > 0 &&
                        jobStartStepDOMapper.updateByJobId(ftpSyncSetp.getJobStartStepDO()) > 0) {

                    isSuccess = true;

                }
            }
        }
        return isSuccess;
    }

    public JobInfo queryJobInfo(String jobName) {
        String sql = "select a.job_name,a.updatetime,a.file_name,a.execute_status,a.job_type,fd.ftp_directory ,fd.ftp_file_name," +
                "fd.target_directory,fp.ftp_directory ,fp.put_file_name,fp.put_file_name,js.is_repeat,js.timing_type,js.timing_time," +
                "fd.ftp_source_id , fp.ftp_source_id " +
                "from job_meta a left join job_start_step js on a.id = js.job_meta_id left join ftp_put_step fp on a.id= fp.job_meta_id " +
                " left join ftp_download_step fd on a.id = fd.job_meta_id " +
                "where a.job_name = " + "\'" + jobName + "\'";
        return JdbcUtil.getJobInfo(sql);
    }


    /**
     * 删除job
     *
     * @param jobName
     * @return
     */
    public boolean removeJob(String jobName) {
        List<String> fileurlList = new ArrayList<>();
        JobMetaDO jobMetaDO = null;
        String jobFileUrl = null;
        jobMetaDO = jobMetaDOMapper.selectByJobName(jobName);
        if (jobMetaDO != null) {
            jobFileUrl = jobMetaDO.getFileName();
            if (jobMetaDO.getExecuteStatus() == 1) {
                return false;
            }
        }
        fileurlList.add(jobFileUrl);
        if (catalogManageService.deleteFiles(fileurlList) != null) {
            if (jobMetaDOMapper.deleteByJobName(jobName) > 0) {
                return true;
            }
        }
        return false;
    }

    public List<JobInfo> queryJobInfoByPage(int page, int count) {
        String sql = "select a.job_name,a.updatetime,a.file_name,a.execute_status,a.job_type,fd.ftp_directory ,fd.ftp_file_name," +
                "fd.target_directory,fp.ftp_directory ,fp.put_file_name,fp.put_file_name,js.is_repeat,js.timing_type,js.timing_time," +
                "fd.ftp_source_id , fp.ftp_source_id " +
                "from job_meta a left join job_start_step js on a.id = js.job_meta_id left join ftp_put_step fp on a.id= fp.job_meta_id " +
                " left join ftp_download_step fd on a.id = fd.job_meta_id " +
                " limit " + page + "," + count;
        return JdbcUtil.getJobInfoByPage(sql);
    }
}
