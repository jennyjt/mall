package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class PeriodExecute {
    private Integer id;

    private Integer dataMigrationId;

    private Byte isExist;

    private String sqlString;

    private Date modifyTime;

    private Byte dataType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataMigrationId() {
        return dataMigrationId;
    }

    public void setDataMigrationId(Integer dataMigrationId) {
        this.dataMigrationId = dataMigrationId;
    }

    public Byte getIsExist() {
        return isExist;
    }

    public void setIsExist(Byte isExist) {
        this.isExist = isExist;
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString == null ? null : sqlString.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }
}