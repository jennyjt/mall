package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class DataMigration {
    private Integer id;

    private Integer transMetaId;

    private Integer jobId;

    private Integer inputStepId;

    private Integer outputStepId;

    private Integer hopId;

    private Integer srcDbconnId;

    private String sqlString;

    private Integer dstDbconnId;

    private Integer records;

    private Date created;

    private Date updated;

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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getInputStepId() {
        return inputStepId;
    }

    public void setInputStepId(Integer inputStepId) {
        this.inputStepId = inputStepId;
    }

    public Integer getOutputStepId() {
        return outputStepId;
    }

    public void setOutputStepId(Integer outputStepId) {
        this.outputStepId = outputStepId;
    }

    public Integer getHopId() {
        return hopId;
    }

    public void setHopId(Integer hopId) {
        this.hopId = hopId;
    }

    public Integer getSrcDbconnId() {
        return srcDbconnId;
    }

    public void setSrcDbconnId(Integer srcDbconnId) {
        this.srcDbconnId = srcDbconnId;
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString == null ? null : sqlString.trim();
    }

    public Integer getDstDbconnId() {
        return dstDbconnId;
    }

    public void setDstDbconnId(Integer dstDbconnId) {
        this.dstDbconnId = dstDbconnId;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
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
}