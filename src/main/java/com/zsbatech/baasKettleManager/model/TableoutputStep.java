package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class TableoutputStep {
    private Integer id;

    private String stepName;

    private String dbConnectionName;

    private String targetTable;

    private Byte isbatchInsert;

    private Integer entryId;

    private Date createtime;

    private Date updatetime;

    private Integer transMetaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName == null ? null : stepName.trim();
    }

    public String getDbConnectionName() {
        return dbConnectionName;
    }

    public void setDbConnectionName(String dbConnectionName) {
        this.dbConnectionName = dbConnectionName == null ? null : dbConnectionName.trim();
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable == null ? null : targetTable.trim();
    }

    public Byte getIsbatchInsert() {
        return isbatchInsert;
    }

    public void setIsbatchInsert(Byte isbatchInsert) {
        this.isbatchInsert = isbatchInsert;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
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

    public Integer getTransMetaId() {
        return transMetaId;
    }

    public void setTransMetaId(Integer transMetaId) {
        this.transMetaId = transMetaId;
    }
}