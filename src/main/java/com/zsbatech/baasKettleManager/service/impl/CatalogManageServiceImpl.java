package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.FileCatalogDOMapper;
import com.zsbatech.baasKettleManager.dao.FilesFileCatalogDOMapper;
import com.zsbatech.baasKettleManager.dao.FilesDOMapper;
import com.zsbatech.baasKettleManager.dao.FtpSourceManageDOMapper;
import com.zsbatech.baasKettleManager.model.FileCatalogDO;
import com.zsbatech.baasKettleManager.model.FilesDO;
import com.zsbatech.baasKettleManager.model.FilesFileCatalogDO;
import com.zsbatech.baasKettleManager.model.FtpSourceManageDO;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.util.FTPUtil;
import com.zsbatech.baasKettleManager.util.StringUtil;
import com.zsbatech.baasKettleManager.vo.*;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
@Service
public class CatalogManageServiceImpl implements CatalogManageService {

    @Autowired
    private FtpSourceManageDOMapper ftpSourceManageDOMapper;

    @Autowired
    private FilesDOMapper filesDOMapper;

    @Autowired
    private FileCatalogDOMapper fileCatalogDOMapper;

    @Autowired
    private FilesFileCatalogDOMapper filesFileCatalogDOMapper;

    /**
     * 创建本地目录
     *
     * @param fileCatalogs
     * @return
     */
    public Map<String, List<String>> createCatalogs(List<String> fileCatalogs) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateContentList = new ArrayList<>();
        for (String file : fileCatalogs) {
            File file1 = new File(file);
            if (file1.exists()) {
                fileList.add(file);
            } else {
                file1.mkdirs();
                newCreateContentList.add(file);
            }
            map.put("exist catalog", fileList);
            map.put("new create catalog", newCreateContentList);
        }
        return map;
    }

    /**
     * 删除本地文件
     *
     * @param files
     * @return
     */
    public Map<String, List<String>> deleteFiles(List<String> files) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateFileList = new ArrayList<>();
        for (String file : files) {
            File file1 = new File(file);
            if (file1.exists()) {
                file1.delete();
                fileList.add(file);
            } else {
                newCreateFileList.add(file);
            }
            map.put("non-existent files", fileList);
            map.put("deleted files", newCreateFileList);
        }
        return map;
    }

    /**
     * 删除本地目录
     *
     * @param fileCatalogs
     * @return
     */
    public Map<String, List<String>> deleteCatalogs(List<String> fileCatalogs) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateFileList = new ArrayList<>();
        for (String file : fileCatalogs) {
            File file1 = new File(file);
            if (file1.exists() && file1.listFiles().length == 0) {
                file1.delete();
                fileList.add(file);
                //删除目录下所有文件
            } else if (file1.exists()) {
                File[] files = file1.listFiles();
                for (File file2 : files) {
                    //文件无法删除
                    if (!file2.delete()) {
                        try {
                            throw new IOException();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (file1.listFiles().length == 0) {
                    file1.delete();
                    fileList.add(file);
                }
            } else {
                newCreateFileList.add(file);
            }
            map.put("existent catalog", fileList);
            map.put("deleted catalog", newCreateFileList);
        }
        return map;
    }

    /**
     * 查询文件
     *
     * @param fileCatalog
     * @param code
     * @return
     */
    public List<FilesDO> queryFiles(String fileCatalog, String code) {
        List<FilesDO> fileList = new ArrayList<>();
        List<FilesDO> files = filesDOMapper.queryFiles(fileCatalog, code);
        if (files != null && files.size() != 0) {
            for (FilesDO file : files) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     * 查询目录
     *
     * @param fileName
     * @param code
     * @return
     */
    public List<String> queryCataLog(String code, String fileName) {
        Set<String> strings = new HashSet<>();
        List<FilesDO> filesVOList = filesDOMapper.queryFile(code, fileName);
        System.out.println(filesVOList.size());
        List<FileCatalogDO> fileCatalogDOList =null;
        if (filesVOList != null && filesVOList.size() != 0) {
            for (FilesDO filesDO : filesVOList) {
                List<FilesFileCatalogDO> filesFileCatalogDOList = filesFileCatalogDOMapper.queryByFileId(filesDO.getId());
                List<Integer> cataLogIdList = new ArrayList<>();
                for (FilesFileCatalogDO filesFileCatalogDO : filesFileCatalogDOList) {
                    cataLogIdList.add(filesFileCatalogDO.getFileCatalogId());
                }
                if(cataLogIdList == null || cataLogIdList.size() == 0){
                    return null;
                }else {
                    fileCatalogDOList = fileCatalogDOMapper.queryCatalogById(cataLogIdList);
                }
                String cataLog = new String();
                for (FileCatalogDO fileCatalogDO : fileCatalogDOList) {
                    cataLog = cataLog + StringUtil.toString(fileCatalogDO.getSourceCatalog(), '/');
                    strings.add(cataLog);
                }
            }

        }
        return new ArrayList<>(strings);
    }

    /**
     * 存储文件目录信息
     *
     * @param files
     * @return
     */
    public int saveFiles(List<String> files) {
        List<FilesDO> filesDOList = new ArrayList<>();
        for (String file : files) {
            File file1 = new File(file);
            FilesDO filesDO = new FilesDO();
            filesDO.setFileCatalog(file1.getParentFile().toString());
            filesDO.setCreateTime(new Date());
            filesDO.setFileName(file1.getName());
            filesDO.setUpdateTime(new Date());
            filesDOList.add(filesDO);
        }
        return filesDOMapper.insertBatch(filesDOList);
    }

    @Override
    public boolean saveFile(FilesDO filesDO) {
        int result = filesDOMapper.insert(filesDO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FilesDO getFileInfoById(Integer fileId) {
        return filesDOMapper.getFileByFileId(fileId);
    }

    @Override
    public List<FilesDO> getFileInfosByIds(List<Integer> fileIds) {
        return filesDOMapper.getFilesByFileIds(fileIds);
    }


    /**
     * 获取ftp目录
     *
     * @param nickName
     * @return
     */
    public List<FtpcatalogNode> getFtpCatalog(String nickName){
        FtpSourceManageDO ftpSourceManageDO = ftpSourceManageDOMapper.selectByName(nickName);
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManageDO.getFtpHost(),Integer.valueOf(ftpSourceManageDO.getFtpPort()),ftpSourceManageDO.getUserName(),ftpSourceManageDO.getPassWord());
        return FTPUtil.ftpCatalog(FTPUtil.ftpCatalog(ftpClient,""));
    }


    @Override
    public String getFullPathByCatalogId(Integer catalogId) {
        StringBuilder sb = new StringBuilder();
        List<FileCatalogDO> catalogList = fileCatalogDOMapper.getFullPathByCatalogId(catalogId);
        for(FileCatalogDO fileCatalog: catalogList) {
            sb.append(File.separator).append(fileCatalog.getSourceCatalog());
        }
        return sb.toString();
    }

}
