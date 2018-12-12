package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FilesVO;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public interface CatalogManageService {

    Map<String, List<String>> createCatalogs(List<String> files);

    Map<String, List<String>> deleteFiles(List<String> files);

    Map<String, List<String>> deleteCatalogs(List<String> fileCatalogs);

    List<FilesVO> queryFiles(String fileCatalog,String code);

    List<String> queryCataLog(String code,String fileName);

    int saveFiles(List<String> files);

    /**
     * 保存单个文件信息
     * @param filesVO
     * @return
     */
    boolean saveFile(FilesVO filesVO);

    /**
     * 根据文件id查询单个文件信息
     * @param fileId
     * @return
     */
    FilesVO getFileInfoById(Integer fileId);

    /**
     * 根据文件id查询单个文件信息
     * @param fileIds
     * @return
     */
    List<FilesVO> getFileInfosByIds(List<Integer> fileIds);

    /**
     * 通过目录id获取完整的路径
     * @param catalogId
     * @return
     */
    String getFullPathByCatalogId(Integer catalogId);
}
