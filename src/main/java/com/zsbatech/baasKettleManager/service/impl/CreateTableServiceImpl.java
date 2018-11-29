package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.ResponseData;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.row.RowMetaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CreateTableServiceImpl implements CreateTableService {

    @Autowired
    DbManagementMapper dbManagementMapper;

    public ResponseData<String> createTable(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

        DbManagement  dbManagementsrc = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
        DbManagement  dbManagementdst = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());
        try {
            String execSql = TableUtil.getCreateTableDDL(dbManagementsrc,dbManagementdst,"user");
//            CREATE TABLE `test` (
//                    `a` varchar(255) NOT NULL,
//            `b` varchar(255) NOT NULL,
//            `c` varchar(255) NOT NULL,
//            `d` varchar(255) DEFAULT NULL,
//            PRIMARY KEY (`a`,`b`,`c`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8;//todo 联合主键

            createTb(dbManagementdst,execSql);
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

            responseData.set(200,"success",Arrays.toString(fields));
//            responseData.setOK(200,"success","Create table success.");
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("Create table fail!");
        }
        return responseData;
    }

}
