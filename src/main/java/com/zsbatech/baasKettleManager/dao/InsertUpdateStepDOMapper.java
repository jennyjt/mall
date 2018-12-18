package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.InsertUpdateStepDO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/2
 */
@Repository
public interface InsertUpdateStepDOMapper {
    int insert(InsertUpdateStepDO tableOutputMetaDO);

    InsertUpdateStepDO selectByName(String stepName);

    InsertUpdateStepDO selectByTransMetaId(int transMetaId);
}

