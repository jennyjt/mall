package com.zsbatech.baasKettleManager.vo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/23
 */
public class FTPDownLoadSetp {

    private JobStartStepVO jobStartStepVO;

    private FTPDownLoadStepVO ftpDownLoadStepVO;

    private String fileName;

    public JobStartStepVO getJobStartStepVO() {
        return jobStartStepVO;
    }

    public FTPDownLoadStepVO getFtpDownLoadStepVO() {
        return ftpDownLoadStepVO;
    }

    public String getFileName() {
        return fileName;
    }

    public void setJobStartStepVO(JobStartStepVO jobStartStepVO) {
        this.jobStartStepVO = jobStartStepVO;
    }

    public void setFtpDownLoadStepVO(FTPDownLoadStepVO ftpDownLoadStepVO) {
        this.ftpDownLoadStepVO = ftpDownLoadStepVO;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
