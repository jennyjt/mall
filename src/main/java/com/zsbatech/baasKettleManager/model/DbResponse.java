package com.zsbatech.baasKettleManager.model;

import org.joda.time.DateTime;

public class DbResponse {

    private String linkName;
    private String updateTime;
    private Long jobNum;
    private Long linesSuccessed;
    private String  ktrPath;

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getJobNum() {
        return jobNum;
    }

    public void setJobNum(Long jobNum) {
        this.jobNum = jobNum;
    }

    public String getKtrPath() {
        return ktrPath;
    }

    public void setKtrPath(String ktrPath) {
        this.ktrPath = ktrPath;
    }

    public Long getLinesSuccessed() {
        return linesSuccessed;
    }

    public void setLinesSuccessed(Long linesSuccessed) {
        this.linesSuccessed = linesSuccessed;
    }
}
