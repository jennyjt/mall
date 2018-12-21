package com.zsbatech.baasKettleManager.vo;

public class FileCatalogNode {

    private String nodeName;

    private String parentName;

    private short layer;

    private int fileCount;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
}
