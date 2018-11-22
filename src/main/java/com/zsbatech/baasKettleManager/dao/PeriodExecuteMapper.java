package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodExecuteMapper {
    int countByExample(PeriodExecuteExample example);

    int deleteByExample(PeriodExecuteExample example);

    int insert(PeriodExecute record);

    int insertSelective(PeriodExecute record);

    List<PeriodExecute> selectByExample(PeriodExecuteExample example);

    int updateByExampleSelective(@Param("record") PeriodExecute record, @Param("example") PeriodExecuteExample example);

    int updateByExample(@Param("record") PeriodExecute record, @Param("example") PeriodExecuteExample example);
}