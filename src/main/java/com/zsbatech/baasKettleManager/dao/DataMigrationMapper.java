package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataMigrationMapper {
    int countByExample(DataMigrationExample example);

    int deleteByExample(DataMigrationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataMigration record);

    int insertSelective(DataMigration record);

    List<DataMigration> selectByExample(DataMigrationExample example);

    DataMigration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataMigration record, @Param("example") DataMigrationExample example);

    int updateByExample(@Param("record") DataMigration record, @Param("example") DataMigrationExample example);

    int updateByPrimaryKeySelective(DataMigration record);

    int updateByPrimaryKey(DataMigration record);
}