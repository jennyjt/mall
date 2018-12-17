package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;
import com.zsbatech.baasKettleManager.model.FTPPutStepDO;
import com.zsbatech.baasKettleManager.model.FileCatalogDO;
import com.zsbatech.baasKettleManager.model.JobStartStepDO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public interface FileSyncJobService {

    String createDownloadJobMeta(JobStartStepDO jobStartStepDO, FTPDownLoadStepDO ftpDownLoadStepDO, String fileName);

    String createPutJobMeta(JobStartStepDO jobStartStepDO, FTPPutStepDO ftpPutStepDO, String fileName);

    String fileSyncFtpToFtpJobMeta(JobStartStepDO jobStartStepDO, FTPPutStepDO ftpPutStepDO,FTPDownLoadStepDO ftpDownLoadStepDO,String fileName);

    boolean saveFileInfo(int ftpSourceId,String createCode, String fileCatalog , String fileList);

    List<FileCatalogDO> saveFileCatalogInfo(List<FileCatalogDO> fileCatalogDOList);

    String selectByJobName(String jobName);
}
