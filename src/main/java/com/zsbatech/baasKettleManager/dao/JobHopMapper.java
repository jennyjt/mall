package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHopMapper {
    int countByExample(JobHopExample example);

    int deleteByExample(JobHopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobHop record);

    int insertSelective(JobHop record);

    List<JobHop> selectByExample(JobHopExample example);

    JobHop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobHop record, @Param("example") JobHopExample example);

    int updateByExample(@Param("record") JobHop record, @Param("example") JobHopExample example);

    int updateByPrimaryKeySelective(JobHop record);

    int updateByPrimaryKey(JobHop record);
}