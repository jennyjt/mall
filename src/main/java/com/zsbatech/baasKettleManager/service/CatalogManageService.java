package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.FileCatalogDO;
import com.zsbatech.baasKettleManager.model.FilesDO;
import com.zsbatech.baasKettleManager.vo.FileCatalogVO;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public interface CatalogManageService {

    FileCatalogVO queryNodeCatalog(Integer id);

    int insert(List<String> catalogs);

    List<FileCatalogVO> getFtpCatalog(String nickName);

    Map<String, List<String>> createCatalogs(List<String> files);

    Map<String, List<String>> deleteFiles(List<String> files);

    Map<String, List<String>> deleteCatalogs(List<String> fileCatalogs);

    List<FilesDO> queryFiles(String fileCatalog, String code);

    List<String> queryCataLog(String code,String fileName);

    int saveFiles(List<String> files);

    /**
     * 保存单个文件信息
     * @param filesDO
     * @return
     */
    boolean saveFile(FilesDO filesDO);

    /**
     * 根据文件id查询单个文件信息
     * @param fileId
     * @return
     */
    FilesDO getFileInfoById(Integer fileId);

    /**
     * 根据文件id查询单个文件信息
     * @param fileIds
     * @return
     */
    List<FilesDO> getFileInfosByIds(List<Integer> fileIds);

    /**
     * 通过目录id获取完整的路径
     * @param catalogId
     * @return
     */
    String getFullPathByCatalogId(Integer catalogId);
}
