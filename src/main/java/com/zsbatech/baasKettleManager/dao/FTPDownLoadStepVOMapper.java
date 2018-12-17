package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/26
 */
public interface FTPDownLoadStepVOMapper {

    int insert(FTPDownLoadStepVO ftpDownLoadStepVO);

    FTPDownLoadStepVO selectByName(String stepName);

    FTPDownLoadStepVO selectFTPDownLoadStepVOById(Integer jobMetaId);

    List<FTPDownLoadStepVO> selectBySourceId(int ftpSourceId);
}
