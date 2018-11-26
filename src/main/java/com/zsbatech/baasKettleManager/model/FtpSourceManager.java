package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class FtpSourceManager {
    private Integer id;

    private String ftpHost;

    private String FtpPort;

    private String userName;

    private String passWord;

    private String poxyHost;

    private String poxyPort;

    private String poxyUserName;

    private String poxyPassWord;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost == null ? null : ftpHost.trim();
    }

    public String getFtpPort() {
        return FtpPort;
    }

    public void setFtpPort(String FtpPort) {
        this.FtpPort = FtpPort == null ? null : FtpPort.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getPoxyHost() {
        return poxyHost;
    }

    public void setPoxyHost(String poxyHost) {
        this.poxyHost = poxyHost == null ? null : poxyHost.trim();
    }

    public String getPoxyPort() {
        return poxyPort;
    }

    public void setPoxyPort(String poxyPort) {
        this.poxyPort = poxyPort == null ? null : poxyPort.trim();
    }

    public String getPoxyUserName() {
        return poxyUserName;
    }

    public void setPoxyUserName(String poxyUserName) {
        this.poxyUserName = poxyUserName == null ? null : poxyUserName.trim();
    }

    public String getPoxyPassWord() {
        return poxyPassWord;
    }

    public void setPoxyPassWord(String poxyPassWord) {
        this.poxyPassWord = poxyPassWord == null ? null : poxyPassWord.trim();
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
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}