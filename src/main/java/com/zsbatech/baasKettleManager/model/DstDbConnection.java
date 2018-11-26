package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class DstDbConnection {
    private Integer id;

    private Integer jobId;

    private Integer stepId;

    private Integer linkId;

    private String dstTable;

    private Date created;

    private Date updated;

    private String dstColumn;

    private String dstSql;

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

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getDstTable() {
        return dstTable;
    }

    public void setDstTable(String dstTable) {
        this.dstTable = dstTable == null ? null : dstTable.trim();
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

    public String getDstColumn() {
        return dstColumn;
    }

    public void setDstColumn(String dstColumn) {
        this.dstColumn = dstColumn == null ? null : dstColumn.trim();
    }

    public String getDstSql() {
        return dstSql;
    }

    public void setDstSql(String dstSql) {
        this.dstSql = dstSql == null ? null : dstSql.trim();
    }
}