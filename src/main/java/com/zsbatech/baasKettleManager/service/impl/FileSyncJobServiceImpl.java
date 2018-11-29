package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.FtpSourceManagerMapper;
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
     * @param fileName
     * @return
     */
    public boolean createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO, String fileName) {
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
        jobEntrySpecialCopy.setLocation(30, 20);
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
        return saveJobMetaService.save(jobMeta, ftpJobUrl + fileName + ".kjb", true);
    }

    /**
     * 创建ftp上传的job
     *
     * @param jobStartStepVO
     * @param ftpPutStepVO
     * @param fileName
     * @return
     */
    public boolean createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO, String fileName) {
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
        jobEntrySpecialCopy.setLocation(30, 20);
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

        //创建目录
        List<String> fileContents = new ArrayList<>();
        fileContents.add(ftpPutStepVO.getFtpDirectory());
        fileContents.add(ftpPutStepVO.getTargetDirectory());
        catalogManageService.createCatalogs(fileContents);

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
        return saveJobMetaService.save(jobMeta, ftpJobUrl + fileName + ".kjb", true);
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
    public boolean fileSyncFtpToFtpJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO, FTPDownLoadStepVO ftpDownLoadStepVO, String fileName) {
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

        jobEntryFTPPUT.setRemoteDirectory(ftpPutStepVO.getFtpDirectory());
        jobEntryFTPPUT.setLocalDirectory(ftpDownLoadStepVO.getTargetDirectory());
        jobEntryFTPPUT.setWildcard(ftpDownLoadStepVO.getFtpFileName());
        JobEntryCopy jobEntryFtpToFtpCopy = new JobEntryCopy(jobEntryFTPPUT);
        jobEntryFtpToFtpCopy.setDrawn(true);
        jobEntryFtpToFtpCopy.setLocation(80, 20);
        jobMeta.addJobEntry(jobEntryFtpToFtpCopy);
        JobHopMeta jobHopFtpMeta = new JobHopMeta(jobEntryFTPCopy, jobEntryFtpToFtpCopy);
        jobHopFtpMeta.setUnconditional(false);
        jobMeta.addJobHop(jobHopFtpMeta);
        return saveJobMetaService.save(jobMeta, ftpJobUrl + fileName + ".kjb", true);
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
        FtpSourceManager ftpSourceManager = ftpSourceManagerMapper.selectByPrimaryKey(ftpSourceId);
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManager.getFtpHost(), Integer.valueOf(ftpSourceManager.getFtpPort()), ftpSourceManager.getUserName(), ftpSourceManager.getPassWord());
        Map<String, List<String>> fileMap = null;
        List<FilesVO> filesVOList = new ArrayList<>();
        if (ftpClient != null) {
            List<FileCatalogVO> fileCatalogVOList = getFileCataLogVO(fileCatalog);
            if(file != null){
                String[] strings = file.split("/");
                FilesVO filesVO = new FilesVO();
                filesVO.setOriginName(strings[strings.length-1]);
                filesVO.setFileName(strings[strings.length-1]);
                filesVO.setFileCatalog(fileCatalog);
                filesVO.setUpdateTime(new Date());
//                filesVO.setCreateUser();
                filesVO.setCreateTime(new Date());

                FilesFileCatalogVO filesFileCatalogVO = new FilesFileCatalogVO();
            }
            fileMap = FTPUtil.getFiles(ftpClient, fileCatalog, file);
            fileMap.get(fileCatalog);
        }
        return true;
    }


    /**
     * 存储目录结构
     * @param fileCatalogVOList
     * @return
     * @parm fileCatalog
     */
    public boolean saveFileCatalogInfo(List<FileCatalogVO> fileCatalogVOList){
        if(fileCatalogVOList != null) {
            for (FileCatalogVO fileCatalogVO : fileCatalogVOList){

            }
        }
     return true;
    }


    //获取目录的层级信息
    public List<FileCatalogVO> getFileCataLogVO(String fileCatalog){
        List<FileCatalogVO> fileCatalogVOList = new ArrayList<>();
        String[] catalogs = fileCatalog.split("/");
        for(int i = 0;i<catalogs.length ;i++){
            FileCatalogVO fileCatalogVO = new FileCatalogVO();
            fileCatalogVO.setSourceCatalog(catalogs[i]);
            fileCatalogVO.setCreateTime(new Date());
            fileCatalogVO.setUpdateTime(new Date());
            fileCatalogVO.setLayer((short)i);
            fileCatalogVOList.add(fileCatalogVO);
        }
        return fileCatalogVOList;
    }
}
