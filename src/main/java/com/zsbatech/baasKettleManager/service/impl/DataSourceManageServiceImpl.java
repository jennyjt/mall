package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.constants.DataSourceConstant;
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
        dbConnection.setStatus(DataSourceConstant.NORMAL_STATUS);
        dbConnection.setUseCount((short) 0);
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

        if(result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, DbManagement dbManagement) {
        PageMethod.startPage(currPage, pageSize);
        dbManagement.setStatus(DataSourceConstant.NORMAL_STATUS);
        List<DbManagement> dataSourceList = dbMapper.getDbManagentsByParam(dbManagement);
        Pagination<DbManagement> dataSourceInfo = new Pagination<DbManagement>(dataSourceList);
        return dataSourceInfo;
    }

    @Override
    public boolean deleteDataSource(Integer id) {
        DbManagement param = new DbManagement();
        param.setId(id);
        param.setStatus(DataSourceConstant.DELETE_STATUS);
        int result = dbMapper.updateByPrimaryKeySelective(param);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean decreaseUseCount(Integer id) {
        int result = dbMapper.decreaseUseCount(id);
        if(result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean increaseUseCount(Integer id) {
        int result = dbMapper.increaseUseCount(id);
        if(result <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
