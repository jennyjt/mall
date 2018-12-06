package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.FileCatalogVOMapper;
import com.zsbatech.baasKettleManager.dao.FilesFileCatalogVOMapper;
import com.zsbatech.baasKettleManager.dao.FilesVOMapper;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.util.StringUtil;
import com.zsbatech.baasKettleManager.vo.FileCatalogVO;
import com.zsbatech.baasKettleManager.vo.FilesFileCatalogVO;
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

    @Autowired
    private FileCatalogVOMapper fileCatalogVOMapper;

    @Autowired
    private FilesFileCatalogVOMapper filesFileCatalogVOMapper;

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
    public List<FilesVO> queryFiles(String fileCatalog, String code) {
        List<FilesVO> fileList = new ArrayList<>();
        List<FilesVO> files = filesVOMapper.queryFiles(fileCatalog, code);
        if (files != null && files.size() != 0) {
            for (FilesVO file : files) {
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
        List<FilesVO> filesVOList = filesVOMapper.queryFile(code, fileName);
        if (filesVOList != null && filesVOList.size() != 0) {
            for (FilesVO filesVO : filesVOList) {
                List<FilesFileCatalogVO> filesFileCatalogVOList = filesFileCatalogVOMapper.queryByFileId(filesVO.getId());
                List<Integer> cataLogIdList = new ArrayList<>();
                for (FilesFileCatalogVO filesFileCatalogVO : filesFileCatalogVOList) {
                    cataLogIdList.add(filesFileCatalogVO.getFileCatalogId());
                }
                List<FileCatalogVO> fileCatalogVOList = fileCatalogVOMapper.queryCatalogById(cataLogIdList);
                String cataLog = new String();
                for (FileCatalogVO fileCatalogVO : fileCatalogVOList) {
                    cataLog = cataLog + StringUtil.toString(fileCatalogVO.getSourceCatalog(), '/');
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
        if (result > 0) {
            return true;
        } else {
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
