package com.zsbatech.baasKettleManager.model;

/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 17:52
 */
public class DataMig extends DataMigration {



    //transmeta
    private String transName;
    private String fileName;

    //dbconn  src

    private Integer srcDbconnId;
    private String databaseSrcType;
    private String srcLinkName;
    private String srcTable;

    private String srcColumn;

    private String srcSql;


    //dbconn  dst
    private Integer dstDbconnId;

    private String databaseDstType;

    private String dstLinkName;


    private String dstTable;

    private String dstColumn;

    private String dstSql;


    private String ktrString;


    public String getTransName() {
        return transName;
    }


    public String getKtrString() {
        return ktrString;
    }

    public void setKtrString(String ktrString) {
        this.ktrString = ktrString;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer getSrcDbconnId() {
        return srcDbconnId;
    }

    @Override
    public void setSrcDbconnId(Integer srcDbconnId) {
        this.srcDbconnId = srcDbconnId;
    }

    public String getDatabaseSrcType() {
        return databaseSrcType;
    }

    public void setDatabaseSrcType(String databaseSrcType) {
        this.databaseSrcType = databaseSrcType;
    }

    public String getSrcLinkName() {
        return srcLinkName;
    }

    public void setSrcLinkName(String srcLinkName) {
        this.srcLinkName = srcLinkName;
    }

    public String getSrcTable() {
        return srcTable;
    }

    public void setSrcTable(String srcTable) {
        this.srcTable = srcTable;
    }

    public String getSrcColumn() {
        return srcColumn;
    }

    public void setSrcColumn(String srcColumn) {
        this.srcColumn = srcColumn;
    }

    public String getSrcSql() {
        return srcSql;
    }

    public void setSrcSql(String srcSql) {
        this.srcSql = srcSql;
    }

    @Override
    public Integer getDstDbconnId() {
        return dstDbconnId;
    }

    @Override
    public void setDstDbconnId(Integer dstDbconnId) {
        this.dstDbconnId = dstDbconnId;
    }

    public String getDatabaseDstType() {
        return databaseDstType;
    }

    public void setDatabaseDstType(String databaseDstType) {
        this.databaseDstType = databaseDstType;
    }

    public String getDstLinkName() {
        return dstLinkName;
    }

    public void setDstLinkName(String dstLinkName) {
        this.dstLinkName = dstLinkName;
    }

    public String getDstTable() {
        return dstTable;
    }

    public void setDstTable(String dstTable) {
        this.dstTable = dstTable;
    }

    public String getDstColumn() {
        return dstColumn;
    }

    public void setDstColumn(String dstColumn) {
        this.dstColumn = dstColumn;
    }

    public String getDstSql() {
        return dstSql;
    }

    public void setDstSql(String dstSql) {
        this.dstSql = dstSql;
    }

    public DataMig() {
    }


}
