package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.TransHopMetaDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TransHopMetaDOMapper {

    int insertHopBatch(List<TransHopMetaDO> transHopMetaDOList);

}

