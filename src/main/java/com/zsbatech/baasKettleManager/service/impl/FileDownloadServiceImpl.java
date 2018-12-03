package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.vo.FilesVO;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载接口实现类
 */
@Service
public class FileDownloadServiceImpl implements FileUpDownloadService {

    @Autowired
    private CatalogManageService catalogManageService;

    @Override
    public boolean fileUpload(MultipartFile file, String userId) {
        //文件要上传的路径
        String path = ConfigUtil.getPropertyValue("file.httpDirectory");
        String oldFileName = file.getOriginalFilename();
        String newFileName = DateUtils.currentTimestamp() + oldFileName.substring(oldFileName.lastIndexOf("."));

        File targetFile = new File(path + File.separator + newFileName);
        //如果目标文件路径不存在就新建
        if(!targetFile.getParentFile().exists()){
            List<String> paths = new ArrayList<>();
            paths.add(path);
            Map<String, List<String>> createCatalogsResult = catalogManageService.createCatalogs(paths);
        }
        try {
            //文件复制
            file.transferTo(targetFile);

            FilesVO filesVO = new FilesVO();
            filesVO.setOriginName(oldFileName);
            filesVO.setCreateTime(DateUtils.currentDateTime());
            filesVO.setCreateUser(userId);
            filesVO.setFileCatalog(path);
            filesVO.setFileName(newFileName);
            boolean result = catalogManageService.saveFile(filesVO);
            if(!result){
                return false;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean fileDownload(Integer fileId, HttpServletResponse response) {
        FilesVO fileInfo = catalogManageService.getFileInfoById(fileId);
        if (fileInfo == null){
            return false;
        }
        try {
            File file = new File(fileInfo.getFileCatalog() + File.separator + fileInfo.getFileName());
            //判断文件是否存在
            if(!file.exists()) {
                return false;
            }

            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            //设置文件响应大小
            response.setContentLengthLong(file.length());
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileInfo.getOriginName(), "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("file-name", URLEncoder.encode(fileInfo.getOriginName(), "UTF-8"));

            //copyUtil里面关闭流
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
