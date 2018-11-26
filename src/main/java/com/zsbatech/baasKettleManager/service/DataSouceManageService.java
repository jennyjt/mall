package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.base.common.Pagination;

import java.util.Map;

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
    boolean createDataSource(DbManagement dbConnection);

    /**
     * 修改数据源
     * @param dbConnection
     * @return
     */
    boolean updateDataSource(DbManagement dbConnection);

    /**
     * 查询数据源
     * @param currPage
     * @param pageSize
     * @param map
     * @return
     */
    Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, Map<String, Object> map);
}
