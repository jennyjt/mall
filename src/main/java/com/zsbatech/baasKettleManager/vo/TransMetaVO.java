package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/19
 */
public class TransMetaVO {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.transName = name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return transName;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    private String transName;


    private String fileName;

    private Date  createTime;

    private  Date updateTime;
}
