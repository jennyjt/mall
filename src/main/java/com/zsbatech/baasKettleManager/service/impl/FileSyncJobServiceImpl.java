package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.ftp.JobEntryFTP;
import org.pentaho.di.job.entries.ftpput.JobEntryFTPPUT;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Service
public class FileSyncJobServiceImpl implements FileSyncJobService {

    @Autowired
    private String ftpJobUrl;

    @Autowired
    private SaveJobMetaService saveJobMetaService;

    /**
     * 创建ftp下载的job
     *
     * @param jobStartStepVO
     * @param ftpDownLoadStepVO
     * @param fileName
     * @return
     */
    public boolean createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO,String fileName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobstatus(0);
        jobMeta.setName("ftp");
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("START");
        jobEntrySpecial.setStart(true);
        if (jobStartStepVO.getIsRepeat() == 1) {
            jobEntrySpecial.setRepeat(true);
        } else {
            jobEntrySpecial.setRepeat(false);
        }
        jobEntrySpecial.setSchedulerType(jobStartStepVO.getTimingType());
        if (jobStartStepVO.getTimingType() == 1) {
            jobEntrySpecial.setIntervalSeconds(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 2) {
            jobEntrySpecial.setHour(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 3) {
            jobEntrySpecial.setWeekDay(jobEntrySpecial.getWeekDay());
        } else if (jobStartStepVO.getTimingType() == 4) {
            jobEntrySpecial.setDayOfMonth(jobEntrySpecial.getDayOfMonth());
        }
        JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
        jobEntrySpecialCopy.setDrawn(true);
        jobEntrySpecialCopy.setLocation(30,20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
        JobEntryFTP jobEntryFTP = new JobEntryFTP();
        jobEntryFTP.setName("ftp下载");
        jobEntryFTP.setServerName(ftpDownLoadStepVO.getServerName());
        jobEntryFTP.setProxyHost(ftpDownLoadStepVO.getProxyHost());
        jobEntryFTP.setUserName(ftpDownLoadStepVO.getUserName());
        jobEntryFTP.setPassword(ftpDownLoadStepVO.getPassword());
        jobEntryFTP.setProxyPort(ftpDownLoadStepVO.getProxyPort());
        jobEntryFTP.setProxyPassword(ftpDownLoadStepVO.getProxyPassword());
        jobEntryFTP.setProxyUsername(ftpDownLoadStepVO.getProxyUsername());
        jobEntryFTP.setPort(ftpDownLoadStepVO.getPort());
        jobEntryFTP.setTimeout(ftpDownLoadStepVO.getTimeout());
        if (ftpDownLoadStepVO.getBinaryMode() == 1) {
            jobEntryFTP.setBinaryMode(true);
        }
        jobEntryFTP.setControlEncoding(ftpDownLoadStepVO.getControlEncoding());
        jobEntryFTP.setFtpDirectory(ftpDownLoadStepVO.getFtpDirectory());
        jobEntryFTP.setTargetDirectory(ftpDownLoadStepVO.getTargetDirectory());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTP);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80,20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        saveJobMetaService.save(jobMeta, ftpJobUrl+fileName, true);
        return true;
    }

    /**
     * 创建ftp上传的job
     *
     * @param jobStartStepVO
     * @param ftpPutStepVO
     * @param fileName
     * @return
     */
    public boolean createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO,String fileName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobstatus(0);
        jobMeta.setName("ftp");
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("START");
        jobEntrySpecial.setStart(true);
        if (jobStartStepVO.getIsRepeat() == 1) {
            jobEntrySpecial.setRepeat(true);
        } else {
            jobEntrySpecial.setRepeat(false);
        }
        jobEntrySpecial.setSchedulerType(jobStartStepVO.getTimingType());
        if (jobStartStepVO.getTimingType() == 1) {
            jobEntrySpecial.setIntervalSeconds(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 2) {
            jobEntrySpecial.setHour(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 3) {
            jobEntrySpecial.setWeekDay(jobEntrySpecial.getWeekDay());
        } else if (jobStartStepVO.getTimingType() == 4) {
            jobEntrySpecial.setDayOfMonth(jobEntrySpecial.getDayOfMonth());
        }
        JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
        jobEntrySpecialCopy.setDrawn(true);
        jobEntrySpecialCopy.setLocation(30,20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
        JobEntryFTPPUT jobEntryFTPPUT = new JobEntryFTPPUT();
        jobEntryFTPPUT.setName("ftp下载");
        jobEntryFTPPUT.setServerName(ftpPutStepVO.getServerName());
        jobEntryFTPPUT.setProxyHost(ftpPutStepVO.getProxyHost());
        jobEntryFTPPUT.setUserName(ftpPutStepVO.getUserName());
        jobEntryFTPPUT.setPassword(ftpPutStepVO.getPassword());
        jobEntryFTPPUT.setProxyPort(ftpPutStepVO.getProxyPort());
        jobEntryFTPPUT.setProxyPassword(ftpPutStepVO.getProxyPassword());
        jobEntryFTPPUT.setProxyUsername(ftpPutStepVO.getProxyUsername());
        jobEntryFTPPUT.setServerPort(ftpPutStepVO.getPort());
        jobEntryFTPPUT.setTimeout(ftpPutStepVO.getTimeout());
        if (ftpPutStepVO.getBinaryMode() == 1) {
            jobEntryFTPPUT.setBinaryMode(true);
        }
        jobEntryFTPPUT.setControlEncoding(ftpPutStepVO.getControlEncoding());
        jobEntryFTPPUT.setRemoteDirectory(ftpPutStepVO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpPutStepVO.getTargetDirectory());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTPPUT);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80,20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        saveJobMetaService.save(jobMeta, ftpJobUrl+fileName, true);
        return true;
    }
}
