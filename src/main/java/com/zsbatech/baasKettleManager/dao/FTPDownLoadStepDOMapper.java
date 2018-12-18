package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FTPDownLoadStepDO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPDownLoadStepDOMapper {

    int insert(FTPDownLoadStepDO ftpDownLoadStepDO);

    FTPDownLoadStepDO selectByName(String stepName);

    FTPDownLoadStepDO selectFTPDownLoadStepVOById(Integer jobMetaId);

    List<FTPDownLoadStepDO> selectBySourceId(int ftpSourceId);
}
