package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DbManagement;

public interface DbManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbManagement record);

    int insertSelective(DbManagement record);

    DbManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbManagement record);

    int updateByPrimaryKey(DbManagement record);
}