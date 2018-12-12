package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FileCatalogVO;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public interface FileCatalogVOMapper {

    int insert(FileCatalogVO fileContentVO);

    FileCatalogVO select(FileCatalogVO fileCatalogVO);

    List<FileCatalogVO> queryCatalogById(List<Integer> idList);

    List<FileCatalogVO> getFullPathByCatalogId(Integer id);
}
