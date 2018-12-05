package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.JobHopMetaVO;
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
public interface JobHopMetaVOMapper {

    int insert(JobHopMetaVO jobHopMetaVO);

    int insertBatch(List<JobHopMetaVO> jobHopMetaVOList);

    JobHopMetaVO selectJobHopMetaVOById(@Param("fromStepId") int fromStepId, @Param("toStepId")int toStepId);

}
