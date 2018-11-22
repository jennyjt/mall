package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMetaMapper {
    int countByExample(JobMetaExample example);

    int deleteByExample(JobMetaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobMeta record);

    int insertSelective(JobMeta record);

    List<JobMeta> selectByExample(JobMetaExample example);

    JobMeta selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobMeta record, @Param("example") JobMetaExample example);

    int updateByExample(@Param("record") JobMeta record, @Param("example") JobMetaExample example);

    int updateByPrimaryKeySelective(JobMeta record);

    int updateByPrimaryKey(JobMeta record);
}