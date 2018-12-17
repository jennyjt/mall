package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.base.common.Pagination;

/**
 * FTP数据源管理接口
 * FtpSouceManageService
 */
public interface FtpSouceManageService {
    /**
     * 创建数据源
     * @param ftpSource
     * @return
     */
    boolean createDataSource(FtpSourceManager ftpSource);

    /**
     * 修改数据源
     * @param ftpSource
     * @return
     */
    boolean updateDataSource(FtpSourceManager ftpSource);

    /**
     * 查询数据源
     * @param currPage
     * @param pageSize
     * @param ftpSource
     * @return
     */
    Pagination<FtpSourceManager> getDataSources(Integer currPage, Integer pageSize, FtpSourceManager ftpSource);

    /**
     * 判断数据源是否被使用
     * @param nickName
     * @return
     */
    boolean isSourceUsed(String nickName);

}
