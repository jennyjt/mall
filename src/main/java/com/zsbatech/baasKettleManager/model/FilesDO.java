package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class FilesDO {
    private Integer id;

    private String fileName;

    private String fileCatalog;

    private String originName;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String fileSource;

    private FileCatalogDO fileCatalogDO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileCatalog() {
        return fileCatalog;
    }

    public void setFileCatalog(String fileCatalog) {
        this.fileCatalog = fileCatalog;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getFileSource() {
        return fileSource;
    }

    public void setFileSource(String fileSource) {
        this.fileSource = fileSource;
    }

    public FileCatalogDO getFileCatalogDO() {
        return fileCatalogDO;
    }

    public void setFileCatalogDO(FileCatalogDO fileCatalogDO) {
        this.fileCatalogDO = fileCatalogDO;
    }

    public String toString(){
        return getFileCatalog()+"/"+getFileName();
    }
}