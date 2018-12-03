package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.JobLog;

import java.util.Date;
import java.util.List;

/**
 * 任务执行日志管理接口
 * JobLogService
 */
public interface JobLogService {
    /**
     * 日志查询
     * @param jobId
     * @param userId
     * @param jobDateFrom
     * @param jobDateEnd
     * @return
     */
    List<JobLog> getJobLogInfo(Integer jobId, String userId, String jobDateFrom, String jobDateEnd);

    /**
     * 插入日志信息
     * @param jobLog
     * @return
     */
    boolean addJobLog(JobLog jobLog);

    /**
     * 修改日志信息
     * @param jobLog
     * @return
     */
    boolean updateJobLog(JobLog jobLog);

    /**
     * 修改任务执行日志结束时间
     * @param logId
     * @param endTime
     * @return
     */
    boolean updateJobLogEndTime(Integer logId, Date endTime);
}
