package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.InsertUpdateStepVO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/2
 */
@Repository
public interface InsertUpdateStepVOMapper {
    int insert(InsertUpdateStepVO tableOutputMetaVO);

    InsertUpdateStepVO selectByName(String stepName);

    InsertUpdateStepVO selectByTransMetaId(int transMetaId);
}
