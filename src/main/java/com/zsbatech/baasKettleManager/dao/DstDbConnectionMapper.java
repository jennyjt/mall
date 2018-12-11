package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.model.DstDbConnectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DstDbConnectionMapper {
    int countByExample(DstDbConnectionExample example);

    int deleteByExample(DstDbConnectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DstDbConnection record);

    int insertSelective(DstDbConnection record);

    List<DstDbConnection> selectByExample(DstDbConnectionExample example);

    DstDbConnection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DstDbConnection record, @Param("example") DstDbConnectionExample example);

    int updateByExample(@Param("record") DstDbConnection record, @Param("example") DstDbConnectionExample example);

    int updateByPrimaryKeySelective(DstDbConnection record);

    int updateByPrimaryKey(DstDbConnection record);
}