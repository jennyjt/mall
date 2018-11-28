package com.zsbatech.baasKettleManager.vo;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/22
 */
public class FileCatalogVO {

    private int id;

    private String sourceCatalog;

    private Date createTime;

    private Date updateTime;

    private List<FilesVO> filesVOList;

    public int getId() {
        return id;
    }

    public String getSourceCatalog() {
        return sourceCatalog;
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

    public void setSourceCatalog(String sourceCatalog) {
        this.sourceCatalog = sourceCatalog;
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
