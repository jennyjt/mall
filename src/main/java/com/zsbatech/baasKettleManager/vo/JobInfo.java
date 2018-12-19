package com.zsbatech.baasKettleManager.vo;


public class JobInfo {

    private String jobName;

    private String excuteType;

    private String sourceNickName;

    private String catalogNickName;

    private String timingType;

    private String updateTime;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getExcuteType() {
        return excuteType;
    }

    public void setExcuteType(String excuteType) {
        this.excuteType = excuteType;
    }

    public String getSourceNickName() {
        return sourceNickName;
    }

    public void setSourceNickName(String sourceNickName) {
        this.sourceNickName = sourceNickName;
    }

    public String getCatalogNickName() {
        return catalogNickName;
    }

    public void setCatalogNickName(String catalogNickName) {
        this.catalogNickName = catalogNickName;
    }

    public String getTimingType() {
        return timingType;
    }

    public void setTimingType(String timingType) {
        this.timingType = timingType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
