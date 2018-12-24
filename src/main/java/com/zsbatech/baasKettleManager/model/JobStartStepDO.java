package com.zsbatech.baasKettleManager.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public class JobStartStepDO {

    private int id;

    private String stepName;

    private short timingType;

    private short isRepeat;

    private int timingTime;

    private int jobMetaId;

    private Date createTime;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public String getStepName() {
        return stepName;
    }

    public short getTimingType() {
        return timingType;
    }

    public short getIsRepeat() {
        return isRepeat;
    }

    public int getTimingTime() {
        return timingTime;
    }

    public int getJobMetaId() {
        return jobMetaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }


    public void setIsRepeat(short isRepeat) {
        this.isRepeat = isRepeat;
    }

    public void setTimingTime(int timingTime) {
        this.timingTime = timingTime;
    }

    public void setJobMetaId(int jobMetaId) {
        this.jobMetaId = jobMetaId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setTimingType(short timingType) {
        this.timingType = timingType;
    }
}
