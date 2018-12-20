package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.InsertUpdateStep;
import com.zsbatech.baasKettleManager.model.InsertUpdateStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InsertUpdateStepMapper {
    int countByExample(InsertUpdateStepExample example);

    int deleteByExample(InsertUpdateStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InsertUpdateStep record);

    int insertSelective(InsertUpdateStep record);

    List<InsertUpdateStep> selectByExample(InsertUpdateStepExample example);

    InsertUpdateStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InsertUpdateStep record, @Param("example") InsertUpdateStepExample example);

    int updateByExample(@Param("record") InsertUpdateStep record, @Param("example") InsertUpdateStepExample example);

    int updateByPrimaryKeySelective(InsertUpdateStep record);

    int updateByPrimaryKey(InsertUpdateStep record);
}