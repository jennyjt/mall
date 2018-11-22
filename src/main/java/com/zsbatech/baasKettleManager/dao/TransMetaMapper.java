package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransMetaMapper {
    int countByExample(TransMetaExample example);

    int deleteByExample(TransMetaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransMeta record);

    int insertSelective(TransMeta record);

    List<TransMeta> selectByExample(TransMetaExample example);

    TransMeta selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransMeta record, @Param("example") TransMetaExample example);

    int updateByExample(@Param("record") TransMeta record, @Param("example") TransMetaExample example);

    int updateByPrimaryKeySelective(TransMeta record);

    int updateByPrimaryKey(TransMeta record);
}