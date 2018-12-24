package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FTPSyncSetp;
import com.zsbatech.baasKettleManager.vo.JobInfo;

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


    boolean modifyJob(FTPSyncSetp ftpSyncSetp);

    JobInfo queryJobInfo(String jobName);

    boolean removeJob(String jobName);

    List<JobInfo> queryJobInfoByPage(int page,int count);

}
