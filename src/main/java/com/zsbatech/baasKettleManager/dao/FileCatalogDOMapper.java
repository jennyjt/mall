package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FileCatalogDO;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public interface FileCatalogDOMapper {

    int insert(FileCatalogDO fileContentDO);

    FileCatalogDO select(FileCatalogDO fileCatalogDO);

    List<FileCatalogDO> queryCatalogById(List<Integer> idList);

    List<FileCatalogDO> getFullPathByCatalogId(Integer id);

    List<FileCatalogDO> getAllChildCatalogById(Integer id);
}
