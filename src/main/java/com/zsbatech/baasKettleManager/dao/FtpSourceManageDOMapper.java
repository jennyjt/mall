package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FtpSourceManageDO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/12
 */
public interface FtpSourceManageDOMapper {

    int insert(FtpSourceManageDO ftpSourceManageDO);

    FtpSourceManageDO selectByName(String nickName);
}
