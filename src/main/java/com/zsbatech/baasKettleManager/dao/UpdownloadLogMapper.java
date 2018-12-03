package com.zsbatech.baasKettleManager.dao;

import com.zsbatech.baasKettleManager.model.UpdownloadLog;

public interface UpdownloadLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UpdownloadLog record);

    int insertSelective(UpdownloadLog record);

    UpdownloadLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UpdownloadLog record);

    int updateByPrimaryKey(UpdownloadLog record);
}