package com.zsbatech.baasKettleManager.model;

import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 17:52
 */
public class DataMig extends DataMigration {

    private String updatetime;
    private String timeStamp;
    private List<String> updateLookup;

    //接口类型
    private Integer createOrUpdate;

    //周期同步
    private String cycleTime;
    private String jobName;
    private String ktrString;
    private Integer schedulerType;
    //transmeta
    private String transName;
    private String fileName;

    //dbconn  src

    private Integer srcDbconnId;

    private String srcTable;

    private String srcColumn;

    private String srcSql;


    //dbconn  dst
    private String dstTableCh;

    public String getDstTableCh() {
        return dstTableCh;
    }

    public void setDstTableCh(String dstTableCh) {
        this.dstTableCh = dstTableCh;
    }

    private Integer dstDbconnId;

    private String dstTable;

    private String dstColumn;

    private String dstSql;


    public Integer getCreateOrUpdate() {
        return createOrUpdate;
    }

    public void setCreateOrUpdate(Integer createOrUpdate) {
        this.createOrUpdate = createOrUpdate;
    }

    public String getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(String cycleTime) {
        this.cycleTime = cycleTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getUpdateLookup() {
        return updateLookup;
    }

    public void setUpdateLookup(List<String> updateLookup) {
        this.updateLookup = updateLookup;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }


    public String getTransName() {
        return transName;
    }


    public String getKtrString() {
        return ktrString;
    }

    public void setKtrString(String ktrString) {
        this.ktrString = ktrString;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer getSrcDbconnId() {
        return srcDbconnId;
    }

    @Override
    public void setSrcDbconnId(Integer srcDbconnId) {
        this.srcDbconnId = srcDbconnId;
    }

    public Integer getSchedulerType() {
        return schedulerType;
    }

    public void setSchedulerType(Integer schedulerType) {
        this.schedulerType = schedulerType;
    }

    public String getSrcTable() {
        return srcTable;
    }

    public void setSrcTable(String srcTable) {
        this.srcTable = srcTable;
    }

    public String getSrcColumn() {
        return srcColumn;
    }

    public void setSrcColumn(String srcColumn) {
        this.srcColumn = srcColumn;
    }

    public String getSrcSql() {
        return srcSql;
    }

    public void setSrcSql(String srcSql) {
        this.srcSql = srcSql;
    }

    @Override
    public Integer getDstDbconnId() {
        return dstDbconnId;
    }

    @Override
    public void setDstDbconnId(Integer dstDbconnId) {
        this.dstDbconnId = dstDbconnId;
    }


    public String getDstTable() {
        return dstTable;
    }

    public void setDstTable(String dstTable) {
        this.dstTable = dstTable;
    }

    public String getDstColumn() {
        return dstColumn;
    }

    public void setDstColumn(String dstColumn) {
        this.dstColumn = dstColumn;
    }

    public String getDstSql() {
        return dstSql;
    }

    public void setDstSql(String dstSql) {
        this.dstSql = dstSql;
    }

    public DataMig() {
    }


}
