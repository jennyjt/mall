package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.TransHopMetaVO;
import com.zsbatech.baasKettleManager.vo.TransMetaVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
@Repository
public interface TransHopMetaVOMapper {

    int insertHopBatch(List<TransHopMetaVO> transHopMetaVOList);

}
