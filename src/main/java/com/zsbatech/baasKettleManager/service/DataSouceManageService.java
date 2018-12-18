package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.base.common.Pagination;

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
     * @param dbManagement
     * @return
     */
    Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, DbManagement dbManagement);

    /**
     * 删除数据源
     * @param id
     * @return
     */
    boolean deleteDataSource(Integer id);

    /**
     * 减少一次
     * @param id
     * @return
     */
    boolean decreaseUseCount(Integer id);

    /**
     * 增加一次
     * @param id
     * @return
     */
    boolean increaseUseCount(Integer id);


}
