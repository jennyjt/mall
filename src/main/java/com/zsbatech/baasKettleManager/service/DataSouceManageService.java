package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.model.SrcDbConnection;

import java.util.List;

/**
 * 数据源管理接口
 * DataSouceManageService
 */
public interface DataSouceManageService {
    /**
     * 创建输入数据源
     * @param dbConnection
     * @return
     */
    boolean createSrcDataSource(SrcDbConnection dbConnection);

    /**
     * 创建输入数据源
     * @param dbConnection
     * @return
     */
    boolean createSrcDataSourceList(List<SrcDbConnection> dbConnectionList);

    /**
     * 创建输出数据源
     * @param dbConnection
     * @return
     */
    boolean createDstDataSource(DstDbConnection dbConnection);

    /**
     * 修改输入数据源
     * @param dbConnection
     * @return
     */
    boolean updateSrcDataSource(SrcDbConnection dbConnection);

    /**
     * 修改输出数据源
     * @param dbConnection
     * @return
     */
    boolean updateDstDataSource(DstDbConnection dbConnection);

    /**
     * 查询输入数据源
     * @param jobId
     * @param stepId
     * @return
     */
    List<SrcDbConnection> getSrcDataSources(Integer jobId, Integer stepId);

    /**
     * 查询输出数据源
     * @param jobId
     * @param stepId
     * @return
     */
    List<DstDbConnection> getDstDataSources(Integer jobId, Integer stepId);
}
