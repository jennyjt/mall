package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class TableinputStep {
    private Integer id;

    private String transStepName;

    private String dbConnectionName;

    private String excSql;

    private Integer transMetaId;

    private Date createtime;

    private Date updatetime;

    private Integer dbManagementId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransStepName() {
        return transStepName;
    }

    public void setTransStepName(String transStepName) {
        this.transStepName = transStepName == null ? null : transStepName.trim();
    }

    public String getDbConnectionName() {
        return dbConnectionName;
    }

    public void setDbConnectionName(String dbConnectionName) {
        this.dbConnectionName = dbConnectionName == null ? null : dbConnectionName.trim();
    }

    public String getExcSql() {
        return excSql;
    }

    public void setExcSql(String excSql) {
        this.excSql = excSql == null ? null : excSql.trim();
    }

    public Integer getTransMetaId() {
        return transMetaId;
    }

    public void setTransMetaId(Integer transMetaId) {
        this.transMetaId = transMetaId;
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

    public Integer getDbManagementId() {
        return dbManagementId;
    }

    public void setDbManagementId(Integer dbManagementId) {
        this.dbManagementId = dbManagementId;
    }
}