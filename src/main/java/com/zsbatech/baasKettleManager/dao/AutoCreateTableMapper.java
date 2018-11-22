package com.zsbatech.baasKettleManager.dao;


import com.zsbatech.baasKettleManager.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoCreateTableMapper {
    int countByExample(AutoCreateTableExample example);

    int deleteByExample(AutoCreateTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutoCreateTable record);

    int insertSelective(AutoCreateTable record);

    List<AutoCreateTable> selectByExample(AutoCreateTableExample example);

    AutoCreateTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutoCreateTable record, @Param("example") AutoCreateTableExample example);

    int updateByExample(@Param("record") AutoCreateTable record, @Param("example") AutoCreateTableExample example);

    int updateByPrimaryKeySelective(AutoCreateTable record);

    int updateByPrimaryKey(AutoCreateTable record);
}