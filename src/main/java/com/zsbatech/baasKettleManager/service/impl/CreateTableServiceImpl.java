package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.dao.DstDbConnectionMapper;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.row.RowMetaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CreateTableServiceImpl implements CreateTableService {

    @Autowired
    DbManagementMapper dbManagementMapper;
    @Autowired
    DstDbConnectionMapper dstDbConnectionMapper;

    public ResponseData<String> createTable(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        DstDbConnection dstDbConnection = new DstDbConnection();
        DbManagement  dbManagementsrc = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
        DbManagement  dbManagementdst = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());
        try {
           String execSql = TableUtil.getCreateTableDDL(dbManagementsrc,dataMig.getSrcTable(),dbManagementdst,dataMig.getDstTable());

            createTb(dbManagementdst,execSql);

            String dstTableName = dataMig.getDstTable();
            if (dstTableName == null || dstTableName==""){
                dstTableName = dataMig.getSrcTable();
            }
            if (dataMig.getDstTableCh()!= null){
                dstDbConnection.setDstTableCh(dataMig.getDstTableCh());
            }
            dstDbConnection.setCreated(new Date());
            dstDbConnection.setDstTable(dstTableName);
            dstDbConnection.setLinkId(dataMig.getDstDbconnId());
            dstDbConnection.setUpdated(new Date());

            dstDbConnectionMapper.insert(dstDbConnection);

            responseData.setOK(200,"success","Create table success.");
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("Create table fail!");
        }
        return responseData;
    }



    public void createTb(DbManagement dbManagement, String sql) throws Exception{

        // database driver
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + dbManagement.getDbHost()+":"+dbManagement.getDbPort()+"/"+dbManagement.getDbName();

        String user = dbManagement.getDbUser();

        String password = dbManagement.getDbPassword();

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);


        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
        connection.close();
    }


    public ResponseData<String> getColumns(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

        DbManagement  dbManagementsrc = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());

        try {
            KettleEnvironment.init();

            DatabaseMeta sourceDbMeta = new DatabaseMeta(TableUtil.getXmlByDbManagement(dbManagementsrc));

            Database db = new Database(sourceDbMeta);

            db.connect();
            String select_sql = "SELECT * FROM "+dataMig.getSrcTable();
            RowMetaInterface rowMetaInterface = db.getQueryFields(select_sql,false);
            String[] fields = rowMetaInterface.getFieldNames();

            db.disconnect();
            responseData.set(200,"success",Arrays.toString(fields));

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("Create table fail!");
        }
        return responseData;
    }

    public ResponseData<String> getTables(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

        DbManagement  dbManagementsrc = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());

        try {
            KettleEnvironment.init();

            DatabaseMeta sourceDbMeta = new DatabaseMeta(TableUtil.getXmlByDbManagement(dbManagementsrc));

            Database db = new Database(sourceDbMeta);

            db.connect();
            String[] tablenames =db.getTablenames();

            db.disconnect();
            if (dataMig.getSrcTable()!=null){
                tablenames = filterString(tablenames,dataMig.getSrcTable());
            }
            if(tablenames.length>0){
                responseData.set(200,"success",Arrays.toString(tablenames));
            }else{
                responseData.set(202400,"不存在匹配的表名",Arrays.toString(tablenames));
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError(500,"get table fail!","fail!");
        }
        return responseData;
    }

    public String[] filterString(String[] tablenames,String str){
        List<String> list = new ArrayList<>();
        for (int i =0;i<tablenames.length;i++){
           if(tablenames[i].contains(str)){
                list.add(tablenames[i]);
            }

        }
        String[] array = new String[list.size()];
        return list.toArray(array);

    }

}
