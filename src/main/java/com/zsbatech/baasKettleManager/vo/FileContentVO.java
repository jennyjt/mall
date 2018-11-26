package com.zsbatech.baasKettleManager.vo;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public class FileContentVO {

    private int id;

    private String sourceContent;

    private Date createTime;

    private Date updateTime;

    private List<FilesVO> filesVOList;

    public int getId() {
        return id;
    }

    public String getSourceContent() {
        return sourceContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public List<FilesVO> getFilesVOList() {
        return filesVOList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSourceContent(String sourceContent) {
        this.sourceContent = sourceContent;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setFilesVOList(List<FilesVO> filesVOList) {
        this.filesVOList = filesVOList;
    }
}
