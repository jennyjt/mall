package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbManagementMapper;
import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 数据源管理接口实现类
 */
@Service
public class DataSourceManageServiceImpl implements DataSouceManageService {
    @Autowired
    private DbManagementMapper dbMapper;

    @Override
    public boolean createDataSource(DbManagement dbConnection) {
        dbConnection.setCreated(DateUtils.currentDateTime());
        int result = dbMapper.insert(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateDataSource(DbManagement dbConnection) {
        dbConnection.setUpdated(DateUtils.currentDateTime());
        int result = dbMapper.updateByPrimaryKeySelective(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Pagination<DbManagement> getDataSources(Integer currPage, Integer pageSize, Map<String, Object> map) {
        return null;
    }
}
