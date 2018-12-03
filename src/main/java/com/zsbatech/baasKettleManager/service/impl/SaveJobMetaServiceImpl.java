package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.vo.*;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.util.Utils;
import org.pentaho.di.core.vfs.KettleVFS;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.job.JobHopMeta;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.ftp.JobEntryFTP;
import org.pentaho.di.job.entries.ftpput.JobEntryFTPPUT;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.job.entries.trans.JobEntryTrans;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.DataOutputStream;
import java.util.ArrayList;
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

    private String DBTransUrl = ConfigUtil.getPropertyValue("file.jobMetaUrl");

    private static Logger logger = LoggerFactory.getLogger(SaveJobMetaServiceImpl.class);

    @Autowired
    private JobHopMetaVOMapper jobHopMetaVOMapper;

    @Autowired
    private JobMetaVOMapper jobMetaVOMapper;

    @Autowired
    private JobStartStepVOMapper jobStartStepVOMapper;

    @Autowired
    private FTPPutStepVOMapper ftpPutStepVOMapper;

    @Autowired
    private FTPDownLoadStepVOMapper ftpDownLoadStepVOMapper;

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

    @Transactional
    public boolean saveFTPJobData(String fileName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = null;
        try {
            jobMeta = new JobMeta(fileName, null);
        } catch (KettleXMLException e) {
            e.printStackTrace();
        }
        JobStartStepVO jobStartStepVO = null;
        FTPPutStepVO ftpPutStepVO = null;
        FTPDownLoadStepVO ftpDownLoadStepVO = null;
        List<JobHopMetaVO> jobHopMetaVOList = new ArrayList<>();
        if (jobMetaVOMapper.selectTransMetaVO(jobMeta.getName()) != null) {
            return true;
        } else if (jobMetaVOMapper.insert(getJobMetaVO(jobMeta)) != 0) {
            int jobMetaId = jobMetaVOMapper.selectTransMetaVO(jobMeta.getName()).getId();
            jobStartStepVO = getJobStartStepVO(jobMeta);
            jobStartStepVO.setJobMetaId(jobMetaId);
            int jobStartStepId = 0;
            int ftpPutStepId = 0;
            int ftpDownLoadStepId = 0;
            if (jobStartStepVOMapper.insert(jobStartStepVO) != 0) {
                jobStartStepId = jobStartStepVOMapper.selectByName(jobStartStepVO.getStepName()).getId();
            }
            ftpPutStepVO = getFTPPutStepVO(jobMeta);
            ftpPutStepVO.setJobMetaId(jobMetaId);
            if (ftpPutStepVO.getStepName() != null && ftpPutStepVOMapper.insert(ftpPutStepVO) != 0) {
                ftpPutStepId = ftpDownLoadStepVOMapper.selectByName(ftpPutStepVO.getStepName()).getId();
            }
            ftpDownLoadStepVO = getFTPDownLoadStepVO(jobMeta);
            ftpDownLoadStepVO.setJobMetaId(jobMetaId);
            if (ftpDownLoadStepVO.getStepName() != null && ftpDownLoadStepVOMapper.insert(ftpDownLoadStepVO) != 0) {
                ftpDownLoadStepId = ftpDownLoadStepVOMapper.selectByName(ftpDownLoadStepVO.getStepName()).getId();
            }
            List<JobHopMeta> jobHopMetaList = jobMeta.getJobhops();
            if (jobHopMetaList.size() < 2) {

            }
            for (JobHopMeta jobHopMeta : jobHopMetaList) {
                JobHopMetaVO jobHopMetaVO = new JobHopMetaVO();
                JobEntryInterface jobEntryInterface = jobHopMeta.getFromEntry().getEntry();
                JobEntryInterface jobEntryInterface1 = jobHopMeta.getToEntry().getEntry();
                if (jobEntryInterface instanceof JobEntrySpecial) {
                    jobHopMetaVO.setFromStepId(jobStartStepId);
                    if (jobHopMeta.isUnconditional()) {
                        jobHopMetaVO.setCondition((short) 0);
                    }
                    if (jobEntryInterface1 instanceof JobEntryFTP) {
                        jobHopMetaVO.setToStepId(ftpDownLoadStepId);
                    } else if (jobEntryInterface1 instanceof JobEntryFTPPUT) {
                        jobHopMetaVO.setToStepId(ftpPutStepId);
                    }
                    jobHopMetaVOList.add(jobHopMetaVO);
                } else if (jobEntryInterface instanceof JobEntryFTP) {
                    jobHopMetaVO.setFromStepId(ftpDownLoadStepId);
                    if (jobHopMeta.isUnconditional()) {
                        jobHopMetaVO.setCondition((short) 0);
                    } else {
                        jobHopMeta.getEvaluation();
                    }
                    if (jobEntryInterface1 instanceof JobEntryFTP) {
                        jobHopMetaVO.setFromStepId(ftpDownLoadStepId);
                    } else if (jobEntryInterface1 instanceof JobEntryFTPPUT) {
                        jobHopMetaVO.setToStepId(ftpPutStepId);
                    }
                    jobHopMetaVOList.add(jobHopMetaVO);
                }
            }
        }
        if (jobHopMetaVOMapper.insertBatch(jobHopMetaVOList) > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    public boolean saveTransJobData(String fileName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = null;
        try {
            jobMeta = new JobMeta(fileName, null);
        } catch (KettleXMLException e) {
            e.printStackTrace();
        }
        JobStartStepVO jobStartStepVO = null;
        JobHopMetaVO jobHopMetaVO = new JobHopMetaVO();
        JobEntryTrans jobEntryTrans = getJobEntryTrans(jobMeta);
        String transName = jobEntryTrans.getName();
        int transMetaId = 0;
        if (transMetaVOMapper.selectTransMetaVO(transName) != null) {
            transMetaId = transMetaVOMapper.selectTransMetaVO(transName).getId();
        }else {
            logger.info("job中对应的转换不存在！！！！！");
            return false;
        }
        getJobMetaVO(jobMeta).setTransMetaId(transMetaId);
        if (jobMetaVOMapper.selectTransMetaVO(jobMeta.getName()) != null) {
            return true;
        } else if (jobMetaVOMapper.insert(getJobMetaVO(jobMeta)) != 0) {
            int jobMetaId = jobMetaVOMapper.selectTransMetaVO(jobMeta.getName()).getId();
            jobStartStepVO = getJobStartStepVO(jobMeta);
            jobStartStepVO.setJobMetaId(jobMetaId);
            int fromStepId = 0;
            int toStepId = 0;
            if (jobStartStepVOMapper.insert(jobStartStepVO) != 0) {
                fromStepId = jobStartStepVOMapper.selectJobStartStepVO(jobStartStepVO.getJobMetaId()).getId();
            }
            toStepId = transMetaId;
            if (fromStepId != 0 && toStepId != 0) {
                jobHopMetaVO.setFromStepId(fromStepId);
                jobHopMetaVO.setToStepId(toStepId);
                jobHopMetaVO.setCondition((short) 0);
                jobHopMetaVO.setCreateTime(new Date());
                jobHopMetaVO.setUpdateTime(new Date());
            }
        }
        if (jobHopMetaVOMapper.insert(jobHopMetaVO) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public JobMetaVO getJobMetaVO(JobMeta jobMeta) {
        JobMetaVO jobMetaVO = new JobMetaVO();
        jobMetaVO.setJobName(jobMeta.getName());
        jobMetaVO.setFileName(jobMeta.getFilename());
        jobMetaVO.setCreateTime(new Date());
        jobMetaVO.setUpdateTime(new Date());
        jobMetaVO.setExecuteStatus((short) 0);
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
            } else {
                jobStartStepVO.setIsRepeat((short) 0);
            }
            jobStartStepVO.setCreateTime(new Date());
            jobStartStepVO.setUpdateTime(new Date());
        }
        return jobStartStepVO;
    }

    public FTPDownLoadStepVO getFTPDownLoadStepVO(JobMeta jobMeta) {
        FTPDownLoadStepVO ftpDownLoadStepVO = new FTPDownLoadStepVO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for (JobEntryCopy jobEntryCopy : jobEntryCopyList) {
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if (jobEntryInterface instanceof JobEntryFTP) {
                ftpDownLoadStepVO.setTimeout(((JobEntryFTP) jobEntryInterface).getTimeout());
                if (((JobEntryFTP) jobEntryInterface).isBinaryMode() == true) {
                    ftpDownLoadStepVO.setBinaryMode((short) 1);
                } else {
                    ftpDownLoadStepVO.setBinaryMode((short) 0);
                }
                ftpDownLoadStepVO.setControlEncoding(((JobEntryFTP) jobEntryInterface).getControlEncoding());
                ftpDownLoadStepVO.setFtpDirectory(((JobEntryFTP) jobEntryInterface).getFtpDirectory());
                ftpDownLoadStepVO.setFtpFileName(((JobEntryFTP) jobEntryInterface).getWildcard());
                ftpDownLoadStepVO.setTargetDirectory(((JobEntryFTP) jobEntryInterface).getTargetDirectory());
                ftpDownLoadStepVO.setStepName(((JobEntryFTP) jobEntryInterface).getName());
                ftpDownLoadStepVO.setCreateTime(new Date());
                ftpDownLoadStepVO.setUpdateTime(new Date());
            }
        }
        return ftpDownLoadStepVO;
    }

    public FTPPutStepVO getFTPPutStepVO(JobMeta jobMeta) {
        FTPPutStepVO ftpPutStepVO = new FTPPutStepVO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for (JobEntryCopy jobEntryCopy : jobEntryCopyList) {
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if (jobEntryInterface instanceof JobEntryFTPPUT) {
                ftpPutStepVO.setTimeout(((JobEntryFTPPUT) jobEntryInterface).getTimeout());
                if (((JobEntryFTPPUT) jobEntryInterface).isBinaryMode() == true) {
                    ftpPutStepVO.setBinaryMode((short) 1);
                } else {
                    ftpPutStepVO.setBinaryMode((short) 0);
                }
                ftpPutStepVO.setControlEncoding(((JobEntryFTPPUT) jobEntryInterface).getControlEncoding());
                ftpPutStepVO.setFtpDirectory(((JobEntryFTPPUT) jobEntryInterface).getRemoteDirectory());
                ftpPutStepVO.setPutFileName(((JobEntryFTPPUT) jobEntryInterface).getWildcard());
                ftpPutStepVO.setTargetDirectory(((JobEntryFTPPUT) jobEntryInterface).getLocalDirectory());
                ftpPutStepVO.setStepName(((JobEntryFTPPUT) jobEntryInterface).getName());
                ftpPutStepVO.setCreateTime(new Date());
                ftpPutStepVO.setUpdateTime(new Date());
            }
        }
        return ftpPutStepVO;
    }

    public JobEntryTrans getJobEntryTrans(JobMeta jobMeta) {
        JobEntryTrans jobEntryTrans = new JobEntryTrans();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for (JobEntryCopy jobEntryCopy : jobEntryCopyList) {
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if (jobEntryInterface instanceof JobEntryTrans) {
                jobEntryTrans = (JobEntryTrans) jobEntryInterface;
            }
        }
        return jobEntryTrans;
    }

    public boolean saveIncrJobByDB(String jobName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMetaVO jobMetaVO = jobMetaVOMapper.selectTransMetaVO(jobName);
        JobStartStepVO jobStartStepVO = jobStartStepVOMapper.selectJobStartStepVO(jobMetaVO.getId());
        TransMetaVO transMetaVO = transMetaVOMapper.selectTransMetaVOById(jobMetaVO.getTransMetaId());
        JobMeta jobMeta = new JobMeta();
        jobMeta.setName(jobMetaVO.getJobName());
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName(jobStartStepVO.getStepName());
        if (jobStartStepVO.getIsRepeat() == 1) {
            jobEntrySpecial.setRepeat(true);
        } else {
            jobEntrySpecial.setRepeat(false);
        }
        jobEntrySpecial.setSchedulerType(jobStartStepVO.getTimingType());
        if (jobStartStepVO.getTimingType() == 1) {
            jobEntrySpecial.setIntervalMinutes(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 2) {
            jobEntrySpecial.setHour(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 3) {
            jobEntrySpecial.setWeekDay(jobStartStepVO.getTimingTime());
        } else if (jobStartStepVO.getTimingType() == 4) {
            jobEntrySpecial.setDayOfMonth(jobStartStepVO.getTimingTime());
        }
        jobEntrySpecial.setStart(true);
        JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
        specialCopy.setLocation(30, 20);
        specialCopy.setDrawn(true);
        jobMeta.addJobEntry(specialCopy);
        JobEntryTrans jobEntryTrans = new JobEntryTrans(transMetaVO.getTransName());
        jobEntryTrans.setFileName(DBTransUrl + transMetaVO.getFileName());
        JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
        transJob.setLocation(200, 20);
        transJob.setDrawn(true);
        jobMeta.addJobEntry(transJob);
        JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        return save(jobMeta, DBTransUrl + jobMetaVO.getFileName(), true);
    }
}
