package com.zsbatech.baasKettleManager.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * HTTP文件上传下载管理接口
 * FileUpDownloadService
 */
public interface FileUpDownloadService {
    /**
     * 文件上传
     * @param file
     * @param userId
     * @return
     */
    boolean fileUpload(MultipartFile file, String userId, String fileDirectory, Integer catalogId);

    /**
     * 文件上传
     * @param files
     * @param userId
     * @return
     */
    boolean multiFilesUpload(List<MultipartFile> files, String userId, String fileDirectory, Integer catalogId);

    /**
     * 文件下载
     * @param fileId
     * @param response
     */
    boolean fileDownload(Integer fileId, HttpServletResponse response, String userId);
}
