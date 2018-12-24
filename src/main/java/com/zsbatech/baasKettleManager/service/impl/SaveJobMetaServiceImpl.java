package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
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
import java.io.File;
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
    private FtpSourceManageDOMapper ftpSourceManageDOMapper;

    @Autowired
    private JobHopMetaDOMapper jobHopMetaDOMapper;

    @Autowired
    private JobMetaDOMapper jobMetaDOMapper;

    @Autowired
    private JobStartStepDOMapper jobStartStepDOMapper;

    @Autowired
    private FTPPutStepDOMapper ftpPutStepDOMapper;

    @Autowired
    private FTPDownLoadStepDOMapper ftpDownLoadStepDOMapper;

    @Autowired
    private TransMetaDOMapper transMetaDOMapper;

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
        boolean saved = false;
        meta.setFilename(filename);
        if (Utils.isEmpty(meta.getName())) {
            meta.nameFromFilename();
        }
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
            saved = false;
        }
        return saved;
    }

    @Transactional
    public boolean saveFTPJobData(String fileName,int upFtpSourceId,int downFtpSourceId) {
        JobMeta jobMeta = null;
        try {
            KettleEnvironment.init();
            jobMeta = new JobMeta(fileName, null);
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobStartStepDO jobStartStepDO = null;
        FTPPutStepDO ftpPutStepDO = null;
        FTPDownLoadStepDO ftpDownLoadStepDO = null;
        List<JobHopMetaDO> jobHopMetaDOList = new ArrayList<>();
        if (jobMetaDOMapper.selectByJobName(jobMeta.getName()) != null) {
            return true;
        } else if (jobMetaDOMapper.insert(getJobMetaDO(jobMeta)) != 0) {
            int jobMetaId = jobMetaDOMapper.selectByJobName(jobMeta.getName()).getId();
            jobStartStepDO = getJobStartStepDO(jobMeta);
            jobStartStepDO.setJobMetaId(jobMetaId);
            int jobStartStepId = 0;
            int ftpPutStepId = 0;
            int ftpDownLoadStepId = 0;
            if (jobStartStepDOMapper.insert(jobStartStepDO) != 0) {
                jobStartStepId = jobStartStepDOMapper.selectJobStartStepVO(jobMetaId).getId();
            }
            ftpPutStepDO = getFTPPutStepDO(jobMeta);
            ftpPutStepDO.setJobMetaId(jobMetaId);
            ftpPutStepDO.setFtpSourceId(upFtpSourceId);
            if (ftpPutStepDO.getStepName() != null && ftpPutStepDOMapper.insert(ftpPutStepDO) != 0) {
                ftpPutStepId = ftpPutStepDOMapper.selectByName(ftpPutStepDO.getStepName()).getId();
            }
            ftpDownLoadStepDO = getFTPDownLoadStepDO(jobMeta);
            ftpDownLoadStepDO.setJobMetaId(jobMetaId);
            ftpDownLoadStepDO.setFtpSourceId(downFtpSourceId);
            if (ftpDownLoadStepDO.getStepName() != null && ftpDownLoadStepDOMapper.insert(ftpDownLoadStepDO) != 0) {
                ftpDownLoadStepId = ftpDownLoadStepDOMapper.selectByName(ftpDownLoadStepDO.getStepName()).getId();
            }
            List<JobHopMeta> jobHopMetaList = jobMeta.getJobhops();
            for (JobHopMeta jobHopMeta : jobHopMetaList) {
                JobHopMetaDO jobHopMetaDO = new JobHopMetaDO();
                JobEntryInterface jobEntryInterface = jobHopMeta.getFromEntry().getEntry();
                JobEntryInterface jobEntryInterface1 = jobHopMeta.getToEntry().getEntry();
                if (jobEntryInterface instanceof JobEntrySpecial) {
                    jobHopMetaDO.setFromStepId(jobStartStepId);
                    if (jobHopMeta.isUnconditional()) {
                        jobHopMetaDO.setCondition((short) 0);
                    }
                    if (jobEntryInterface1 instanceof JobEntryFTP) {
                        jobHopMetaDO.setToStepId(ftpDownLoadStepId);
                    } else if (jobEntryInterface1 instanceof JobEntryFTPPUT) {
                        jobHopMetaDO.setToStepId(ftpPutStepId);
                    }
                    jobHopMetaDOList.add(jobHopMetaDO);
                } else if (jobEntryInterface instanceof JobEntryFTP) {
                    jobHopMetaDO.setFromStepId(ftpDownLoadStepId);
                    if (jobHopMeta.isUnconditional()) {
                        jobHopMetaDO.setCondition((short) 0);
                    } else {
                        jobHopMeta.getEvaluation();
                    }
                    if (jobEntryInterface1 instanceof JobEntryFTPPUT) {
                        jobHopMetaDO.setToStepId(ftpPutStepId);
                    }
                    jobHopMetaDOList.add(jobHopMetaDO);
                }
            }
        }
        if (jobHopMetaDOMapper.insertBatch(jobHopMetaDOList) > 0) {
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
        JobStartStepDO jobStartStepDO = null;
        JobHopMetaDO jobHopMetaDO = new JobHopMetaDO();
        JobEntryTrans jobEntryTrans = getJobEntryTrans(jobMeta);
        File file= new File(jobEntryTrans.getFilename());
        String transName =file.getName().replaceAll(".ktr","");
        int transMetaId = 0;
        if (transMetaDOMapper.selectTransMetaVO(transName) != null) {
            transMetaId = transMetaDOMapper.selectTransMetaVO(transName).getId();
        } else {
            logger.info("job中对应的转换不存在！！！！！");
            return false;
        }
        JobMetaDO jobMetaDO = getJobMetaDO(jobMeta);
        jobMetaDO.setTransMetaId(transMetaId);
        if (jobMetaDOMapper.selectByJobName(jobMeta.getName()) != null) {
            return true;
        } else if (jobMetaDOMapper.insert(jobMetaDO) != 0) {
            int jobMetaId = jobMetaDOMapper.selectByJobName(jobMeta.getName()).getId();
            jobStartStepDO = getJobStartStepDO(jobMeta);
            jobStartStepDO.setJobMetaId(jobMetaId);
            int fromStepId = 0;
            int toStepId = 0;
            if (jobStartStepDOMapper.insert(jobStartStepDO) != 0) {
                fromStepId = jobStartStepDOMapper.selectJobStartStepVO(jobStartStepDO.getJobMetaId()).getId();
            }
            toStepId = transMetaId;
            if (fromStepId != 0 && toStepId != 0) {
                jobHopMetaDO.setFromStepId(fromStepId);
                jobHopMetaDO.setToStepId(toStepId);
                jobHopMetaDO.setCondition((short) 0);
                jobHopMetaDO.setCreateTime(new Date());
                jobHopMetaDO.setUpdateTime(new Date());
            }
        }
        if (jobHopMetaDOMapper.insert(jobHopMetaDO) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public JobMetaDO getJobMetaDO(JobMeta jobMeta) {
        JobMetaDO jobMetaDO = new JobMetaDO();
        jobMetaDO.setJobName(jobMeta.getName());
        jobMetaDO.setFileName(jobMeta.getFilename());
        jobMetaDO.setCreateTime(new Date());
        jobMetaDO.setUpdateTime(new Date());
        jobMetaDO.setExecuteStatus((short) 0);
        return jobMetaDO;
    }

    public JobStartStepDO getJobStartStepDO(JobMeta jobMeta) {
        JobEntryCopy jobEntryCopy = jobMeta.getStart();
        JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
        JobStartStepDO jobStartStepDO = new JobStartStepDO();
        if (jobEntryInterface instanceof JobEntrySpecial) {
            jobStartStepDO.setStepName(jobEntryInterface.getName());
            jobStartStepDO.setTimingType((short) ((JobEntrySpecial) jobEntryInterface).getSchedulerType());
            if (jobStartStepDO.getTimingType() == 1) {
                jobStartStepDO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getIntervalSeconds());
            } else if (jobStartStepDO.getTimingType() == 2) {
                jobStartStepDO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getHour());
            } else if (jobStartStepDO.getTimingType() == 3) {
                jobStartStepDO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getWeekDay());
            } else if (jobStartStepDO.getTimingType() == 4) {
                jobStartStepDO.setTimingTime(((JobEntrySpecial) jobEntryInterface).getDayOfMonth());
            }
            if (((JobEntrySpecial) jobEntryInterface).isRepeat() == true) {
                jobStartStepDO.setIsRepeat((short) 1);
            } else {
                jobStartStepDO.setIsRepeat((short) 0);
            }
            jobStartStepDO.setCreateTime(new Date());
            jobStartStepDO.setUpdateTime(new Date());
        }
        return jobStartStepDO;
    }

    public FTPDownLoadStepDO getFTPDownLoadStepDO(JobMeta jobMeta) {
        FTPDownLoadStepDO ftpDownLoadStepDO = new FTPDownLoadStepDO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for (JobEntryCopy jobEntryCopy : jobEntryCopyList) {
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if (jobEntryInterface instanceof JobEntryFTP) {
                ftpDownLoadStepDO.setTimeout(((JobEntryFTP) jobEntryInterface).getTimeout());
                if (((JobEntryFTP) jobEntryInterface).isBinaryMode() == true) {
                    ftpDownLoadStepDO.setBinaryMode((short) 1);
                } else {
                    ftpDownLoadStepDO.setBinaryMode((short) 0);
                }
                ftpDownLoadStepDO.setControlEncoding(((JobEntryFTP) jobEntryInterface).getControlEncoding());
                ftpDownLoadStepDO.setFtpDirectory(((JobEntryFTP) jobEntryInterface).getFtpDirectory());
                ftpDownLoadStepDO.setFtpFileName(((JobEntryFTP) jobEntryInterface).getWildcard());
                ftpDownLoadStepDO.setTargetDirectory(((JobEntryFTP) jobEntryInterface).getTargetDirectory());
                ftpDownLoadStepDO.setStepName(((JobEntryFTP) jobEntryInterface).getName());
                ftpDownLoadStepDO.setCreateTime(new Date());
                ftpDownLoadStepDO.setUpdateTime(new Date());
            }
        }
        return ftpDownLoadStepDO;
    }

    public FTPPutStepDO getFTPPutStepDO(JobMeta jobMeta) {
        FTPPutStepDO ftpPutStepDO = new FTPPutStepDO();
        List<JobEntryCopy> jobEntryCopyList = jobMeta.getJobCopies();
        for (JobEntryCopy jobEntryCopy : jobEntryCopyList) {
            JobEntryInterface jobEntryInterface = jobEntryCopy.getEntry();
            if (jobEntryInterface instanceof JobEntryFTPPUT) {
                ftpPutStepDO.setTimeout(((JobEntryFTPPUT) jobEntryInterface).getTimeout());
                if (((JobEntryFTPPUT) jobEntryInterface).isBinaryMode() == true) {
                    ftpPutStepDO.setBinaryMode((short) 1);
                } else {
                    ftpPutStepDO.setBinaryMode((short) 0);
                }
                ftpPutStepDO.setControlEncoding(((JobEntryFTPPUT) jobEntryInterface).getControlEncoding());
                ftpPutStepDO.setFtpDirectory(((JobEntryFTPPUT) jobEntryInterface).getRemoteDirectory());
                ftpPutStepDO.setPutFileName(((JobEntryFTPPUT) jobEntryInterface).getWildcard());
                ftpPutStepDO.setTargetDirectory(((JobEntryFTPPUT) jobEntryInterface).getLocalDirectory());
                ftpPutStepDO.setStepName(((JobEntryFTPPUT) jobEntryInterface).getName());
                ftpPutStepDO.setCreateTime(new Date());
                ftpPutStepDO.setUpdateTime(new Date());
            }
        }
        return ftpPutStepDO;
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
        JobMetaDO jobMetaDO = jobMetaDOMapper.selectByJobName(jobName);
        JobStartStepDO jobStartStepDO = jobStartStepDOMapper.selectJobStartStepVO(jobMetaDO.getId());
        TransMetaDO transMetaDO = transMetaDOMapper.selectTransMetaVOById(jobMetaDO.getTransMetaId());
        JobMeta jobMeta = new JobMeta();
        jobMeta.setName(jobMetaDO.getJobName());
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName(jobStartStepDO.getStepName());
        if (jobStartStepDO.getIsRepeat() == 1) {
            jobEntrySpecial.setRepeat(true);
        } else {
            jobEntrySpecial.setRepeat(false);
        }
        jobEntrySpecial.setSchedulerType(jobStartStepDO.getTimingType());
        if (jobStartStepDO.getTimingType() == 1) {
            jobEntrySpecial.setIntervalMinutes(0);
            jobEntrySpecial.setIntervalSeconds(jobStartStepDO.getTimingTime());
        } else if (jobStartStepDO.getTimingType() == 2) {
            jobEntrySpecial.setHour(jobStartStepDO.getTimingTime());
        } else if (jobStartStepDO.getTimingType() == 3) {
            jobEntrySpecial.setWeekDay(jobStartStepDO.getTimingTime());
        } else if (jobStartStepDO.getTimingType() == 4) {
            jobEntrySpecial.setDayOfMonth(jobStartStepDO.getTimingTime());
        }
        jobEntrySpecial.setStart(true);
        JobEntryCopy specialCopy = new JobEntryCopy(jobEntrySpecial);
        specialCopy.setLocation(30, 20);
        specialCopy.setDrawn(true);
        jobMeta.addJobEntry(specialCopy);
        JobEntryTrans jobEntryTrans = new JobEntryTrans(transMetaDO.getTransName());
        jobEntryTrans.setFileName(DBTransUrl + transMetaDO.getFileName());
        JobEntryCopy transJob = new JobEntryCopy(jobEntryTrans);
        transJob.setLocation(200, 20);
        transJob.setDrawn(true);
        jobMeta.addJobEntry(transJob);
        JobHopMeta jobHopMeta = new JobHopMeta(specialCopy, transJob);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        return save(jobMeta, DBTransUrl + jobMetaDO.getJobName()+".kjb", true);
    }

    public boolean saveFtpIncrJobByDB(String jobName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        JobMetaDO jobMetaDO = jobMetaDOMapper.selectByJobName(jobName);
        JobStartStepDO jobStartStepDO = null;
        FTPPutStepDO ftpPutStepDO = null;
        FTPDownLoadStepDO ftpDownLoadStepDO = null;
        JobEntryCopy jobEntryFTPCopy = null;
        JobEntryCopy jobEntryFTPPutCopy = null;
        if (jobMetaDO != null) {
            jobMeta.setJobstatus(0);
            jobMeta.setName(jobName);
            jobStartStepDO = jobStartStepDOMapper.selectJobStartStepVO(jobMetaDO.getId());
            JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
            jobEntrySpecial.setName(jobStartStepDO.getStepName());
            jobEntrySpecial.setStart(true);
            if (jobStartStepDO.getIsRepeat() == 1) {
                jobEntrySpecial.setRepeat(true);
            } else {
                jobEntrySpecial.setRepeat(false);
            }
            jobEntrySpecial.setSchedulerType(jobStartStepDO.getTimingType());
            if (jobStartStepDO.getTimingType() == 1) {
                jobEntrySpecial.setIntervalSeconds(jobStartStepDO.getTimingTime());
            } else if (jobStartStepDO.getTimingType() == 2) {
                jobEntrySpecial.setHour(jobStartStepDO.getTimingTime());
            } else if (jobStartStepDO.getTimingType() == 3) {
                jobEntrySpecial.setWeekDay(jobEntrySpecial.getWeekDay());
            } else if (jobStartStepDO.getTimingType() == 4) {
                jobEntrySpecial.setDayOfMonth(jobEntrySpecial.getDayOfMonth());
            }
            ftpDownLoadStepDO = ftpDownLoadStepDOMapper.selectFTPDownLoadStepVOById(jobMetaDO.getId());
            JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
            jobEntrySpecialCopy.setDrawn(true);
            jobEntrySpecialCopy.setLocation(30, 20);
            jobMeta.addJobEntry(jobEntrySpecialCopy);
            if (ftpDownLoadStepDO != null) {
                JobEntryFTP jobEntryFTP = new JobEntryFTP();
                jobEntryFTP.setName(ftpDownLoadStepDO.getStepName());
                jobEntryFTP.setServerName(ftpDownLoadStepDO.getServerName());
                jobEntryFTP.setProxyHost(ftpDownLoadStepDO.getProxyHost());
                jobEntryFTP.setUserName(ftpDownLoadStepDO.getUserName());
                jobEntryFTP.setPassword(ftpDownLoadStepDO.getPassword());
                jobEntryFTP.setProxyPort(ftpDownLoadStepDO.getProxyPort());
                jobEntryFTP.setProxyPassword(ftpDownLoadStepDO.getProxyPassword());
                jobEntryFTP.setProxyUsername(ftpDownLoadStepDO.getProxyUsername());
                jobEntryFTP.setPort(ftpDownLoadStepDO.getPort());
                jobEntryFTP.setTimeout(ftpDownLoadStepDO.getTimeout());
                if (ftpDownLoadStepDO.getBinaryMode() == 1) {
                    jobEntryFTP.setBinaryMode(true);
                }
                jobEntryFTP.setControlEncoding(ftpDownLoadStepDO.getControlEncoding());

                //创建目录
                List<String> fileContents = new ArrayList<>();
                fileContents.add(ftpDownLoadStepDO.getFtpDirectory());
                fileContents.add(ftpDownLoadStepDO.getTargetDirectory());
//                catalogManageService.createCatalogs(fileContents);

                jobEntryFTP.setFtpDirectory(ftpDownLoadStepDO.getFtpDirectory());
                jobEntryFTP.setTargetDirectory(ftpDownLoadStepDO.getTargetDirectory());
                jobEntryFTP.setWildcard(ftpDownLoadStepDO.getFtpFileName());
                jobEntryFTPCopy = new JobEntryCopy(jobEntryFTP);
                jobEntryFTPCopy.setDrawn(true);
                jobEntryFTPCopy.setLocation(80, 20);
                jobMeta.addJobEntry(jobEntryFTPCopy);
                JobHopMetaDO jobHopMetaDO = jobHopMetaDOMapper.selectJobHopMetaVOById(jobStartStepDO.getId(), ftpDownLoadStepDO.getId());
                JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
                jobHopMeta.setUnconditional(true);
                jobMeta.addJobHop(jobHopMeta);
            }
            ftpPutStepDO = ftpPutStepDOMapper.selectFTPPutStepVOById(jobMetaDO.getId());
            JobEntryFTPPUT jobEntryFTPPUT = null;
            if (ftpPutStepDO != null) {
                jobEntryFTPPUT = new JobEntryFTPPUT();
                jobEntryFTPPUT.setName(ftpPutStepDO.getStepName());
                jobEntryFTPPUT.setServerName(ftpPutStepDO.getServerName());
                jobEntryFTPPUT.setProxyHost(ftpPutStepDO.getProxyHost());
                jobEntryFTPPUT.setUserName(ftpPutStepDO.getUserName());
                jobEntryFTPPUT.setPassword(ftpPutStepDO.getPassword());
                jobEntryFTPPUT.setProxyPort(ftpPutStepDO.getProxyPort());
                jobEntryFTPPUT.setProxyPassword(ftpPutStepDO.getProxyPassword());
                jobEntryFTPPUT.setProxyUsername(ftpPutStepDO.getProxyUsername());
                jobEntryFTPPUT.setServerPort(ftpPutStepDO.getPort());
                jobEntryFTPPUT.setTimeout(ftpPutStepDO.getTimeout());
                if (ftpPutStepDO.getBinaryMode() == 1) {
                    jobEntryFTPPUT.setBinaryMode(true);
                }
                jobEntryFTPPUT.setControlEncoding(ftpPutStepDO.getControlEncoding());

                //创建目录
                List<String> fileContents = new ArrayList<>();
                fileContents.add(ftpPutStepDO.getFtpDirectory());
                fileContents.add(ftpPutStepDO.getTargetDirectory());
//                catalogManageService.createCatalogs(fileContents);

                jobEntryFTPPUT.setRemoteDirectory(ftpPutStepDO.getFtpDirectory());
                jobEntryFTPPUT.setLocalDirectory(ftpPutStepDO.getTargetDirectory());
                jobEntryFTPPUT.setWildcard(ftpPutStepDO.getPutFileName());
                jobEntryFTPPutCopy = new JobEntryCopy(jobEntryFTPPUT);
                jobEntryFTPPutCopy.setDrawn(true);
                jobEntryFTPPutCopy.setLocation(200, 20);
                jobMeta.addJobEntry(jobEntryFTPPutCopy);
                JobHopMetaDO jobHopMetaDO = jobHopMetaDOMapper.selectJobHopMetaVOById(jobStartStepDO.getId(), ftpPutStepDO.getId());
                JobHopMeta jobHopMeta1 = null;
                if (jobHopMetaDO != null) {
                    jobHopMeta1 = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPPutCopy);
                    jobHopMeta1.setUnconditional(true);
                } else {
                    jobHopMeta1 = new JobHopMeta(jobEntryFTPCopy, jobEntryFTPPutCopy);
                    jobHopMeta1.setUnconditional(false);
                }
                jobMeta.addJobHop(jobHopMeta1);
            }
            return save(jobMeta, DBTransUrl + jobName + ".kjb", true);

        }
        return false;
    }
}
