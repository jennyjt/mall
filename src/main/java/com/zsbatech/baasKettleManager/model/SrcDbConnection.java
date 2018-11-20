package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class SrcDbConnection {
    private Integer id;

    private Integer jobId;

    private Integer stepId;

    private String linkName;

    private String dbHost;

    private String dbPort;

    private String dbName;

    private String dbUser;

    private String dbPassword;

    private String srcTable;

    private Date created;

    private Date updated;

    private String srcColumn;

    private String srcSql;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost == null ? null : dbHost.trim();
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort == null ? null : dbPort.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser == null ? null : dbUser.trim();
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword == null ? null : dbPassword.trim();
    }

    public String getSrcTable() {
        return srcTable;
    }

    public void setSrcTable(String srcTable) {
        this.srcTable = srcTable == null ? null : srcTable.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getSrcColumn() {
        return srcColumn;
    }

    public void setSrcColumn(String srcColumn) {
        this.srcColumn = srcColumn == null ? null : srcColumn.trim();
    }

    public String getSrcSql() {
        return srcSql;
    }

    public void setSrcSql(String srcSql) {
        this.srcSql = srcSql == null ? null : srcSql.trim();
    }
}