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

    private String fileName;

    public JobStartStepDO getJobStartStepVO() {
        return jobStartStepVO;
    }

    public FTPDownLoadStepDO getFtpDownLoadStepVO() {
        return ftpDownLoadStepVO;
    }

    public String getFileName() {
        return fileName;
    }

    public FTPPutStepDO getFtpPutStepVO() {
        return ftpPutStepVO;
    }

    public void setJobStartStepVO(JobStartStepDO jobStartStepVO) {
        this.jobStartStepVO = jobStartStepVO;
    }

    public void setFtpDownLoadStepVO(FTPDownLoadStepDO ftpDownLoadStepVO) {
        this.ftpDownLoadStepVO = ftpDownLoadStepVO;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFtpPutStepVO(FTPPutStepDO ftpPutStepVO) {
        this.ftpPutStepVO = ftpPutStepVO;
    }
}
