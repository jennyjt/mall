package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPDownLoadStepVOMapper {

    int insert(FTPDownLoadStepVO ftpDownLoadStepVO);

    FTPDownLoadStepVO selectByName(String stepName);
}
