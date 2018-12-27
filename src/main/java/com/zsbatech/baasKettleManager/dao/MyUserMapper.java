package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.MyUser;
import com.zsbatech.baasKettleManager.model.MyUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper {
    int countByExample(MyUserExample example);

    int deleteByExample(MyUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    List<MyUser> selectByExample(MyUserExample example);

    MyUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyUser record, @Param("example") MyUserExample example);

    int updateByExample(@Param("record") MyUser record, @Param("example") MyUserExample example);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);
}