package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DbConnection;

import java.util.List;

/**
 * 数据源管理接口
 * DataSouceManageService
 */
public interface DataSouceManageService {
    /**
     * 创建数据源
     * @param dbConnection
     * @return
     */
    boolean createDataSource(DbConnection dbConnection);

    /**
     * 修改数据源
     * @param dbConnection
     * @return
     */
    boolean updateDataSource(DbConnection dbConnection);

    /**
     * 查询数据源
     * @param jobId
     * @param stepId
     * @return
     */
    List<DbConnection> getDataSources(Integer jobId, Integer stepId);
}
