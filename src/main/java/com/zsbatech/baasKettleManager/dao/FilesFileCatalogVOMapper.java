package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.vo.FilesFileCatalogVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/29
 */
public interface FilesFileCatalogVOMapper {

    int insertBatch(List<FilesFileCatalogVO> filesFileCatalogVOList);

     int insert(FilesFileCatalogVO filesFileCatalogVO);
}
