package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.JobMetaVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Repository
public interface JobMetaVOMapper {

    int insert(JobMetaVO jobMetaVO);

    JobMetaVO selectByJobName(String jobName);

    JobMetaVO selectById(List<Integer> id);

}
