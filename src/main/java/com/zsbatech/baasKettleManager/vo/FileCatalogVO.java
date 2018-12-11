package com.zsbatech.baasKettleManager.vo;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public class FileCatalogVO {

    private int id;

    private String sourceCatalog;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private int parentId;

    private short layer;

    private String aliasName;

    public int getId() {
        return id;
    }

    public String getSourceCatalog() {
        return sourceCatalog;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public int getParentId() {
        return parentId;
    }

    public short getLayer() {
        return layer;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSourceCatalog(String sourceCatalog) {
        this.sourceCatalog = sourceCatalog;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setLayer(short layer) {
        this.layer = layer;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}
