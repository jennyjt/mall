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
     * 查询未被删除的数据源
     * @param currPage
     * @param pageSize
     * @param dbManagement
     * @return
     */
    Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, DbManagement dbManagement);

    /**
     * 删除数据源--修改状态为已删除
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

    /**
     * 校验数据源是否能够连接
     * @param dbManagement
     * @return
     */
    boolean checkDataSource(DbManagement dbManagement);

    /**
     * 校验数据源名称
     * @param dbManagement
     * @return 未被使用，返回true
     */
    boolean checkUniqueLinkName(DbManagement dbManagement);
}
