package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载接口实现类
 */
@Service
public class FileDownloadServiceImpl implements FileUpDownloadService {

    @Autowired
    private SaveJobMetaService saveJobMetaService;


    @Override
    public boolean fileUpload(MultipartFile file) {
        return false;
    }

    @Override
    public void fileDownload(Integer fileId) {

    }
}
