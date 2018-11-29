package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.baasKettleManager.vo.FilesVO;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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
            targetFile.getParentFile().mkdirs();
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
    public boolean fileDownload(List<Integer> fileIds, HttpServletResponse response) {
        List<FilesVO> fileInfos = catalogManageService.getFileInfosByIds(fileIds);
        if (fileInfos == null || fileInfos.isEmpty()){
            return false;
        }
        try {
            for(FilesVO fileInfo:fileInfos) {
                File file = new File(fileInfo.getFileCatalog() + File.separator + fileInfo.getFileName());
                //判断文件是否存在
                if (!file.exists()) {
                    return false;
                }

                response.setContentLengthLong(file.length());
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("UTF-8");
                //设置文件响应大小
                response.setContentLengthLong(file.length());
                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileInfo.getOriginName(), "UTF-8"));
                response.addHeader("Pargam", "no-cache");
                response.addHeader("Cache-Control", "no-cache");

                //copyUtil里面关闭流
                //FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
                FileInputStream is = new FileInputStream(file);
                StreamUtils.copy(is, response.getOutputStream());
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
