package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class UpdownloadLog {
    private Integer id;

    private Date createTime;

    private String createUser;

    private Integer fileId;

    private Byte operation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Byte getOperation() {
        return operation;
    }

    public void setOperation(Byte operation) {
        this.operation = operation;
    }
}