package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class JobHop {
    private Integer id;

    private Integer fromStepId;

    private Integer toStepId;

    private Byte hopSequence;

    private Byte condition;

    private Date createtime;

    private Date updatetime;

    private Integer jobMetaId;

    public JobHop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromStepId() {
        return fromStepId;
    }

    public void setFromStepId(Integer fromStepId) {
        this.fromStepId = fromStepId;
    }

    public Integer getToStepId() {
        return toStepId;
    }

    public void setToStepId(Integer toStepId) {
        this.toStepId = toStepId;
    }

    public Byte getHopSequence() {
        return hopSequence;
    }

    public void setHopSequence(Byte hopSequence) {
        this.hopSequence = hopSequence;
    }

    public Byte getCondition() {
        return condition;
    }

    public void setCondition(Byte condition) {
        this.condition = condition;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getJobMetaId() {
        return jobMetaId;
    }

    public void setJobMetaId(Integer jobMetaId) {
        this.jobMetaId = jobMetaId;
    }
}