package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLogMapper {
    int countByExample(JobLogExample example);

    int deleteByExample(JobLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobLog record);

    int insertSelective(JobLog record);

    List<JobLog> selectByExampleWithBLOBs(JobLogExample example);

    List<JobLog> selectByExample(JobLogExample example);

    JobLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobLog record, @Param("example") JobLogExample example);

    int updateByExampleWithBLOBs(@Param("record") JobLog record, @Param("example") JobLogExample example);

    int updateByExample(@Param("record") JobLog record, @Param("example") JobLogExample example);

    int updateByPrimaryKeySelective(JobLog record);

    int updateByPrimaryKeyWithBLOBs(JobLog record);

    int updateByPrimaryKey(JobLog record);
}