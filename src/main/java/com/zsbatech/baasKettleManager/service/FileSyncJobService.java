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

    boolean createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO,String fileName);

    boolean createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO,String fileName);

    boolean fileSyncFtpToFtpJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO,FTPDownLoadStepVO ftpDownLoadStepVO,String fileName);

    boolean saveFileInfo(int ftpSourceId,String createCode, String fileCatalog , String fileList);

    boolean saveFileCatalogInfo(List<FileCatalogVO> fileCatalogVOList);
}
