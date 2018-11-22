package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class TransHop {
    private Integer id;

    private Integer fromStepId;

    private Integer toStepId;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromStepId() {
        return fromStepId;
    }

    public void setFromStepId(Integer fromStepId) {
        this.fromStepId = fromStepId;
    }

    public Integer getToStepId() {
        return toStepId;
    }

    public void setToStepId(Integer toStepId) {
        this.toStepId = toStepId;
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
}