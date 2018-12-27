package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.OrderUser;
import com.zsbatech.baasKettleManager.model.OrderUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderUserMapper {
    int countByExample(OrderUserExample example);

    int deleteByExample(OrderUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderUser record);

    int insertSelective(OrderUser record);

    List<OrderUser> selectByExample(OrderUserExample example);

    OrderUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderUser record, @Param("example") OrderUserExample example);

    int updateByExample(@Param("record") OrderUser record, @Param("example") OrderUserExample example);

    int updateByPrimaryKeySelective(OrderUser record);

    int updateByPrimaryKey(OrderUser record);
}