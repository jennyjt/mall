package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.JobHopMetaDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Repository
public interface JobHopMetaDOMapper {

    int insert(JobHopMetaDO jobHopMetaDO);

    int insertBatch(List<JobHopMetaDO> jobHopMetaDOList);

    JobHopMetaDO selectJobHopMetaVOById(@Param("fromStepId") int fromStepId, @Param("toStepId")int toStepId);

}
