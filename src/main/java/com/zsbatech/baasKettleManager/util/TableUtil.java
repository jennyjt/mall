package com.zsbatech.baasKettleManager.util;

import com.zsbatech.baasKettleManager.model.DbManagement;
import org.apache.tomcat.util.buf.StringUtils;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 获取目标库建表sql
     * @param sourceDM 源库链接
     * @param destDM 目标库链接
     * @param tableName 同步表名
     * @return 建表sql（String）
     * @throws KettleException
     */
    public static String getCreateTableDDL(DbManagement sourceDM,DbManagement destDM,String tableName) throws KettleException {
        KettleEnvironment.init();

        DatabaseMeta sourceDbMeta = new DatabaseMeta(getXmlByDbManagement(sourceDM));
        DatabaseMeta destDbMeta = new DatabaseMeta(getXmlByDbManagement(destDM));

        Database db = new Database(sourceDbMeta);

        db.connect();
        db.setQueryLimit(1);
        String select_sql = "SELECT * FROM "+tableName;
        RowMetaInterface rowMetaInterface = db.getQueryFields(select_sql,false);
        String[] primaryKeys = db.getPrimaryKeyColumnNames(tableName);//获取主键 TODO 拼接设置主键的sql
        db = new Database(destDbMeta);
//        db.getAlterTableStatement();//获取新增字段
        String sqlddl = db.getDDLCreationTable(tableName,rowMetaInterface);
        return sqlddl;
    }
    public static String getCreateIndexDDL(DbManagement sourceDM,DbManagement destDM,String tableName) throws KettleException {
        StringBuilder ddlBuilder = new StringBuilder();

        KettleEnvironment.init();

        DatabaseMeta sourceDbMeta = new DatabaseMeta(getXmlByDbManagement(sourceDM));
        DatabaseMeta destDbMeta = new DatabaseMeta(getXmlByDbManagement(destDM));

        Database sourceDB = new Database(sourceDbMeta);
        Database destDB = new Database(destDbMeta);
        sourceDB.connect();
        String[] indexF = {"id"};

        String select_sql = "SELECT * FROM "+tableName;
        RowMetaInterface rowMetaInterface = sourceDB.getQueryFields(select_sql,false);
        String[] fields = rowMetaInterface.getFieldNames();
        for(String field:fields){
            indexF[0] = field;
            boolean indexBoolean = sourceDB.checkIndexExists(tableName,indexF);
            if(indexBoolean){
                ddlBuilder.append(destDB.getCreateIndexStatement( tableName, "IDX_" + tableName + "_" + field, indexF, false, false, false, true ));
                ddlBuilder.append(Const.CR);
            }
        }

        return ddlBuilder.toString();
    }

    /**
     * 获取目标库建表sql
     * @param sourceDM 源库链接
     * @param destDM 目标库链接
     * @param tableName 同步表名
     * @param columns 列名
     * @return 建表sql（String）
     * @throws KettleException
     */
    public static String getCreateTableDDL(DbManagement sourceDM,DbManagement destDM,String tableName,String columns) throws KettleException {
        KettleEnvironment.init();

        DatabaseMeta sourceDbMeta = new DatabaseMeta(getXmlByDbManagement(sourceDM));
        DatabaseMeta destDbMeta = new DatabaseMeta(getXmlByDbManagement(destDM));

        Database db = new Database(sourceDbMeta);

        db.connect();
        String select_sql = "SELECT "+columns+" FROM "+tableName+" where 1=0";
        RowMetaInterface rowMetaInterface = db.getQueryFields(select_sql,false);
        db = new Database(destDbMeta);
        String sqlddl = db.getDDLCreationTable(tableName,rowMetaInterface);
//        db.getCreateTableStatement();
//        db.execStatement(sqlddl);
        return sqlddl;
    }
    public static String getCreateIndexDDL(DbManagement sourceDM,DbManagement destDM,String tableName,String columns) throws KettleException {
        StringBuilder ddlBuilder = new StringBuilder();

        KettleEnvironment.init();

        DatabaseMeta sourceDbMeta = new DatabaseMeta(getXmlByDbManagement(sourceDM));
        DatabaseMeta destDbMeta = new DatabaseMeta(getXmlByDbManagement(destDM));

        Database sourceDB = new Database(sourceDbMeta);
        Database destDB = new Database(destDbMeta);
        sourceDB.connect();
        String[] indexF = {"id"};

        String select_sql = "SELECT "+columns+" FROM "+tableName;
        RowMetaInterface rowMetaInterface = sourceDB.getQueryFields(select_sql,false);
        String[] fields = rowMetaInterface.getFieldNames();
        for(String field:fields){
            indexF[0] = field;
            boolean indexBoolean = sourceDB.checkIndexExists(tableName,indexF);
            if(indexBoolean){
                ddlBuilder.append(destDB.getCreateIndexStatement( tableName, "IDX_" + tableName + "_" + field, indexF, false, false, false, true ));
                ddlBuilder.append(Const.CR);
            }
        }

        return ddlBuilder.toString();
    }

    public static String getXmlByDbManagement(DbManagement dbManagement){
        if(dbManagement != null){
            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                    .append("<connection>")
                    .append("<name>"+dbManagement.getLinkName()+"</name>")
                    .append("<server>"+dbManagement.getDbHost()+"</server>" )
                    .append("<type>"+dbManagement.getDbType()+"</type>" )
                    .append("<access>Native</access>" )
                    .append("<database>"+dbManagement.getDbName()+"</database>" )
                    .append("<port>"+dbManagement.getDbPort()+"</port>" )
                    .append("<username>"+dbManagement.getDbUser()+"</username>" )
                    .append("<password>"+dbManagement.getDbPassword()+"</password>" )
                    .append("</connection>");
            return xmlBuffer.toString();
        }
        return null;
    }
}
