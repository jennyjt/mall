package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.TransMetaVOMapper;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.JobMetaVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.annotations.JobEntry;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.vfs.KettleVFS;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.ftp.JobEntryFTP;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-11-15
 */
@Service
public class SaveJobMetaServiceImpl implements SaveJobMetaService {

    @Autowired
    private TransMetaVOMapper transMetaVOMapper;

    public boolean save(EngineMetaInterface meta, String filename, boolean export) {
        EngineMetaInterface lmeta;
        if (export) {
            lmeta = (JobMeta) ((JobMeta) meta).realClone(false);
        } else {
            lmeta = meta;
        }
        boolean saveStatus = saveMeta(lmeta, filename);
        return saveStatus;
    }

    public boolean saveMeta(EngineMetaInterface meta, String filename) {
        meta.setFilename(filename);
        if (Utils.isEmpty(meta.getName())) {
            meta.nameFromFilename();
        }
        boolean saved = false;
        try {
            String xml = XMLHandler.getXMLHeader() + meta.getXML();
            DataOutputStream dos = new DataOutputStream(KettleVFS.getOutputStream(filename, false));
            dos.write(xml.getBytes(Const.XML_ENCODING));
            dos.close();
            saved = true;
            meta.setFilename(filename);
            meta.clearChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }

    public boolean saveTransData(String fileName) {
        return true;
    }

    public JobMetaVO getJobMetaVO(JobMeta jobMeta) {
        JobMetaVO jobMetaVO = new JobMetaVO();
        jobMetaVO.setJobName(jobMeta.getName());
        jobMetaVO.setFileName(jobMeta.getFilename() + ".kjb");
        jobMetaVO.setCreateTime(new Date());
        jobMetaVO.setUpdateTime(new Date());
        return jobMetaVO;
    }

    public JobStartStepVO getJobStartStepVO(JobMeta jobMeta) {
        JobEntryCopy jobEntryCopy = jobMeta.getStart();
        JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
        JobStartStepVO jobStartStepVO = new JobStartStepVO();
        if (jobEntryInterface instanceof JobEntrySpecial) {
            jobStartStepVO.setStepName(jobEntryInterface.getName());
            jobStartStepVO.setTimingType((short) ((JobEntrySpecial) jobEntryInterface).getSchedulerType());
            if (jobStartStepVO.getTimingType() == 1) {
                jobStartStepVO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getIntervalSeconds());
            } else if (jobStartStepVO.getTimingType() == 2) {
                jobStartStepVO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getHour());
            } else if (jobStartStepVO.getTimingType() == 3) {
                jobStartStepVO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getWeekDay());
            } else if (jobStartStepVO.getTimingType() == 4) {
                jobStartStepVO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getDayOfMonth());
            }
            if (((JobEntrySpecial) jobEntryInterface).isRepeat() == true) {
                jobStartStepVO.setIsRepeat((short) 1);
            }else {
                jobStartStepVO.setIsRepeat((short)0);
            }
            jobStartStepVO.setCreateTime(new Date());
            jobStartStepVO.setUpdateTime(new Date());
        }
        return jobStartStepVO;
    }

    public FTPDownLoadStepVO getFTPDownLoadStepVO(JobMeta jobMeta) {
        FTPDownLoadStepVO ftpDownLoadStepVO = new FTPDownLoadStepVO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for(JobEntryCopy jobEntryCopy:jobEntryCopyList){
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if(jobEntryInterface instanceof JobEntryFTP){
                ftpDownLoadStepVO.setTimeout(((JobEntryFTP) jobEntryInterface).getTimeout());
                if(((JobEntryFTP) jobEntryInterface).isBinaryMode() == true) {
                    ftpDownLoadStepVO.setBinaryMode((short)1);
                }else {
                    ftpDownLoadStepVO.setBinaryMode((short)0);
                }
                ftpDownLoadStepVO.setControlEncoding(((JobEntryFTP) jobEntryInterface).getControlEncoding());
                ftpDownLoadStepVO.setFtpDirectory(((JobEntryFTP) jobEntryInterface).getFtpDirectory());
                ftpDownLoadStepVO.setFtpFileName(((JobEntryFTP) jobEntryInterface).getWildcard());
                ftpDownLoadStepVO.setTargetDirectory(((JobEntryFTP) jobEntryInterface).getTargetDirectory());
                ftpDownLoadStepVO.setCreateTime(new Date());
                ftpDownLoadStepVO.setUpdateTime(new Date());
            }
        }
        return ftpDownLoadStepVO;
    }

    public FTPPutStepVO getFTPPutStepVO(JobMeta jobMeta){
        FTPPutStepVO ftpPutStepVO = new FTPPutStepVO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for(JobEntryCopy jobEntryCopy:jobEntryCopyList){
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if(jobEntryInterface instanceof JobEntryFTP){
                ftpPutStepVO.setTimeout(((JobEntryFTP) jobEntryInterface).getTimeout());
                if(((JobEntryFTP) jobEntryInterface).isBinaryMode() == true) {
                    ftpPutStepVO.setBinaryMode((short)1);
                }else {
                    ftpPutStepVO.setBinaryMode((short)0);
                }
                ftpPutStepVO.setControlEncoding(((JobEntryFTP) jobEntryInterface).getControlEncoding());
                ftpPutStepVO.setFtpDirectory(((JobEntryFTP) jobEntryInterface).getFtpDirectory());
                ftpPutStepVO.setPutFileName(((JobEntryFTP) jobEntryInterface).getWildcard());
                ftpPutStepVO.setTargetDirectory(((JobEntryFTP) jobEntryInterface).getTargetDirectory());
                ftpPutStepVO.setCreateTime(new Date());
                ftpPutStepVO.setUpdateTime(new Date());
            }
        }
        return ftpPutStepVO;
    }
    public boolean saveByDB(String name) {
        return true;
    }
}
