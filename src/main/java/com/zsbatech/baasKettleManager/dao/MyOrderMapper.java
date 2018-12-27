package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.MyOrder;
import com.zsbatech.baasKettleManager.model.MyOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyOrderMapper {
    int countByExample(MyOrderExample example);

    int deleteByExample(MyOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyOrder record);

    int insertSelective(MyOrder record);

    List<MyOrder> selectByExample(MyOrderExample example);

    MyOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyOrder record, @Param("example") MyOrderExample example);

    int updateByExample(@Param("record") MyOrder record, @Param("example") MyOrderExample example);

    int updateByPrimaryKeySelective(MyOrder record);

    int updateByPrimaryKey(MyOrder record);
}