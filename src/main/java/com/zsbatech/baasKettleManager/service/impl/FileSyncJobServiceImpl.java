package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.util.FTPUtil;
import com.zsbatech.baasKettleManager.vo.*;
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
    private JobMetaVOMapper jobMetaVOMapper;

    @Autowired
    private FileCatalogVOMapper fileCatalogVOMapper;

    @Autowired
    private FilesVOMapper filesVOMapper;

    @Autowired
    private FilesFileCatalogVOMapper filesFileCatalogVOMapper;

    @Autowired
    private FtpSourceManagerMapper ftpSourceManagerMapper;

    @Autowired
    private CatalogManageService catalogManageService;

    @Autowired
    private SaveJobMetaService saveJobMetaService;

    /**
     * 创建ftp下载的job
     *
     * @param jobStartStepVO
     * @param ftpDownLoadStepVO
     * @param jobName
     * @return
     */
    public String createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO, String jobName) {
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
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
        JobEntryFTP jobEntryFTP = new JobEntryFTP();
        jobEntryFTP.setName(ftpDownLoadStepVO.getStepName());
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

        //创建目录
        List<String> fileContents = new ArrayList<>();
        fileContents.add(ftpDownLoadStepVO.getFtpDirectory());
        fileContents.add(ftpDownLoadStepVO.getTargetDirectory());
        catalogManageService.createCatalogs(fileContents);

        jobEntryFTP.setFtpDirectory(ftpDownLoadStepVO.getFtpDirectory());
        jobEntryFTP.setTargetDirectory(ftpDownLoadStepVO.getTargetDirectory());
        jobEntryFTP.setWildcard(ftpDownLoadStepVO.getFtpFileName());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTP);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        if (saveJobMetaService.save(jobMeta, ftpJobUrl + jobName + ".kjb", true)) {
            saveJobMetaService.saveFTPJobData(ftpJobUrl + jobName + ".kjb",0,ftpDownLoadStepVO.getFtpSourceId());
            return ftpJobUrl + jobName + ".kjb";
        } else {
            return null;
        }
    }

    /**
     * 创建ftp上传的job
     *
     * @param jobStartStepVO
     * @param ftpPutStepVO
     * @param jobName
     * @return
     */
    public String createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO, String jobName) {
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
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);
        JobEntryFTPPUT jobEntryFTPPUT = new JobEntryFTPPUT();
        jobEntryFTPPUT.setName(ftpPutStepVO.getStepName());
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

        FTPClient ftpClient = FTPUtil.loginFTP(ftpPutStepVO.getServerName(),Integer.valueOf(ftpPutStepVO.getPort()),ftpPutStepVO.getUserName(),ftpPutStepVO.getPassword());
        try {
           ftpClient.makeDirectory(ftpPutStepVO.getFtpDirectory());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        jobEntryFTPPUT.setRemoteDirectory(ftpPutStepVO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpPutStepVO.getTargetDirectory());
        jobEntryFTPPUT.setWildcard(ftpPutStepVO.getPutFileName());
        JobEntryCopy jobEntryFTPCopy = new JobEntryCopy(jobEntryFTPPUT);
        jobEntryFTPCopy.setDrawn(true);
        jobEntryFTPCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFTPCopy);
        JobHopMeta jobHopMeta = new JobHopMeta(jobEntrySpecialCopy, jobEntryFTPCopy);
        jobHopMeta.setUnconditional(true);
        jobMeta.addJobHop(jobHopMeta);
        if (saveJobMetaService.save(jobMeta, ftpJobUrl + jobName + ".kjb", true)) {
            saveJobMetaService.saveFTPJobData(ftpJobUrl + jobName + ".kjb",ftpPutStepVO.getFtpSourceId(),0);
            return ftpJobUrl + jobName + ".kjb";
        } else {
            return null;
        }
    }

    /**
     * ftp间同步文件job
     *
     * @param jobStartStepVO
     * @param ftpPutStepVO
     * @param ftpDownLoadStepVO
     * @param fileName
     * @return
     */
    public String fileSyncFtpToFtpJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO, FTPDownLoadStepVO ftpDownLoadStepVO, String fileName) {
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
        jobEntrySpecialCopy.setLocation(30, 20);
        jobMeta.addJobEntry(jobEntrySpecialCopy);

        //ftp下载
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

        //创建目录
        List<String> fileCataLogs = new ArrayList<>();
        fileCataLogs.add(ftpDownLoadStepVO.getFtpDirectory());
        fileCataLogs.add(ftpDownLoadStepVO.getTargetDirectory());
        catalogManageService.createCatalogs(fileCataLogs);

        jobEntryFTP.setFtpDirectory(ftpDownLoadStepVO.getFtpDirectory());
        jobEntryFTP.setTargetDirectory(ftpDownLoadStepVO.getTargetDirectory());
        jobEntryFTP.setWildcard(ftpDownLoadStepVO.getFtpFileName());
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

        FTPClient ftpClient = FTPUtil.loginFTP(ftpPutStepVO.getServerName(),Integer.valueOf(ftpPutStepVO.getPort()),ftpPutStepVO.getUserName(),ftpPutStepVO.getPassword());
        try {
            ftpClient.makeDirectory(ftpPutStepVO.getFtpDirectory());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        jobEntryFTPPUT.setRemoteDirectory(ftpDownLoadStepVO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpDownLoadStepVO.getTargetDirectory());
        jobEntryFTPPUT.setWildcard(ftpDownLoadStepVO.getFtpFileName());
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
        List<FilesFileCatalogVO> filesFileCatalogVOList = new ArrayList<>();
        FtpSourceManager ftpSourceManager = ftpSourceManagerMapper.selectByPrimaryKey(ftpSourceId);
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManager.getFtpHost(), Integer.valueOf(ftpSourceManager.getFtpPort()), ftpSourceManager.getUserName(), ftpSourceManager.getPassWord());
        Map<String, List<String>> fileMap = null;
        if (ftpClient != null) {
            List<FileCatalogVO> fileCatalogVOList = getFileCataLogVO(fileCatalog);
            List<FileCatalogVO> fileCatalogVOS = saveFileCatalogInfo(fileCatalogVOList);
            if (file != null) {
                FilesVO filesVO = new FilesVO();
                filesVO.setOriginName(file);
                filesVO.setFileName(file);
                filesVO.setFileCatalog(fileCatalog);
                filesVO.setUpdateTime(new Date());
                filesVO.setCreateUser(createCode);
                filesVO.setCreateTime(new Date());
                if (filesVOMapper.insert(filesVO) != 0) {
                    int filsVOId = filesVOMapper.selectByName(filesVO.getFileName()).getId();
                    for (FileCatalogVO fileCatalogVO : fileCatalogVOS) {
                        FilesFileCatalogVO filesFileCatalogVO = new FilesFileCatalogVO();
                        filesFileCatalogVO.setFileId(filsVOId);
                        filesFileCatalogVO.setFileCatalogId(fileCatalogVO.getId());
                        filesFileCatalogVOList.add(filesFileCatalogVO);
                    }
                }
                if (filesFileCatalogVOMapper.insertBatch(filesFileCatalogVOList) != 0) {
                    insert = true;
                }

            } else {
                fileMap = FTPUtil.getFiles(ftpClient, fileCatalog, file);
                List<String> fileList = fileMap.get(fileCatalog);
                for (String file1 : fileList) {
                    FilesVO filesVO = new FilesVO();
                    filesVO.setOriginName(file1);
                    filesVO.setFileName(file1);
                    filesVO.setFileCatalog(fileCatalog);
                    filesVO.setUpdateTime(new Date());
                    filesVO.setCreateTime(new Date());
                    if (filesVOMapper.insert(filesVO) != 0) {
                        FilesVO filesVO1 = filesVOMapper.selectByName(filesVO.getFileName());
                        if (fileCatalog != null) {
                            for (FileCatalogVO fileCatalogVO : fileCatalogVOS) {
                                FilesFileCatalogVO filesFileCatalogVO = new FilesFileCatalogVO();
                                filesFileCatalogVO.setFileId(filesVO1.getId());
                                filesFileCatalogVO.setFileCatalogId(fileCatalogVO.getId());
                                filesFileCatalogVOList.add(filesFileCatalogVO);
                            }
                        } else {
                            FilesFileCatalogVO filesFileCatalogVO = new FilesFileCatalogVO();
                            filesFileCatalogVO.setFileId(filesVO1.getId());
                            filesFileCatalogVOList.add(filesFileCatalogVO);
                        }
                    }
                }
            }
            if (filesFileCatalogVOMapper.insertBatch(filesFileCatalogVOList) != 0) {
                insert = true;
            }
        }
        return insert;
    }


    /**
     * 存储目录结构
     *
     * @param fileCatalogVOList
     * @return
     * @parm fileCatalog
     */
    public List<FileCatalogVO> saveFileCatalogInfo(List<FileCatalogVO> fileCatalogVOList) {

        //查询的表中数据带id字段
        List<FileCatalogVO> fileCatalogVOS = new ArrayList<>();
        if (fileCatalogVOList != null) {
            for (int i = 0; i < fileCatalogVOList.size(); i++) {
                FileCatalogVO fileCatalogVO = fileCatalogVOMapper.select(fileCatalogVOList.get(i));
                fileCatalogVOS.add(fileCatalogVO);
                if (fileCatalogVO == null) {
                    if (i != 0) {
                        fileCatalogVO.setParentId(fileCatalogVOS.get(i - 1).getId());
                        fileCatalogVOMapper.insert(fileCatalogVO);
                    } else {
                        fileCatalogVOMapper.insert(fileCatalogVO);
                    }
                }
                if (i == fileCatalogVOList.size() - 1) {
                    fileCatalogVOS.add(fileCatalogVOMapper.select(fileCatalogVO));
                }
            }
        }
        return fileCatalogVOS;
    }


    //获取目录的层级信息
    public List<FileCatalogVO> getFileCataLogVO(String fileCatalog) {
        List<FileCatalogVO> fileCatalogVOList = new ArrayList<>();
        if (fileCatalog != null) {
            String[] catalogs = fileCatalog.split("/");
            for (int i = 0; i < catalogs.length; i++) {
                FileCatalogVO fileCatalogVO = new FileCatalogVO();
                fileCatalogVO.setSourceCatalog(catalogs[i]);
                fileCatalogVO.setCreateTime(new Date());
                fileCatalogVO.setUpdateTime(new Date());
                fileCatalogVO.setLayer((short) i);
                fileCatalogVOList.add(fileCatalogVO);
            }
        }
        return fileCatalogVOList;
    }

    public String selectByJobName(String jobName) {
        JobMetaVO jobMetaVO = jobMetaVOMapper.selectByJobName(jobName);
        if (jobMetaVO != null) {
            return jobMetaVO.getFileName();
        } else {
            return null;
        }
    }
}
