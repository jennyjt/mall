package com.zsbatech.baasKettleManager.vo;


import com.zsbatech.baasKettleManager.model.*;

import java.util.List;

public class JobInfo {

    private JobMetaDO jobMetaDO;

    private FTPPutStepDO ftpPutStepDO;

    private FTPDownLoadStepDO ftpDownLoadStepDO;

    private JobStartStepDO jobStartStepDO;

    public JobMetaDO getJobMetaDO() {
        return jobMetaDO;
    }

    public void setJobMetaDO(JobMetaDO jobMetaDO) {
        this.jobMetaDO = jobMetaDO;
    }

    public FTPPutStepDO getFtpPutStepDO() {
        return ftpPutStepDO;
    }

    public void setFtpPutStepDO(FTPPutStepDO ftpPutStepDO) {
        this.ftpPutStepDO = ftpPutStepDO;
    }

    public FTPDownLoadStepDO getFtpDownLoadStepDO() {
        return ftpDownLoadStepDO;
    }

    public void setFtpDownLoadStepDO(FTPDownLoadStepDO ftpDownLoadStepDO) {
        this.ftpDownLoadStepDO = ftpDownLoadStepDO;
    }


    public JobStartStepDO getJobStartStepDO() {
        return jobStartStepDO;
    }

    public void setJobStartStepDO(JobStartStepDO jobStartStepDO) {
        this.jobStartStepDO = jobStartStepDO;
    }
}
