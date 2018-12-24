package com.zsbatech.baasKettleManager.util;

import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;
import com.zsbatech.baasKettleManager.model.FTPPutStepDO;
import com.zsbatech.baasKettleManager.model.JobMetaDO;
import com.zsbatech.baasKettleManager.model.JobStartStepDO;
import com.zsbatech.baasKettleManager.vo.JobInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {

    public static  JobInfo getJobInfo(String sql) {

        JobInfo jobInfo = new JobInfo();
        JobMetaDO jobMetaDO = new JobMetaDO();
        JobStartStepDO jobStartStepDO = new JobStartStepDO();
        FTPDownLoadStepDO ftpDownLoadStepDO = new FTPDownLoadStepDO();
        FTPPutStepDO ftpPutStepDO = new FTPPutStepDO();

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
                jobMetaDO.setJobName(rs.getString("job_name"));
                jobMetaDO.setExecuteStatus(rs.getShort("execute_status"));
                jobMetaDO.setFileName(rs.getString("job_name"));
                jobMetaDO.setUpdateTime(rs.getTime("updatetime"));
                if (rs.getString("job_type").trim().equals("0")) {

                    jobMetaDO.setJobType("单次任务");
                } else {
                    jobMetaDO.setJobType("周期任务");
                }
                jobInfo.setJobMetaDO(jobMetaDO);

                jobStartStepDO.setTimingTime(rs.getShort(""));
                jobStartStepDO.setTimingType(rs.getShort("timing_type"));
                jobStartStepDO.setTimingTime(rs.getShort("timing_time"));
                jobInfo.setJobStartStepDO(jobStartStepDO);

                ftpDownLoadStepDO.setFtpDirectory(rs.getString("ftp_directory"));
                ftpDownLoadStepDO.setFtpFileName(rs.getString("file_name"));
                ftpDownLoadStepDO.setTargetDirectory(rs.getString("target_directory"));
                ftpDownLoadStepDO.setFtpSourceId(rs.getInt("ftp_source_id"));
                jobInfo.setFtpDownLoadStepDO(ftpDownLoadStepDO);

                ftpPutStepDO.setFtpDirectory(rs.getString("ftp_directory"));
                ftpPutStepDO.setPutFtpName(rs.getString("put_file_name"));
                ftpPutStepDO.setTargetDirectory(rs.getString("target_directory"));
                ftpPutStepDO.setFtpSourceId(rs.getInt("ftp_source_id"));
                jobInfo.setFtpPutStepDO(ftpPutStepDO);

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

    public static List<JobInfo> getJobInfoByPage(String sql) {

       List<JobInfo> jobInfoList = new ArrayList<>();

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
                JobInfo jobInfo = new JobInfo();
                FTPPutStepDO ftpPutStepDO = new FTPPutStepDO();
                JobMetaDO jobMetaDO = new JobMetaDO();
                JobStartStepDO jobStartStepDO = new JobStartStepDO();
                FTPDownLoadStepDO ftpDownLoadStepDO = new FTPDownLoadStepDO();
                jobMetaDO.setJobName(rs.getString("job_name"));
                jobMetaDO.setExecuteStatus(rs.getShort("execute_status"));
                jobMetaDO.setFileName(rs.getString("job_name"));
                jobMetaDO.setUpdateTime(rs.getTime("updatetime"));
                if (rs.getString("job_type").trim().equals("0")) {

                    jobMetaDO.setJobType("单次任务");
                } else {
                    jobMetaDO.setJobType("周期任务");
                }
                jobInfo.setJobMetaDO(jobMetaDO);

                jobStartStepDO.setTimingTime(rs.getShort(""));
                jobStartStepDO.setTimingType(rs.getShort("timing_type"));
                jobStartStepDO.setTimingTime(rs.getShort("timing_time"));
                jobInfo.setJobStartStepDO(jobStartStepDO);

                ftpDownLoadStepDO.setFtpDirectory(rs.getString("ftp_directory"));
                ftpDownLoadStepDO.setFtpFileName(rs.getString("file_name"));
                ftpDownLoadStepDO.setTargetDirectory(rs.getString("target_directory"));
                ftpDownLoadStepDO.setFtpSourceId(rs.getInt("ftp_source_id"));
                jobInfo.setFtpDownLoadStepDO(ftpDownLoadStepDO);

                ftpPutStepDO.setFtpDirectory(rs.getString("ftp_directory"));
                ftpPutStepDO.setPutFtpName(rs.getString("put_file_name"));
                ftpPutStepDO.setTargetDirectory(rs.getString("target_directory"));
                ftpPutStepDO.setFtpSourceId(rs.getInt("ftp_source_id"));
                jobInfo.setFtpPutStepDO(ftpPutStepDO);

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobInfoList;
    }

}
