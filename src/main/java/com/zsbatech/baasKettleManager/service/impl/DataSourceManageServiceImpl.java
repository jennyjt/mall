package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.DstDbConnectionMapper;
import com.zsbatech.baasKettleManager.dao.SrcDbConnectionMapper;
import com.zsbatech.baasKettleManager.model.DstDbConnection;
import com.zsbatech.baasKettleManager.model.SrcDbConnection;
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
    private SrcDbConnectionMapper srcDbMapper;

    @Autowired
    private DstDbConnectionMapper dstDbMapper;

    @Override
    public boolean createSrcDataSource(SrcDbConnection dbConnection) {
        int result = srcDbMapper.insert(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean createSrcDataSourceList(List<SrcDbConnection> dbConnectionList) {
        int result = srcDbMapper.insertSrcDbConnList(dbConnectionList);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean createDstDataSource(DstDbConnection dbConnection) {
        int result = dstDbMapper.insert(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateSrcDataSource(SrcDbConnection dbConnection) {
        int result = srcDbMapper.updateByPrimaryKeySelective(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateDstDataSource(DstDbConnection dbConnection) {
        int result = dstDbMapper.updateByPrimaryKeySelective(dbConnection);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<SrcDbConnection> getSrcDataSources(Integer jobId, Integer stepId) {
        return srcDbMapper.getConnectionList(jobId, stepId);
    }

    @Override
    public List<DstDbConnection> getDstDataSources(Integer jobId, Integer stepId) {
        return dstDbMapper.getConnectionList(jobId, stepId);
    }
}
