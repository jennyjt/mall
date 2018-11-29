package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.FilesVOMapper;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.vo.FilesVO;
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
    private FilesVOMapper filesVOMapper;

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
                file1.mkdir();
                newCreateContentList.add(file);
            }
            map.put("已存在目录", fileList);
            map.put("新创建目录", newCreateContentList);
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
            map.put("删除文件", fileList);
            map.put("已删除文件", newCreateFileList);
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
            map.put("删除目录", fileList);
            map.put("已删除目录", newCreateFileList);
        }
        return map;
    }

    /**
     * 创建本地目录
     *
     * @param fileNames
     * @return
     */
    public List<String> queryFiles(List<String> fileNames) {
        List<String> fileList = new ArrayList<>();
        List<FilesVO> files = filesVOMapper.queryFilesByfileName(fileNames);
        if (files != null) {
            for (FilesVO file : files) {
                fileList.add(file.toString());
            }
        }
        return fileList;
    }

    /**
     * 存储文件目录信息
     *
     * @param files
     * @return
     */
    public int saveFiles(List<String> files) {
        List<FilesVO> filesVOList = new ArrayList<>();
        for (String file : files) {
            File file1 = new File(file);
            FilesVO filesVO = new FilesVO();
            filesVO.setFileCatalog(file1.getParentFile().toString());
            filesVO.setCreateTime(new Date());
            filesVO.setFileName(file1.getName());
            filesVO.setUpdateTime(new Date());
            filesVOList.add(filesVO);
        }
        return filesVOMapper.insertBatch(filesVOList);
    }

    @Override
    public boolean saveFile(FilesVO filesVO) {
        int result = filesVOMapper.insert(filesVO);
        if(result > 0) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public FilesVO getFileInfoById(Integer fileId) {
        return filesVOMapper.getFileByFileId(fileId);
    }

    @Override
    public List<FilesVO> getFileInfosByIds(List<Integer> fileIds) {
        return filesVOMapper.getFilesByFileIds(fileIds);
    }

}
