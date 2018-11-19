package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DbConnection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbConnection record);

    int insertSelective(DbConnection record);

    DbConnection selectByPrimaryKey(Integer id);

    List<DbConnection> getConnectionList(@Param("jobId") Integer jobId, @Param("stepId") Integer stepId);

    int updateByPrimaryKeySelective(DbConnection record);

    int updateByPrimaryKey(DbConnection record);
}