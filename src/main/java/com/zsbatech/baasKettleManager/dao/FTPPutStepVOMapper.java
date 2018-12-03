package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPPutStepVOMapper {

    int insert(FTPPutStepVO ftpPutStepVO);

    FTPPutStepVO selectByName(String stepName);
}
