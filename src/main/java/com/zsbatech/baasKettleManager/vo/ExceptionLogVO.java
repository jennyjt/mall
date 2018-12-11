package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/10
 */
public class ExceptionLogVO {

    private int id;

    private String exceptionInfo;

    private Date occurTime;

    private String jobName;

    public int getId() {
        return id;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
