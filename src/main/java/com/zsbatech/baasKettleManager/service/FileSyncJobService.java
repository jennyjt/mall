package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/21
 */
public interface FileSyncJobService {

    boolean createDownloadJobMeta(JobStartStepVO jobStartStepVO, FTPDownLoadStepVO ftpDownLoadStepVO);

    boolean createPutJobMeta(JobStartStepVO jobStartStepVO, FTPPutStepVO ftpPutStepVO);
}
