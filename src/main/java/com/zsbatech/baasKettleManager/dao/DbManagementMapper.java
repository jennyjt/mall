package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DbManagementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbManagementMapper {
    int countByExample(DbManagementExample example);

    int deleteByExample(DbManagementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbManagement record);

    int insertSelective(DbManagement record);

    List<DbManagement> selectByExample(DbManagementExample example);

    DbManagement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbManagement record, @Param("example") DbManagementExample example);

    int updateByExample(@Param("record") DbManagement record, @Param("example") DbManagementExample example);

    int updateByPrimaryKeySelective(DbManagement record);

    int updateByPrimaryKey(DbManagement record);
    List<DbManagement> getDbManagentsByParam(DbManagement param);

    int increaseUseCount(Integer id);

    int decreaseUseCount(Integer id);
}