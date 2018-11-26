package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.dao.FtpSourceManagerMapper;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FTP数据源管理接口实现类
 */
@Service
public class FtpSourceManageServiceImpl implements FtpSouceManageService {
    @Autowired
    private FtpSourceManagerMapper ftpSourceMapper;

    @Override
    public boolean createDataSource(FtpSourceManager ftpSource) {
        ftpSource.setCreateTime(DateUtils.currentDateTime());
        int result = ftpSourceMapper.insert(ftpSource);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateDataSource(FtpSourceManager ftpSource) {
        ftpSource.setUpdateTime(DateUtils.currentDateTime());
        int result = ftpSourceMapper.updateByPrimaryKeySelective(ftpSource);

        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Pagination<FtpSourceManager> getDataSources(Integer currPage, Integer pageSize, FtpSourceManager ftpSource) {
        PageMethod.startPage(currPage, pageSize);
        List<FtpSourceManager> ftpSourceList = ftpSourceMapper.getFtpSourcesByParam(ftpSource);
        Pagination<FtpSourceManager> sourceInfo = new Pagination<FtpSourceManager>(ftpSourceList);
        return sourceInfo;
    }
}
