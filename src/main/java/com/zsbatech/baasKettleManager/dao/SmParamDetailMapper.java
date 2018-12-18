package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.SmParamDetail;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SmParamDetailMapper {
    int deleteByPrimaryKey(Long paramId);

    int insert(SmParamDetail record);

    int insertSelective(SmParamDetail record);

    SmParamDetail selectByPrimaryKey(Long paramId);

    int updateByPrimaryKeySelective(SmParamDetail record);

    int updateByPrimaryKey(SmParamDetail record);

    List<SmParamDetail> selectBySelective(SmParamDetail smParamDetail);

    Date getDbDate();
}