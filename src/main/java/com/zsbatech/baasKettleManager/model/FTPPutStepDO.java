package com.zsbatech.baasKettleManager.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public class FTPPutStepDO {

    private int id;

    private String serverName;

    private String proxyHost;

    private String userName;

    private String password;

    private String proxyPort;

    private String proxyPassword;

    private String proxyUsername;

    private String port;

    private int timeout;

    private short binaryMode;

    private String controlEncoding;

    private String ftpDirectory;

    private String targetDirectory;

    private Date createTime;

    private Date updateTime;

    private int jobMetaId;

    private  String putFileName;

    private String stepName;

    private int ftpSourceId;

    private String putFtpName;

    public int getId() {
        return id;
    }

    public String getServerName() {
        return serverName;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public String getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public short getBinaryMode() {
        return binaryMode;
    }

    public String getControlEncoding() {
        return controlEncoding;
    }

    public String getFtpDirectory() {
        return ftpDirectory;
    }

    public String getTargetDirectory() {
        return targetDirectory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public int getJobMetaId() {
        return jobMetaId;
    }

    public String getPutFileName() {
        return putFileName;
    }

    public String getStepName() {
        return stepName;
    }

    public int getFtpSourceId() {
        return ftpSourceId;
    }

    public String getPutFtpName() {
        return putFtpName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setBinaryMode(short binaryMode) {
        this.binaryMode = binaryMode;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }

    public void setFtpDirectory(String ftpDirectory) {
        this.ftpDirectory = ftpDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setJobMetaId(int jobMetaId) {
        this.jobMetaId = jobMetaId;
    }

    public void setPutFileName(String putFileName) {
        this.putFileName = putFileName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setFtpSourceId(int ftpSourceId) {
        this.ftpSourceId = ftpSourceId;
    }

    public void setPutFtpName(String putFtpName) {
        this.putFtpName = putFtpName;
    }
}
