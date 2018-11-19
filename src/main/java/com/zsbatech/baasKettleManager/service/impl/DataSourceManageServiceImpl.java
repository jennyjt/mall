package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DbConnectionMapper;
import com.zsbatech.baasKettleManager.model.DbConnection;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据源管理接口实现类
 */
@Service
public class DataSourceManageServiceImpl implements DataSouceManageService {
    @Autowired
    private DbConnectionMapper dbConnectionMapper;

    @Override
    public boolean createDataSource(DbConnection dbConnection) {
        int result = dbConnectionMapper.insert(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateDataSource(DbConnection dbConnection) {
        int result = dbConnectionMapper.updateByPrimaryKeySelective(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<DbConnection> getDataSources(Integer jobId, Integer stepId) {
        return dbConnectionMapper.getConnectionList(jobId, stepId);
    }
}
