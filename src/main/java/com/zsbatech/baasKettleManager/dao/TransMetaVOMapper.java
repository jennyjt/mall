package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.TransMetaVO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TransMetaVOMapper {

    int insert(TransMetaVO transMetaVO);

    TransMetaVO selectTransMetaVO(String name);

    TransMetaVO selectTransMetaVOById(Integer transMetaId);
}
