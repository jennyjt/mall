package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FTPPutStepDO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPPutStepDOMapper {

    int insert(FTPPutStepDO ftpPutStepDO);

    FTPPutStepDO selectByName(String stepName);

    FTPPutStepDO selectFTPPutStepVOById(Integer jobMetaId);

    List<FTPPutStepDO> selectBySourceId(int ftpSourceId);

    int updateByJobId(FTPPutStepDO ftpPutStepDO);
}
