package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class FtpSourceManager {
    private Integer id;

    private String ftpHost;

    private String ftpPort;

    private String userName;

    private String passWord;

    private String proxyHost;

    private String proxyPort;

    private String proxyUserName;

    private String proxyPassWord;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String nickName;

    private Short useCount;

    private Byte status;

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
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort == null ? null : ftpPort.trim();
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

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost == null ? null : proxyHost.trim();
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort == null ? null : proxyPort.trim();
    }

    public String getProxyUserName() {
        return proxyUserName;
    }

    public void setProxyUserName(String proxyUserName) {
        this.proxyUserName = proxyUserName == null ? null : proxyUserName.trim();
    }

    public String getProxyPassWord() {
        return proxyPassWord;
    }

    public void setProxyPassWord(String proxyPassWord) {
        this.proxyPassWord = proxyPassWord == null ? null : proxyPassWord.trim();
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Short getUseCount() {
        return useCount;
    }

    public void setUseCount(Short useCount) {
        this.useCount = useCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}