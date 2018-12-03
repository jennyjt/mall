package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/2
 */
public class InsertUpdateStepVO {

    private int id;

    private int transMetaId;

    private int dBManageMentId;

    private String stepName;

    private String updateLookUp;

    private Date timeStampColumn;

    private String excSql;

    private Date createTime;

    private Date updateTime;

    private String targetTable;

    private short isUpdate;

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

    public String getUpdateLookUp() {
        return updateLookUp;
    }

    public Date getTimeStampColumn() {
        return timeStampColumn;
    }

    public String getExcSql() {
        return excSql;
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

    public void setUpdateLookUp(String updateLookUp) {
        this.updateLookUp = updateLookUp;
    }

    public void setTimeStampColumn(Date timeStampColumn) {
        this.timeStampColumn = timeStampColumn;
    }

    public void setExcSql(String excSql) {
        this.excSql = excSql;
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
}
