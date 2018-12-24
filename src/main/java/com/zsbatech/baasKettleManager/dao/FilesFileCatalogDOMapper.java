package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.FilesFileCatalogDO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/29
 */
public interface FilesFileCatalogDOMapper {

    int insertBatch(List<FilesFileCatalogDO> filesFileCatalogDOList);

    int insert(FilesFileCatalogDO filesFileCatalogDO);

    List<FilesFileCatalogDO> queryByFileId(Integer fileId);

    List<FilesFileCatalogDO> queryByCatalogId(Integer fileCatalogId);
}
