package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.*;

@Service
public class CreateTableServiceImpl implements CreateTableService {


    public ResponseData<String> createTable(DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

        try {
            String execSql = generateSql(dataMig);
            createTb(dataMig,execSql);
            responseData.setOK(200,"success","Create table success.");
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setError("Create table fail!");
        }
//       String tbname = createTable.getTableName();
        return responseData;
    }


    public  String generateSql(DataMig dataMig) throws Exception {
        // database driver
        String driver = "com.mysql.jdbc.Driver";
        // database url
//        String url = "jdbc:mysql://localhost:3306"+"/"+dataMig.getSrcDbName();
//        System.out.println(dataMig.getSrcDbName());

        String url = "jdbc:mysql://localhost:3306";


        String user = "root";

        String password = "root";

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        String tableName = "mytable";

        DatabaseMetaData dBMetaData = connection.getMetaData();

        ResultSet colSet = dBMetaData.getColumns(null, "%", tableName, "%");

        String newTabaleName = tableName + "2018_16";
        // generate SQL
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE if not exists`").append(newTabaleName).append("`");
        sql.append("(");

        while (colSet.next()) {
            sql.append("`").append(colSet.getString("COLUMN_NAME")).append("` ");
            sql.append(colSet.getString("TYPE_NAME"));
            String typeName = colSet.getString("TYPE_NAME").trim();
            if(!"TIMESTAMP".equals(typeName)&& !"DATETIME".equals(typeName)) {
                sql.append("(").append(colSet.getString("COLUMN_SIZE")).append(") "); //
            }else if("TIMESTAMP".equals(typeName) || "DATETIME".equalsIgnoreCase(typeName)) {
                sql.append("(6) ");
            }
            if ("0".equals(colSet.getString("NULLABLE"))) {
                if (StringUtils.isEmpty(colSet.getString("COLUMN_DEF"))) {
                    sql.append(" NOT NULL");
                } else {

                    sql.append(" DEFAULT '").append(colSet.getString("COLUMN_DEF")).append("'");
                }
            } else {
                sql.append(" DEFAULT NULL ");
            }
            sql.append(" COMMENT ").append("'").append(colSet.getString("REMARKS")).append("' ,");

        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        sql.append(";");
        colSet.close();

//       create table
        System.out.println("\n Create Table: " + sql);
//        Statement st = connection.createStatement();
//        st.executeUpdate(sql.toString());
//        st.close();

        connection.close();
    return sql.toString();
    }

    public void createTb(DataMig dataMig, String sql) throws Exception{

        // database driver
        String driver = "com.mysql.jdbc.Driver";
        // database url
//        String url = "jdbc:mysql://localhost:3306"+"/"+dataMig.getDstDbName();
        String url = "jdbc:mysql://localhost:3306/";
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
