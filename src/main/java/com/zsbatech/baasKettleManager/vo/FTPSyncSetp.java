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

    private JobStartStepDO jobStartStepVO;

    private FTPDownLoadStepDO ftpDownLoadStepVO;

    private FTPPutStepDO ftpPutStepVO;

    private String jobName;

    private String srcNickName;

    private String dstNickName;

    public JobStartStepDO getJobStartStepVO() {
        return jobStartStepVO;
    }

    public FTPDownLoadStepDO getFtpDownLoadStepVO() {
        return ftpDownLoadStepVO;
    }

    public String getJobName() {
        return jobName;
    }

    public FTPPutStepDO getFtpPutStepVO() {
        return ftpPutStepVO;
    }

    public String getSrcNickName() {
        return srcNickName;
    }

    public String getDstNickName() {
        return dstNickName;
    }

    public void setJobStartStepVO(JobStartStepDO jobStartStepVO) {
        this.jobStartStepVO = jobStartStepVO;
    }

    public void setFtpDownLoadStepVO(FTPDownLoadStepDO ftpDownLoadStepVO) {
        this.ftpDownLoadStepVO = ftpDownLoadStepVO;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setFtpPutStepVO(FTPPutStepDO ftpPutStepVO) {
        this.ftpPutStepVO = ftpPutStepVO;
    }

    public void setSrcNickName(String srcNickName) {
        this.srcNickName = srcNickName;
    }

    public void setDstNickName(String dstNickName) {
        this.dstNickName = dstNickName;
    }
}
