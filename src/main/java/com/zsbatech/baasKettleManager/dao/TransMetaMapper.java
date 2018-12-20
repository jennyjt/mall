package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.TransMeta;
import com.zsbatech.baasKettleManager.model.TransMetaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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