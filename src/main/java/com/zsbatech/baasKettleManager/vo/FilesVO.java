package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public class FilesVO {

    private int id;

    private String fileName;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String originName;

    private String fileCatalog;

    public int getId() {
        return id;
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


    public String getCreateUser() {
        return createUser;
    }

    public String getOriginName() {
        return originName;
    }

    public String getFileCatalog() {
        return fileCatalog;
    }

    public void setId(int id) {
        this.id = id;
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


    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String toString(){
        return getFileCatalog()+"/"+getFileName();
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public void setFileCatalog(String fileCatalog) {
        this.fileCatalog = fileCatalog;
    }
}
