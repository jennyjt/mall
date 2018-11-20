package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DstDbConnection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DstDbConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DstDbConnection record);

    int insertSelective(DstDbConnection record);

    DstDbConnection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DstDbConnection record);

    int updateByPrimaryKey(DstDbConnection record);

    List<DstDbConnection> getConnectionList(@Param("jobId") Integer jobId, @Param("stepId") Integer stepId);
}