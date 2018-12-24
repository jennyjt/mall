package com.zsbatech.baasKettleManager.vo;

import java.util.List;

public class FileCatalogVO {

    private String sourceCatalog;

    private short layer;

    private int fileCount;

    private int id;

    private List<FileCatalogVO> fileCatalogVOList;

    public String getSourceCatalog() {
        return sourceCatalog;
    }

    public void setSourceCatalog(String sourceCatalog) {
        this.sourceCatalog = sourceCatalog;
    }


    public short getLayer() {
        return layer;
    }

    public void setLayer(short layer) {
        this.layer = layer;
    }


    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public List<FileCatalogVO> getFileCatalogVOList() {
        return fileCatalogVOList;
    }

    public void setFileCatalogVOList(List<FileCatalogVO> fileCatalogVOList) {
        this.fileCatalogVOList = fileCatalogVOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
