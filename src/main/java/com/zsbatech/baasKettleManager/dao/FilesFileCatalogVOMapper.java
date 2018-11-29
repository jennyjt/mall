package com.zsbatech.baasKettleManager.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/29
 */
public interface FilesFileCatalogVOMapper {

    int insertBatch(List<FilesFileCatalogVOMapper> filesFileCatalogVOList);
}
