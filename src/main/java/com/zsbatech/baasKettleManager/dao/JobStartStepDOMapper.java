package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.JobStartStepDO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Repository
public interface JobStartStepDOMapper {

    int insert(JobStartStepDO startStepDO);

    JobStartStepDO selectJobStartStepVO(Integer jobMetaId);

    JobStartStepDO selectByName(String name);
}
