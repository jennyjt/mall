package com.zsbatech.baasKettleManager.vo;

import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;
import com.zsbatech.baasKettleManager.model.FTPPutStepDO;
import com.zsbatech.baasKettleManager.model.JobStartStepDO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/23
 */
public class FTPSyncSetp {

    private JobStartStepDO jobStartStepDO;

    private FTPDownLoadStepDO ftpDownLoadStepDO;

    private FTPPutStepDO ftpPutStepDO;

    private String jobName;

    private String srcNickName;

    private String dstNickName;

    public JobStartStepDO getJobStartStepDO() {
        return jobStartStepDO;
    }

    public FTPDownLoadStepDO getFtpDownLoadStepDO() {
        return ftpDownLoadStepDO;
    }

    public String getJobName() {
        return jobName;
    }

    public FTPPutStepDO getFtpPutStepDO() {
        return ftpPutStepDO;
    }

    public String getSrcNickName() {
        return srcNickName;
    }

    public String getDstNickName() {
        return dstNickName;
    }

    public void setJobStartStepDO(JobStartStepDO jobStartStepDO) {
        this.jobStartStepDO = jobStartStepDO;
    }

    public void setFtpDownLoadStepDO(FTPDownLoadStepDO ftpDownLoadStepDO) {
        this.ftpDownLoadStepDO = ftpDownLoadStepDO;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setFtpPutStepDO(FTPPutStepDO ftpPutStepDO) {
        this.ftpPutStepDO = ftpPutStepDO;
    }

    public void setSrcNickName(String srcNickName) {
        this.srcNickName = srcNickName;
    }

    public void setDstNickName(String dstNickName) {
        this.dstNickName = dstNickName;
    }
}
