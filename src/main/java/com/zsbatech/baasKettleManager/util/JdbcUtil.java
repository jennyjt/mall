package com.zsbatech.baasKettleManager.util;

import com.zsbatech.baasKettleManager.model.DbJobInfo;
import com.zsbatech.baasKettleManager.vo.JobInfo;

import java.sql.*;

public class JdbcUtil {

    public JobInfo getJobInfo (String sql) {

        JobInfo jobInfo = new JobInfo();

        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.75.73.34:8306/kettle_manager?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "Zsba@mysql2018*";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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

        return jobInfo;
    }
}
