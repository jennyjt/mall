package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.JobLogMapper;
import com.zsbatech.baasKettleManager.model.JobLog;
import com.zsbatech.baasKettleManager.model.JobLogExample;
import com.zsbatech.baasKettleManager.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 任务执行日志管理接口实现类
 */
@Service
public class JobLogServiceImpl implements JobLogService {
    @Autowired
    private JobLogMapper jobLogMapper;

    @Override
    public List<JobLog> getJobLogInfo(Integer jobId, String userId, String jobDateFrom, String jobDateEnd) {
        JobLogExample jobLogExample = new JobLogExample();
        JobLogExample.Criteria criteria = jobLogExample.createCriteria();
        if(jobId != null){
            criteria.andIdEqualTo(jobId);
        }
        if(userId != null){
            criteria.andExecutingUserEqualTo(userId);
        }

        if(jobDateFrom != null){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = dateFormat.parse(jobDateFrom);
                criteria.andStartTimeGreaterThan(fromDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(jobDateEnd != null){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date endDate = dateFormat.parse(jobDateEnd);
                criteria.andEndTimeLessThan(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<JobLog> jobLogs = jobLogMapper.selectByExample(jobLogExample);
        return jobLogs;
    }

    @Override
    public boolean addJobLog(JobLog jobLog) {
        int result = jobLogMapper.insert(jobLog);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateJobLog(JobLog jobLog) {
        int result = jobLogMapper.updateByPrimaryKeySelective(jobLog);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateJobLogEndTime(Integer logId, Date endTime) {
        JobLog jobLog = new JobLog();
        jobLog.setId(logId);
        jobLog.setEndTime(endTime);
        int result = jobLogMapper.updateByPrimaryKeySelective(jobLog);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
