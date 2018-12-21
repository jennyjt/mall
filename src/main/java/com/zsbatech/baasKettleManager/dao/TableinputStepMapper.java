package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.TableinputStep;
import com.zsbatech.baasKettleManager.model.TableinputStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TableinputStepMapper {
    int countByExample(TableinputStepExample example);

    int deleteByExample(TableinputStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TableinputStep record);

    int insertSelective(TableinputStep record);

    List<TableinputStep> selectByExample(TableinputStepExample example);

    TableinputStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TableinputStep record, @Param("example") TableinputStepExample example);

    int updateByExample(@Param("record") TableinputStep record, @Param("example") TableinputStepExample example);

    int updateByPrimaryKeySelective(TableinputStep record);

    int updateByPrimaryKey(TableinputStep record);
}