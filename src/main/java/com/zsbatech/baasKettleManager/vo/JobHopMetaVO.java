package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public class JobHopMetaVO {

    private int id;

    private int fromStepId;

    private int toStepId;

    private short hopSequence;

    private short condition;

    private Date createTime;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public int getFromStepId() {
        return fromStepId;
    }

    public int getToStepId() {
        return toStepId;
    }

    public short getHopSequence() {
        return hopSequence;
    }

    public short getCondition() {
        return condition;
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

    public void setFromStepId(int fromStepId) {
        this.fromStepId = fromStepId;
    }

    public void setToStepId(int toStepId) {
        this.toStepId = toStepId;
    }

    public void setHopSequence(short hopSequence) {
        this.hopSequence = hopSequence;
    }

    public void setCondition(short condition) {
        this.condition = condition;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
