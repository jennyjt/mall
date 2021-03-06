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
     * 查询未被删除的数据源
     * @param currPage
     * @param pageSize
     * @param ftpSource
     * @return
     */
    Pagination<FtpSourceManager> getDataSources(Integer currPage, Integer pageSize, FtpSourceManager ftpSource);

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
     * ftp数据源是否链接
     * @param ftpSourceManager
     * @return
     */
    boolean isFtpConnected(FtpSourceManager ftpSourceManager);

    /**
     * 校验数据源名称对唯一性
     * @param ftpSourceManager
     * @return 未被使用，返回true
     */
    boolean checkUniqueNickName(FtpSourceManager ftpSourceManager);
}
