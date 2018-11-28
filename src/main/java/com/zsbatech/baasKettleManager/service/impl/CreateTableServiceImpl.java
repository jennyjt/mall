package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.baasKettleManager.util.TableUtil;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.*;

@Service
public class CreateTableServiceImpl implements CreateTableService {

    @Autowired
    DbManagementMapper dbManagementMapper;

    public ResponseData<String> createTable(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
//        getCreateTableDDL(DbManagement sourceDM,DbManagement destDM,String tableName)

        DbManagement  dbManagementsrc = dbManagementMapper.selectByPrimaryKey(dataMig.getSrcDbconnId());
        DbManagement  dbManagementdst = dbManagementMapper.selectByPrimaryKey(dataMig.getDstDbconnId());
        try {
            String execSql = TableUtil.getCreateTableDDL(dbManagementsrc,dbManagementdst,"user");
//            execSql = "use database test2; \n" + execSql;
            createTb(dataMig,execSql);
            responseData.setOK(200,"success","Create table success.");
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("Create table fail!");
        }
//       String tbname = createTable.getTableName();
        return responseData;
    }



    public void createTb(DataMig dataMig, String sql) throws Exception{

        // database driver
        String driver = "com.mysql.jdbc.Driver";
        // database url
//        String url = "jdbc:mysql://localhost:3306"+"/"+dataMig.getDstDbName();
        String url = "jdbc:mysql://localhost:3306/test2";
      System.out.println(url);
        String user = "root";

        String password = "root";

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        //if table already exists return,otherwise create table
        System.out.println("\n Create Table: " + sql);
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
        st.close();
        connection.close();
    }

}
