package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransHopMapper {
    int countByExample(TransHopExample example);

    int deleteByExample(TransHopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransHop record);

    int insertSelective(TransHop record);

    List<TransHop> selectByExample(TransHopExample example);

    TransHop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransHop record, @Param("example") TransHopExample example);

    int updateByExample(@Param("record") TransHop record, @Param("example") TransHopExample example);

    int updateByPrimaryKeySelective(TransHop record);

    int updateByPrimaryKey(TransHop record);
}