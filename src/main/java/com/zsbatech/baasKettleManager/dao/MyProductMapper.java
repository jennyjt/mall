package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.MyProduct;
import com.zsbatech.baasKettleManager.model.MyProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyProductMapper {
    int countByExample(MyProductExample example);

    int deleteByExample(MyProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyProduct record);

    int insertSelective(MyProduct record);

    List<MyProduct> selectByExampleWithBLOBs(MyProductExample example);

    List<MyProduct> selectByExample(MyProductExample example);

    MyProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyProduct record, @Param("example") MyProductExample example);

    int updateByExampleWithBLOBs(@Param("record") MyProduct record, @Param("example") MyProductExample example);

    int updateByExample(@Param("record") MyProduct record, @Param("example") MyProductExample example);

    int updateByPrimaryKeySelective(MyProduct record);

    int updateByPrimaryKeyWithBLOBs(MyProduct record);

    int updateByPrimaryKey(MyProduct record);
}