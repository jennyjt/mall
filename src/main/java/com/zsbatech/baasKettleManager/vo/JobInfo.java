package com.zsbatech.baasKettleManager.vo;


import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;
import com.zsbatech.baasKettleManager.model.FTPPutStepDO;
import com.zsbatech.baasKettleManager.model.FtpSourceManageDO;
import com.zsbatech.baasKettleManager.model.JobMetaDO;

import java.util.List;

public class JobInfo {

    private JobMetaDO jobMetaDO;

    private FTPPutStepDO ftpPutStepDO;

    private FTPDownLoadStepDO ftpDownLoadStepDO;

    private List<FtpSourceManageDO> ftpSourceManageDO;

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

    public List<FtpSourceManageDO> getFtpSourceManageDO() {
        return ftpSourceManageDO;
    }

    public void setFtpSourceManageDO(List<FtpSourceManageDO> ftpSourceManageDO) {
        this.ftpSourceManageDO = ftpSourceManageDO;
    }
}
