package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FileCatalogVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public interface FileSyncJobService {

    String createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO,String fileName);

    String createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO,String fileName);

    String fileSyncFtpToFtpJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO,FTPDownLoadStepVO ftpDownLoadStepVO,String fileName);

    boolean saveFileInfo(int ftpSourceId,String createCode, String fileCatalog , String fileList);

    List<FileCatalogVO> saveFileCatalogInfo(List<FileCatalogVO> fileCatalogVOList);

    String selectByJobName(String jobName);
}
