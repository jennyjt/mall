package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.FTPUtil;
import org.apache.commons.net.ftp.FTPClient;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Service
public class FileSyncJobServiceImpl implements FileSyncJobService {

    private String ftpJobUrl = ConfigUtil.getPropertyValue("file.ftpJobUrl");

    @Autowired
    private JobMetaDOMapper jobMetaDOMapper;

    @Autowired
    private FileCatalogDOMapper fileCatalogDOMapper;

    @Autowired
    private FilesDOMapper filesDOMapper;

    @Autowired
    private FilesFileCatalogDOMapper filesFileCatalogDOMapper;

    @Autowired
    private FtpSourceManagerMapper ftpSourceManagerMapper;

    @Autowired
    private CatalogManageService catalogManageService;

    @Autowired
    private SaveJobMetaService saveJobMetaService;

    /**
     * 创建ftp下载的job
     *
     * @param jobStartStepDO
     * @param ftpDownLoadStepDO
     * @param jobName
     * @return
     */
    public String createDownloadJobMeta(JobStartStepDO jobStartStepDO, FTPDownLoadStepDO ftpDownLoadStepDO, String jobName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobstatus(0);
        jobMeta.setName(jobName);
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("START");
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
        JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
        jobEntrySpecialCopy.setDrawn(true);
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
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
        catalogManageService.createCatalogs(fileContents);

        jobEntryFTP.setFtpDirectory(ftpDownLoadStepDO.getFtpDirectory());
        jobEntryFTP.setTargetDirectory(ftpDownLoadStepDO.getTargetDirectory());
        jobEntryFTP.setWildcard(ftpDownLoadStepDO.getFtpFileName());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTP);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        if (saveJobMetaService.save(jobMeta, ftpJobUrl + jobName + ".kjb", true)) {
            saveJobMetaService.saveFTPJobData(ftpJobUrl + jobName + ".kjb",0,ftpDownLoadStepDO.getFtpSourceId());
            return ftpJobUrl + jobName + ".kjb";
        } else {
            return null;
        }
    }

    /**
     * 创建ftp上传的job
     *
     * @param jobStartStepDO
     * @param ftpPutStepDO
     * @param jobName
     * @return
     */
    public String createPutJobMeta(JobStartStepDO jobStartStepDO, FTPPutStepDO ftpPutStepDO, String jobName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobstatus(0);
        jobMeta.setName(jobName);
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("START");
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
        JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
        jobEntrySpecialCopy.setDrawn(true);
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
        JobEntryFTPPUT jobEntryFTPPUT = new JobEntryFTPPUT();
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

        FTPClient ftpClient = FTPUtil.loginFTP(ftpPutStepDO.getServerName(),Integer.valueOf(ftpPutStepDO.getPort()),ftpPutStepDO.getUserName(),ftpPutStepDO.getPassword());
        try {
           ftpClient.makeDirectory(ftpPutStepDO.getFtpDirectory());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        jobEntryFTPPUT.setRemoteDirectory(ftpPutStepDO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpPutStepDO.getTargetDirectory());
        jobEntryFTPPUT.setWildcard(ftpPutStepDO.getPutFileName());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTPPUT);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        if (saveJobMetaService.save(jobMeta, ftpJobUrl + jobName + ".kjb", true)) {
            saveJobMetaService.saveFTPJobData(ftpJobUrl + jobName + ".kjb",ftpPutStepDO.getFtpSourceId(),0);
            return ftpJobUrl + jobName + ".kjb";
        } else {
            return null;
        }
    }

    /**
     * ftp间同步文件job
     *
     * @param jobStartStepDO
     * @param ftpPutStepDO
     * @param ftpDownLoadStepDO
     * @param fileName
     * @return
     */
    public String fileSyncFtpToFtpJobMeta(JobStartStepDO jobStartStepDO, FTPPutStepDO ftpPutStepDO, FTPDownLoadStepDO ftpDownLoadStepDO, String fileName) {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobstatus(0);
        jobMeta.setName("ftp同步文件job");
        JobEntrySpecial jobEntrySpecial = new JobEntrySpecial();
        jobEntrySpecial.setName("START");
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
        JobEntryCopy jobEntrySpecialCopy = new JobEntryCopy(jobEntrySpecial);
        jobEntrySpecialCopy.setDrawn(true);
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);

        //ftp下载
        JobEntryFTP jobEntryFTP = new JobEntryFTP();
        jobEntryFTP.setName("ftp下载");
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
        List<String> fileCataLogs = new ArrayList<>();
        fileCataLogs.add(ftpDownLoadStepDO.getFtpDirectory());
        fileCataLogs.add(ftpDownLoadStepDO.getTargetDirectory());
        catalogManageService.createCatalogs(fileCataLogs);

        jobEntryFTP.setFtpDirectory(ftpDownLoadStepDO.getFtpDirectory());
        jobEntryFTP.setTargetDirectory(ftpDownLoadStepDO.getTargetDirectory());
        jobEntryFTP.setWildcard(ftpDownLoadStepDO.getFtpFileName());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTP);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);

        //ftp上传
        JobEntryFTPPUT jobEntryFTPPUT = new JobEntryFTPPUT();
        jobEntryFTPPUT.setName("ftp下载");
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

        FTPClient ftpClient = FTPUtil.loginFTP(ftpPutStepDO.getServerName(),Integer.valueOf(ftpPutStepDO.getPort()),ftpPutStepDO.getUserName(),ftpPutStepDO.getPassword());
        try {
            ftpClient.makeDirectory(ftpPutStepDO.getFtpDirectory());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        jobEntryFTPPUT.setRemoteDirectory(ftpDownLoadStepDO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpDownLoadStepDO.getTargetDirectory());
        jobEntryFTPPUT.setWildcard(ftpDownLoadStepDO.getFtpFileName());
        JobEntryCopy jobEntryFtpToFtpCopy = new JobEntryCopy(jobEntryFTPPUT);
        jobEntryFtpToFtpCopy.setDrawn(true);
        jobEntryFtpToFtpCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFtpToFtpCopy);
        JobHopMeta jobHopFtpMeta = new JobHopMeta(jobEntryFTPCopy, jobEntryFtpToFtpCopy);
        jobHopFtpMeta.setUnconditional(false);
        jobMeta.addJobHop(jobHopFtpMeta);
        if (saveJobMetaService.save(jobMeta, ftpJobUrl + fileName + ".kjb", true)) {
            return ftpJobUrl + fileName + ".kjb";
        } else {
            return null;
        }
    }

    /**
     * 存储文件信息
     *
     * @param createCode
     * @param file
     * @return
     * @parm fileCatalog
     */
    @Transactional
    public boolean saveFileInfo(int ftpSourceId, String createCode, String fileCatalog, String file) {
        boolean insert = false;
        List<FilesFileCatalogDO> filesFileCatalogDOList = new ArrayList<>();
        FtpSourceManager ftpSourceManager = ftpSourceManagerMapper.selectByPrimaryKey(ftpSourceId);
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManager.getFtpHost(), Integer.valueOf(ftpSourceManager.getFtpPort()), ftpSourceManager.getUserName(), ftpSourceManager.getPassWord());
        Map<String, List<String>> fileMap = null;
        if (ftpClient != null) {
            List<FileCatalogDO> fileCatalogDOList = getFileCataLogDO(fileCatalog);
            List<FileCatalogDO> fileCatalogDOS = saveFileCatalogInfo(fileCatalogDOList);
            if (file != null) {
                FilesDO filesDO = new FilesDO();
                filesDO.setOriginName(file);
                filesDO.setFileName(file);
                filesDO.setFileCatalog(fileCatalog);
                filesDO.setUpdateTime(new Date());
                filesDO.setCreateUser(createCode);
                filesDO.setCreateTime(new Date());
                if (filesDOMapper.insert(filesDO) != 0) {
                    int filsDOId = filesDOMapper.selectByName(filesDO.getFileName()).getId();
                    for (FileCatalogDO fileCatalogDO : fileCatalogDOS) {
                        FilesFileCatalogDO filesFileCatalogDO = new FilesFileCatalogDO();
                        filesFileCatalogDO.setFileId(filsDOId);
                        filesFileCatalogDO.setFileCatalogId(fileCatalogDO.getId());
                        filesFileCatalogDOList.add(filesFileCatalogDO);
                    }
                }
                if (filesFileCatalogDOMapper.insertBatch(filesFileCatalogDOList) != 0) {
                    insert = true;
                }

            } else {
                fileMap = FTPUtil.getFiles(ftpClient, fileCatalog, file);
                List<String> fileList = fileMap.get(fileCatalog);
                for (String file1 : fileList) {
                    FilesDO filesDO = new FilesDO();
                    filesDO.setOriginName(file1);
                    filesDO.setFileName(file1);
                    filesDO.setFileCatalog(fileCatalog);
                    filesDO.setUpdateTime(new Date());
                    filesDO.setCreateTime(new Date());
                    if (filesDOMapper.insert(filesDO) != 0) {
                        FilesDO filesDO1 = filesDOMapper.selectByName(filesDO.getFileName());
                        if (fileCatalog != null) {
                            for (FileCatalogDO fileCatalogDO : fileCatalogDOS) {
                                FilesFileCatalogDO filesFileCatalogDO = new FilesFileCatalogDO();
                                filesFileCatalogDO.setFileId(filesDO1.getId());
                                filesFileCatalogDO.setFileCatalogId(fileCatalogDO.getId());
                                filesFileCatalogDOList.add(filesFileCatalogDO);
                            }
                        } else {
                            FilesFileCatalogDO filesFileCatalogDO = new FilesFileCatalogDO();
                            filesFileCatalogDO.setFileId(filesDO1.getId());
                            filesFileCatalogDOList.add(filesFileCatalogDO);
                        }
                    }
                }
            }
            if (filesFileCatalogDOMapper.insertBatch(filesFileCatalogDOList) != 0) {
                insert = true;
            }
        }
        return insert;
    }


    /**
     * 存储目录结构
     *
     * @param fileCatalogDOList
     * @return
     * @parm fileCatalog
     */
    public List<FileCatalogDO> saveFileCatalogInfo(List<FileCatalogDO> fileCatalogDOList) {

        //查询的表中数据带id字段
        List<FileCatalogDO> fileCatalogDOS = new ArrayList<>();
        if (fileCatalogDOList != null) {
            for (int i = 0; i < fileCatalogDOList.size(); i++) {
                FileCatalogDO fileCatalogDO = fileCatalogDOMapper.select(fileCatalogDOList.get(i));
                fileCatalogDOS.add(fileCatalogDO);
                if (fileCatalogDO == null) {
                    if (i != 0) {
                        fileCatalogDO.setParentId(fileCatalogDOS.get(i - 1).getId());
                        fileCatalogDOMapper.insert(fileCatalogDO);
                    } else {
                        fileCatalogDOMapper.insert(fileCatalogDO);
                    }
                }
                if (i == fileCatalogDOList.size() - 1) {
                    fileCatalogDOS.add(fileCatalogDOMapper.select(fileCatalogDO));
                }
            }
        }
        return fileCatalogDOS;
    }


    //获取目录的层级信息
    public List<FileCatalogDO> getFileCataLogDO(String fileCatalog) {
        List<FileCatalogDO> fileCatalogDOList = new ArrayList<>();
        if (fileCatalog != null) {
            String[] catalogs = fileCatalog.split("/");
            for (int i = 0; i < catalogs.length; i++) {
                FileCatalogDO fileCatalogDO = new FileCatalogDO();
                fileCatalogDO.setSourceCatalog(catalogs[i]);
                fileCatalogDO.setCreateTime(new Date());
                fileCatalogDO.setUpdateTime(new Date());
                fileCatalogDO.setLayer((short) i);
                fileCatalogDOList.add(fileCatalogDO);
            }
        }
        return fileCatalogDOList;
    }

    public String selectByJobName(String jobName) {
        JobMetaDO jobMetaDO = jobMetaDOMapper.selectByJobName(jobName);
        if (jobMetaDO != null) {
            return jobMetaDO.getFileName();
        } else {
            return null;
        }
    }
}
