package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public class JobMetaVO {

    private int id;

    private String jobName;

    private String fileName;

    private Date createTime;

    private  Date updateTime;

    private int transMetaId;

    private short executeStatus;

    public int getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public int getTransMetaId() {
        return transMetaId;
    }

    public short getExecuteStatus() {
        return executeStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setTransMetaId(int transMetaId) {
        this.transMetaId = transMetaId;
    }

    public void setExecuteStatus(short executeStatus) {
        this.executeStatus = executeStatus;
    }
}
