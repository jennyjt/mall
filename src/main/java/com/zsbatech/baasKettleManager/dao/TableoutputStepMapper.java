package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableoutputStepMapper {
    int countByExample(TableoutputStepExample example);

    int deleteByExample(TableoutputStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TableoutputStep record);

    int insertSelective(TableoutputStep record);

    List<TableoutputStep> selectByExample(TableoutputStepExample example);

    TableoutputStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TableoutputStep record, @Param("example") TableoutputStepExample example);

    int updateByExample(@Param("record") TableoutputStep record, @Param("example") TableoutputStepExample example);

    int updateByPrimaryKeySelective(TableoutputStep record);

    int updateByPrimaryKey(TableoutputStep record);
}