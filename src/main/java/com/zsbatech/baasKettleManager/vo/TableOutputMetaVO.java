package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
public class TableOutputMetaVO {
    private int id;

    private String stepName;

    private String dbConnectionName;

    private Date createTime;

    private Date updateTime;

    private int transMetaId;

    private String targetTable;

    private int isBatchInsert;

    private int entryId;

    private int dBManageMentId;

    public void setId(int id) {
        this.id = id;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setDbConnectionName(String dbConnectionName) {
        this.dbConnectionName = dbConnectionName;
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

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public void setIsBatchInsert(int isBatchInsert) {
        this.isBatchInsert = isBatchInsert;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setdBManageMentId(int dBManageMentId) {
        this.dBManageMentId = dBManageMentId;
    }

    public int getId() {
        return id;
    }

    public String getStepName() {
        return stepName;
    }

    public String getDbConnectionName() {
        return dbConnectionName;
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

    public String getTargetTable() {
        return targetTable;
    }

    public int getIsBatchInsert() {
        return isBatchInsert;
    }

    public int getEntryId() {
        return entryId;
    }

    public int getdBManageMentId() {
        return dBManageMentId;
    }
}
