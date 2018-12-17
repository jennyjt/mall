package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.JobMetaDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
@Repository
public interface JobMetaDOMapper {

    int insert(JobMetaDO jobMetaDO);

    JobMetaDO selectByJobName(String jobName);

    JobMetaDO selectById(List<Integer> id);

    int updateByJobName(JobMetaDO jobMetaDO);

}
