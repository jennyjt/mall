package com.zsbatech.baasKettleManager.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 这里提供一些文件的上传下载操作
 */
public class FileUtil {

    public static String upload(MultipartFile file){
        //文件要上传的路径
        String path = ConfigUtil.getPropertyValue("file.httpDirectory");
        String fileName = file.getOriginalFilename();
        //File.serparator会根据不同系统加分隔符
        path = path + File.separator + fileName;
        File tempFile = new File(path);
        //如果上传文件路径不存在就新建
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();
        }
        try {
            //文件复制
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File uploading is successfully";
    }
}