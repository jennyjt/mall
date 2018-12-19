package com.zsbatech.baasKettleManager.util;

import java.sql.*;

public class JdbcUtil {

    public ResultSet queryJob(String sqls) {
        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.75.73.34:8306/kettle_manager?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "Zsba@mysql2018*";
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
