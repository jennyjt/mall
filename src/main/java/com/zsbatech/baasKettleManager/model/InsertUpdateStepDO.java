package com.zsbatech.baasKettleManager.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/2
 */
public class InsertUpdateStepDO {

    private int id;

    private int transMetaId;

    private int dBManageMentId;

    private String stepName;

    private String updateLookup;

    private Date timeStampColumn;

    private String keyLookup;

    private Date createTime;

    private Date updateTime;

    private String targetTable;

    private short isUpdate;

    private String keyStream;

    private String keyStream2;

    private String keyCondition;

    private String updateStream;

    private String updateOrNot;

    public int getId() {
        return id;
    }

    public int getTransMetaId() {
        return transMetaId;
    }

    public int getdBManageMentId() {
        return dBManageMentId;
    }

    public String getStepName() {
        return stepName;
    }

    public String getUpdateLookup() {
        return updateLookup;
    }

    public Date getTimeStampColumn() {
        return timeStampColumn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public short getIsUpdate() {
        return isUpdate;
    }

    public String getKeyLookup() {
        return keyLookup;
    }

    public String getKeyStream() {
        return keyStream;
    }

    public String getKeyStream2() {
        return keyStream2;
    }

    public String getKeyCondition() {
        return keyCondition;
    }

    public String getUpdateStream() {
        return updateStream;
    }

    public String getUpdateOrNot() {
        return updateOrNot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTransMetaId(int transMetaId) {
        this.transMetaId = transMetaId;
    }

    public void setdBManageMentId(int dBManageMentId) {
        this.dBManageMentId = dBManageMentId;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setUpdateLookup(String updateLookup) {
        this.updateLookup = updateLookup;
    }

    public void setTimeStampColumn(Date timeStampColumn) {
        this.timeStampColumn = timeStampColumn;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public void setIsUpdate(short isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setKeyLookup(String keyLookup) {
        this.keyLookup = keyLookup;
    }

    public void setKeyStream(String keyStream) {
        this.keyStream = keyStream;
    }

    public void setKeyStream2(String keyStream2) {
        this.keyStream2 = keyStream2;
    }

    public void setKeyCondition(String keyCondition) {
        this.keyCondition = keyCondition;
    }

    public void setUpdateStream(String updateStream) {
        this.updateStream = updateStream;
    }

    public void setUpdateOrNot(String updateOrNot) {
        this.updateOrNot = updateOrNot;
    }
}
