package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FtpSourceManageVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/12
 */
public interface FtpSourceManageVOMapper {

    int insert(FtpSourceManageVO ftpSourceManageVO);

    FtpSourceManageVO selectByName(String nickName);
}
