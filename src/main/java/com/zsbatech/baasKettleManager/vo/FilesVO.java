package com.zsbatech.baasKettleManager.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public class FilesVO {

    private int id;

    private String fileName;

    private Date createTime;

    private Date updateTime;

    private FileContentVO fileContentVO;

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FileContentVO getFileContentVO() {
        return fileContentVO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setFileContentVO(FileContentVO fileContentVO) {
        this.fileContentVO = fileContentVO;
    }

    public String toString(){
        return getFileContentVO().getSourceContent()+"/"+getFileName();
    }
}
