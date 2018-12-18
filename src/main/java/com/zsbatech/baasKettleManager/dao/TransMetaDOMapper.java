package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.TransMetaDO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TransMetaDOMapper {

    int insert(TransMetaDO transMetaDO);

    TransMetaDO selectTransMetaVO(String name);

    TransMetaDO selectTransMetaVOById(Integer transMetaId);
}
