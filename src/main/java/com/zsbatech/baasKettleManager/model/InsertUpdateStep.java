package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class InsertUpdateStep {
    private Integer id;

    private Integer transMetaId;

    private Integer dbManagementId;

    private String stepName;

    private String updateLookup;

    private Date timeStampColumn;

    private Date updateTime;

    private Date createTime;

    private String keyLookup;

    private String targetTable;

    private Byte isUpdate;

    private String keyStream;

    private String keyStream2;

    private String keyCondition;

    private String updateStream;

    private String updateOrNot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransMetaId() {
        return transMetaId;
    }

    public void setTransMetaId(Integer transMetaId) {
        this.transMetaId = transMetaId;
    }

    public Integer getDbManagementId() {
        return dbManagementId;
    }

    public void setDbManagementId(Integer dbManagementId) {
        this.dbManagementId = dbManagementId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName == null ? null : stepName.trim();
    }

    public String getUpdateLookup() {
        return updateLookup;
    }

    public void setUpdateLookup(String updateLookup) {
        this.updateLookup = updateLookup == null ? null : updateLookup.trim();
    }

    public Date getTimeStampColumn() {
        return timeStampColumn;
    }

    public void setTimeStampColumn(Date timeStampColumn) {
        this.timeStampColumn = timeStampColumn;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeyLookup() {
        return keyLookup;
    }

    public void setKeyLookup(String keyLookup) {
        this.keyLookup = keyLookup == null ? null : keyLookup.trim();
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable == null ? null : targetTable.trim();
    }

    public Byte getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Byte isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getKeyStream() {
        return keyStream;
    }

    public void setKeyStream(String keyStream) {
        this.keyStream = keyStream == null ? null : keyStream.trim();
    }

    public String getKeyStream2() {
        return keyStream2;
    }

    public void setKeyStream2(String keyStream2) {
        this.keyStream2 = keyStream2 == null ? null : keyStream2.trim();
    }

    public String getKeyCondition() {
        return keyCondition;
    }

    public void setKeyCondition(String keyCondition) {
        this.keyCondition = keyCondition == null ? null : keyCondition.trim();
    }

    public String getUpdateStream() {
        return updateStream;
    }

    public void setUpdateStream(String updateStream) {
        this.updateStream = updateStream == null ? null : updateStream.trim();
    }

    public String getUpdateOrNot() {
        return updateOrNot;
    }

    public void setUpdateOrNot(String updateOrNot) {
        this.updateOrNot = updateOrNot == null ? null : updateOrNot.trim();
    }
}