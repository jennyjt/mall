package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.ContentManageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
@Service
public class ContentManageServiceImpl implements ContentManageService {

    /**
     * 创建本地目录
     *
     * @param fileContents
     * @return
     */
    public Map<String, List<String>> createContent(String[] fileContents) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateContentList = new ArrayList<>();
        for (String file : fileContents) {
            File file1 = new File(file);
            if (file1.exists()) {
                fileList.add(file);
            } else {
                file1.mkdir();
                newCreateContentList.add(file);
            }
            map.put("已存在目录", fileList);
            map.put("新创建目录", newCreateContentList);
        }
        return map;
    }

    /**
     * 删除本地文件
     *
     * @param files
     * @return
     */
    public Map<String, List<String>> deleteFiles(String[] files) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateFileList = new ArrayList<>();
        for (String file : files) {
            File file1 = new File(file);
            if (file1.exists()) {
                file1.delete();
                fileList.add(file);
            } else {
                newCreateFileList.add(file);
            }
            map.put("删除文件", fileList);
            map.put("已删除文件", newCreateFileList);
        }
        return map;
    }

    /**
     * 删除本地目录
     *
     * @param fileContents
     * @return
     */
    public Map<String, List<String>> deleteContents(String[] fileContents) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        List<String> newCreateFileList = new ArrayList<>();
        for (String file : fileContents) {
            File file1 = new File(file);
            if (file1.exists() && file1.listFiles().length == 0) {
                file1.delete();
                fileList.add(file);
                //删除目录下所有文件
            } else if (file1.exists()) {
                File[] files = file1.listFiles();
                for (File file2 : files) {
                    //文件无法删除
                    if(!file2.delete()){
                        try {
                            throw new IOException();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (file1.listFiles().length == 0) {
                    file1.delete();
                    fileList.add(file);
                }
            } else {
                newCreateFileList.add(file);
            }
            map.put("删除目录", fileList);
            map.put("已删除目录", newCreateFileList);
        }
        return map;
    }
}
