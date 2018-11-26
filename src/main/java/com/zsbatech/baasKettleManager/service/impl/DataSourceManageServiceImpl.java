package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据源管理接口实现类
 */
@Service
public class DataSourceManageServiceImpl implements DataSouceManageService {
    @Autowired
    private DbManagementMapper dbMapper;

    @Override
    public boolean createDataSource(DbManagement dbConnection) {
        dbConnection.setCreateTime(DateUtils.currentDateTime());
        int result = dbMapper.insert(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateDataSource(DbManagement dbConnection) {
        dbConnection.setUpdatedTime(DateUtils.currentDateTime());
        int result = dbMapper.updateByPrimaryKeySelective(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, DbManagement dbManagement) {
        PageMethod.startPage(currPage, pageSize);
        List<DbManagement> dataSourceList = dbMapper.getDbManagentsByParam(dbManagement);
        Pagination<DbManagement> dataSourceInfo = new Pagination<DbManagement>(dataSourceList);
        return dataSourceInfo;
    }
}
