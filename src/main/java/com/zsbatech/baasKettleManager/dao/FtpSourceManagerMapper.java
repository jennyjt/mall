package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.FtpSourceManager;

public interface FtpSourceManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FtpSourceManager record);

    int insertSelective(FtpSourceManager record);

    FtpSourceManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FtpSourceManager record);

    int updateByPrimaryKey(FtpSourceManager record);
}