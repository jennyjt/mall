package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FtpSourceManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FtpSourceManager record);

    int insertSelective(FtpSourceManager record);

    FtpSourceManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FtpSourceManager record);

    int updateByPrimaryKey(FtpSourceManager record);

    List<FtpSourceManager> getFtpSourcesByParam(FtpSourceManager param);

    int increaseUseCount(Integer id);

    int decreaseUseCount(Integer id);

    /**通过nickName获取未被删除的数据源信息*/
    List<FtpSourceManager> getNormalFtpSourceByNickName(String nickName);
}