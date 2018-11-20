package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.SrcDbConnection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SrcDbConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SrcDbConnection record);

    int insertSrcDbConnList(List<SrcDbConnection> list);

    int insertSelective(SrcDbConnection record);

    SrcDbConnection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SrcDbConnection record);

    int updateByPrimaryKey(SrcDbConnection record);

    List<SrcDbConnection> getConnectionList(@Param("jobId") Integer jobId, @Param("stepId") Integer stepId);
}