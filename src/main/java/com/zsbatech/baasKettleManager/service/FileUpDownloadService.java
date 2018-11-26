package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * HTTP文件上传下载管理接口
 * FileUpDownloadService
 */
public interface FileUpDownloadService {
    /**
     * 文件上传
     * @param file
     * @param fileInfo
     * @return
     */
    boolean fileUpload(MultipartFile file, FTPDownLoadStepVO fileInfo);

    /**
     * 文件下载
     * @param fileId
     */
    void fileDownload(Integer fileId);
}
