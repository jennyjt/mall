package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DstDbConnection;

public interface DstDbConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DstDbConnection record);

    int insertSelective(DstDbConnection record);

    DstDbConnection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DstDbConnection record);

    int updateByPrimaryKey(DstDbConnection record);
}