package com.zsbatech.baasKettleManager.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/5
 */
public interface JobManageService {

    int executeJob(String jobFile);

    boolean stop(String jobName);

    boolean stopJobs(List<String> jobNames);
}
