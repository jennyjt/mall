package com.zsbatech.baasKettleManager.model;

/**
 * @author Caohuaijie@zsbatech.com
 * @date 2018/11/19 17:52
 */
public class DataMig extends DataMigration {

    //transmeta
    private String transName;
    private String fileName;

    //transhop
    private String fromStepId;
    private String toStepId;

    //inputstep
    private String transStepName;

    private String excSql;
    private Integer transMetaId;

    //outputstep

    private String stepName;


    private String targetTable;

    private Byte isbatchInsert;

    private Integer entityId;

    //srcdbconnection
    private String srcLinkName;

    private String srcDbHost;

    private String srcDbPort;

    private String srcDbName;

    private String srcDbUser;

    private String srcDbPassword;

    private String srcTable;

    private String srcColumn;

    private String srcSql;

    //dst_db_connection
    private String dstLinkName;

    private String dstDbHost;

    private String dstDbPort;

    private String dstDbName;

    private String dstDbUser;

    private String dstDbPassword;

    private String dstTable;

    private String dstColumn;

    private String dstSql;

    public DataMig() {
    }
//    private JobMeta jobMeta;
//    private JobHop jobHop;
//    private SrcDbConnection srcDbConnection;
//    private DstDbConnection dstDbConnection;
//    private TableinputStep tableinputStep;
//    private TableoutputStep tableoutputStep;


    public String getDstColumn() {
        return dstColumn;
    }

    public void setDstColumn(String dstColumn) {
        this.dstColumn = dstColumn;
    }

    public String getTransName() {
        return transName;
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

    public String getFromStepId() {
        return fromStepId;
    }

    public void setFromStepId(String fromStepId) {
        this.fromStepId = fromStepId;
    }

    public String getToStepId() {
        return toStepId;
    }

    public void setToStepId(String toStepId) {
        this.toStepId = toStepId;
    }

    public String getTransStepName() {
        return transStepName;
    }

    public void setTransStepName(String transStepName) {
        this.transStepName = transStepName;
    }


    public String getExcSql() {
        return excSql;
    }

    public void setExcSql(String excSql) {
        this.excSql = excSql;
    }

    public Integer getTransMetaId() {
        return transMetaId;
    }

    public void setTransMetaId(Integer transMetaId) {
        this.transMetaId = transMetaId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }


    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public Byte getIsbatchInsert() {
        return isbatchInsert;
    }

    public void setIsbatchInsert(Byte isbatchInsert) {
        this.isbatchInsert = isbatchInsert;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getSrcLinkName() {
        return srcLinkName;
    }

    public void setSrcLinkName(String srcLinkName) {
        this.srcLinkName = srcLinkName;
    }

    public String getSrcDbHost() {
        return srcDbHost;
    }

    public void setSrcDbHost(String srcDbHost) {
        this.srcDbHost = srcDbHost;
    }

    public String getSrcDbPort() {
        return srcDbPort;
    }

    public void setSrcDbPort(String srcDbPort) {
        this.srcDbPort = srcDbPort;
    }

    public String getSrcDbName() {
        return srcDbName;
    }

    public void setSrcDbName(String srcDbName) {
        this.srcDbName = srcDbName;
    }

    public String getSrcDbUser() {
        return srcDbUser;
    }

    public void setSrcDbUser(String srcDbUser) {
        this.srcDbUser = srcDbUser;
    }

    public String getSrcDbPassword() {
        return srcDbPassword;
    }

    public void setSrcDbPassword(String srcDbPassword) {
        this.srcDbPassword = srcDbPassword;
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

    public String getDstLinkName() {
        return dstLinkName;
    }

    public void setDstLinkName(String dstLinkName) {
        this.dstLinkName = dstLinkName;
    }

    public String getDstDbHost() {
        return dstDbHost;
    }

    public void setDstDbHost(String dstDbHost) {
        this.dstDbHost = dstDbHost;
    }

    public String getDstDbPort() {
        return dstDbPort;
    }

    public void setDstDbPort(String dstDbPort) {
        this.dstDbPort = dstDbPort;
    }

    public String getDstDbName() {
        return dstDbName;
    }

    public void setDstDbName(String dstDbName) {
        this.dstDbName = dstDbName;
    }

    public String getDstDbUser() {
        return dstDbUser;
    }

    public void setDstDbUser(String dstDbUser) {
        this.dstDbUser = dstDbUser;
    }

    public String getDstDbPassword() {
        return dstDbPassword;
    }

    public void setDstDbPassword(String dstDbPassword) {
        this.dstDbPassword = dstDbPassword;
    }

    public String getDstTable() {
        return dstTable;
    }

    public void setDstTable(String dstTable) {
        this.dstTable = dstTable;
    }

    public String getDstSql() {
        return dstSql;
    }

    public void setDstSql(String dstSql) {
        this.dstSql = dstSql;
    }
}
