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
    boolean fileUpload(MultipartFile file, String userId);

    /**
     * 文件下载
     * @param fileId
     * @param response
     */
    boolean fileDownload(List<Integer> fileId, HttpServletResponse response);
}
