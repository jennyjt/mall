package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
public class TableInputStepVO {

    private int id;

    private String stepName;

    private String dbConnectionName;

    private String excSql;

    private Date createTime;

    private Date updateTime;

    private int transMetaId;
    public int getId() {
        return id;
    }

    public String getStepName() {
        return stepName;
    }

    public String getDbConnectionName() {
        return dbConnectionName;
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

    public int getTransMetaId() {
        return transMetaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setDbConnectionName(String dbConnectionName) {
        this.dbConnectionName = dbConnectionName;
    }

    public void setSql(String excSql) {
        this.excSql = excSql;
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
}
