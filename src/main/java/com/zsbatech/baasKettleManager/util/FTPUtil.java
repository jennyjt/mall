package com.zsbatech.baasKettleManager.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/29
 */
public class FTPUtil {

    //建立ftp链接
    public static FTPClient loginFTP(String host, int port, String user, String pass) {
        int reply;
        boolean loginFlag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            if (port != 0) {
                ftpClient.connect(host, port);
            } else {
                ftpClient.connect(host);
            }
            System.out.println("Connected to " + host);
            System.out.print(ftpClient.getReplyString());
            reply = ftpClient.getReplyCode();
            ftpClient.setFileTransferMode(FTP.ASCII_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            loginFlag = ftpClient.login(user, pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginFlag) {
            System.out.println("login success");
            System.out.println("host" + host);
            return ftpClient;
        } else {
            System.out.println("login failed");
            return null;

        }
    }

    //关闭ftp链接
    public void ftpConnectClose(FTPClient ftp) {
        try {
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    //获取文件目录信息
    public static Map<String, List<String>> getFiles(FTPClient client,String fileCatalog, String file) {
        Map<String, List<String>> filesVOMap = null;
        try {
            filesVOMap = new HashMap<>();
            List<String> fielList = new ArrayList<>();
            if (file == null) {
                FTPFile[] files = client.listFiles(fileCatalog);
                for(FTPFile file1:files){
                    String fileName= file1.getName();
                    fielList.add(fileName);
                }
                filesVOMap.put(fileCatalog,fielList);
            }else {
                FTPFile[] files = client.listFiles(fileCatalog);
                fielList.add(file);
                filesVOMap.put(fileCatalog,fielList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filesVOMap;
    }

    //获取文件目录信息
    public static boolean createCatalog(FTPClient client,String fileCatalog) {
        try {
            FTPFile[] ftpFile = client.listFiles(fileCatalog);
            if(ftpFile == null && ftpFile.length == 0){
                client.makeDirectory(fileCatalog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}