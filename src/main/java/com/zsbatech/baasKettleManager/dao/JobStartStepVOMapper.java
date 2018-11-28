package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Repository
public interface JobStartStepVOMapper {

    int insert(JobStartStepVO startStepVO);

    JobStartStepVO selectJobStartStepVO(Integer jobMetaId);
}
