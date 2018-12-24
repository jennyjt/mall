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
import com.zsbatech.baasKettleManager.util.Singleton;
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
                            return null;
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
        List<FileCatalogDO> fileCatalogDOList = null;
        if (filesVOList != null && filesVOList.size() != 0) {
            for (FilesDO filesDO : filesVOList) {
                List<FilesFileCatalogDO> filesFileCatalogDOList = filesFileCatalogDOMapper.queryByFileId(filesDO.getId());
                List<Integer> cataLogIdList = new ArrayList<>();
                for (FilesFileCatalogDO filesFileCatalogDO : filesFileCatalogDOList) {
                    cataLogIdList.add(filesFileCatalogDO.getFileCatalogId());
                }
                if (cataLogIdList == null || cataLogIdList.size() == 0) {
                    return null;
                } else {
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
    public List<FileCatalogVO> getFtpCatalog(String nickName) {
        List<FileCatalogNode> nodeList = getFileCatalogDO(nickName);
        List<FileCatalogVO> fileCatalogVOList = getFileCataLogVO(nodeList);
        return fileCatalogVOList;
    }

    public List<FileCatalogNode> getFileCatalogDO(String nickName) {
        FtpSourceManageDO ftpSourceManageDO = ftpSourceManageDOMapper.selectByName(nickName);
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManageDO.getFtpHost(), Integer.valueOf(ftpSourceManageDO.getFtpPort()), ftpSourceManageDO.getUserName(), ftpSourceManageDO.getPassWord());
        return FTPUtil.ftpCatalog(FTPUtil.ftpCatalog(ftpClient, ""));
    }

    public List<FileCatalogVO> getFileCataLogVO(List<FileCatalogNode> nodeList) {
        List<FileCatalogVO> fileCatalogVOList = new ArrayList<>();
        try {
            List<FileCatalogVO> fileCatalogVOS = null;
            for (FileCatalogNode fileCatalogNode : nodeList) {
                if (fileCatalogNode.getParentName() == null) {
                    FileCatalogVO fileCatalogVO = new FileCatalogVO();
                    fileCatalogVO.setSourceCatalog(fileCatalogNode.getNodeName());
                    fileCatalogVO.setLayer(fileCatalogNode.getLayer());
                    fileCatalogVO.setFileCount(fileCatalogNode.getFileCount());
                    fileCatalogVOS = new ArrayList<>();
                    for (FileCatalogNode fileCatalog : nodeList) {
                        if (fileCatalog.getParentName() != null && fileCatalog.getParentName().equals(fileCatalogVO.getSourceCatalog())) {
                            FileCatalogVO fileCatalogVO1 = new FileCatalogVO();
                            fileCatalogVO1.setSourceCatalog(fileCatalog.getNodeName());
                            fileCatalogVO1.setLayer(fileCatalog.getLayer());
                            fileCatalogVO1.setFileCount(fileCatalogNode.getFileCount());
                            fileCatalogVOS.add(fileCatalogVO1);
                            fileCatalogVO.setFileCatalogVOList(fileCatalogVOS);
                        }
                    }
                    fileCatalogVOList.add(fileCatalogVO);

                } else {
                    if (fileCatalogVOList != null && fileCatalogVOList.size() != 0) {
                        Singleton.getListInstance().clear();

                        fileCatalogVOList = getFileCatalogVOByRecur(fileCatalogVOList, fileCatalogNode);
                    }

                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            Singleton.getListInstance().clear();
        }
        return fileCatalogVOList;
    }

    /**
     * 递归获取目录树形结构
     *
     * @param fileCatalogVOList
     * @param fileCatalogNode
     * @return
     */
    public List<FileCatalogVO> getFileCatalogVOByRecur(List<FileCatalogVO> fileCatalogVOList, FileCatalogNode fileCatalogNode) {
        FileCatalogVO fileCatalogVO1 = null;
        for (FileCatalogVO fileCatalogVO : fileCatalogVOList) {
            if (fileCatalogNode.getParentName().equals(fileCatalogVO.getSourceCatalog())) {
                if (fileCatalogVO.getFileCatalogVOList() != null && fileCatalogVO.getFileCatalogVOList().size() != 0) {
                    for (FileCatalogVO ff : fileCatalogVO.getFileCatalogVOList()) {
                        if (fileCatalogNode.getNodeName().equals(ff.getSourceCatalog())) {
                            return fileCatalogVOList;
                        }
                    }
                    fileCatalogVO1 = new FileCatalogVO();
                    fileCatalogVO1.setLayer(fileCatalogNode.getLayer());
                    fileCatalogVO1.setSourceCatalog(fileCatalogNode.getNodeName());
                    fileCatalogVO1.setFileCount(fileCatalogNode.getFileCount());
                    fileCatalogVO.getFileCatalogVOList().add(fileCatalogVO1);
                } else {
                    fileCatalogVO1 = new FileCatalogVO();
                    fileCatalogVO1.setLayer(fileCatalogNode.getLayer());
                    fileCatalogVO1.setSourceCatalog(fileCatalogNode.getNodeName());
                    if (fileCatalogVO.getFileCatalogVOList() != null && fileCatalogVO.getFileCatalogVOList().size() != 0) {
                        fileCatalogVO.getFileCatalogVOList().add(fileCatalogVO1);
                    } else {
                        List<FileCatalogVO> fileCatalogVOList1 = new ArrayList<>();
                        fileCatalogVOList1.add(fileCatalogVO1);
                        fileCatalogVO.setFileCatalogVOList(fileCatalogVOList1);
                    }

                }
            } else {
                if (fileCatalogVO.getFileCatalogVOList() != null && fileCatalogVO.getFileCatalogVOList().size() != 0) {
                    getFileCatalogVOByRecur(fileCatalogVO.getFileCatalogVOList(), fileCatalogNode);
                }
            }
        }
        return fileCatalogVOList;
    }

    @Override
    public String getFullPathByCatalogId(Integer catalogId) {
        StringBuilder sb = new StringBuilder();
        List<FileCatalogDO> catalogList = fileCatalogDOMapper.getFullPathByCatalogId(catalogId);
        for (FileCatalogDO fileCatalog : catalogList) {
            sb.append(File.separator).append(fileCatalog.getSourceCatalog());
        }
        return sb.toString();
    }

    public int insert(List<String> catalogs) {
        return 1;
    }

    /**
     * 通过id获取陌路信息并生成目录树
     * @param id
     * @return
     */
    public FileCatalogVO queryNodeCatalog(Integer id) {
        List<FileCatalogDO> fileCatalogDOList = fileCatalogDOMapper.getAllChildCatalogById(id);
        FileCatalogVO fileCatalogVO = new FileCatalogVO();
        for (FileCatalogDO fileCatalogDO : fileCatalogDOList) {
            if (fileCatalogVO.getFileCatalogVOList() != null && fileCatalogVO.getFileCatalogVOList().size() != 0) {
                FTPUtil.getFileCatalogVOByRecur(fileCatalogVO,fileCatalogDOList);
//                for (FileCatalogVO fileCatalogVO2 : fileCatalogVO.getFileCatalogVOList()) {
//                    for (FileCatalogDO fileCatalogDO1 : fileCatalogDOList) {
//                        if (fileCatalogDO1.getParentId() == fileCatalogVO2.getId()) {
//                            FileCatalogVO fileCatalog = new FileCatalogVO();
//                            fileCatalog.setSourceCatalog(fileCatalogDO1.getSourceCatalog());
//                            fileCatalog.setId(fileCatalogDO1.getId());
//                            fileCatalog.setLayer(fileCatalogDO1.getLayer());
//                            if (fileCatalogVO2.getFileCatalogVOList() != null) {
//                                fileCatalogVO2.getFileCatalogVOList().add(fileCatalog);
//                            } else {
//                                List<FileCatalogVO> fileCatalogVOList1 = new ArrayList<>();
//                                fileCatalogVOList1.add(fileCatalog);
//                                fileCatalogVO2.setFileCatalogVOList(fileCatalogVOList1);
//                            }
//                        }
//                    }
//                }
            } else {
                fileCatalogVO.setLayer(fileCatalogDO.getLayer());
                fileCatalogVO.setSourceCatalog(fileCatalogDO.getSourceCatalog());
                fileCatalogVO.setId(fileCatalogDO.getId());
                fileCatalogVO.setFileCount(fileCatalogDO.getFileCount());
                for (FileCatalogDO fileCatalogDO1 : fileCatalogDOList) {
                    if (fileCatalogDO.getId() == fileCatalogDO1.getParentId()) {
                        FileCatalogVO fileCatalogVO1 = new FileCatalogVO();
                        fileCatalogVO1.setLayer(fileCatalogDO1.getLayer());
                        fileCatalogVO1.setId(fileCatalogDO1.getId());
                        fileCatalogVO1.setSourceCatalog(fileCatalogDO1.getSourceCatalog());
                        if (fileCatalogVO.getFileCatalogVOList() != null) {
                            fileCatalogVO.getFileCatalogVOList().add(fileCatalogVO1);
                        } else {
                            List<FileCatalogVO> fileCatalogVOList = new ArrayList<>();
                            fileCatalogVOList.add(fileCatalogVO1);
                            fileCatalogVO.setFileCatalogVOList(fileCatalogVOList);
                        }
                    }
                }
            }
        }
        return fileCatalogVO;
    }

}
