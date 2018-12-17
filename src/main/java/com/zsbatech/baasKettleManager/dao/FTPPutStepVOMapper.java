package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPPutStepVOMapper {

    int insert(FTPPutStepVO ftpPutStepVO);

    FTPPutStepVO selectByName(String stepName);

    FTPPutStepVO selectFTPPutStepVOById(Integer jobMetaId);

    List<FTPPutStepVO> selectBySourceId(int ftpSourceId);
}
