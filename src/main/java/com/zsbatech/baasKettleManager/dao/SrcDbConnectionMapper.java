package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.SrcDbConnection;

public interface SrcDbConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SrcDbConnection record);

    int insertSelective(SrcDbConnection record);

    SrcDbConnection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SrcDbConnection record);

    int updateByPrimaryKey(SrcDbConnection record);
}