package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.constants.DataSourceConstant;
import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.baasKettleManager.util.FTPUtil;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.utils.DateUtils;
import org.apache.commons.net.ftp.FTPClient;
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
        ftpSource.setStatus(DataSourceConstant.NORMAL_STATUS);
        ftpSource.setUseCount((short) 0);
        int result = ftpSourceMapper.insert(ftpSource);

        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updateDataSource(FtpSourceManager ftpSource) {
        ftpSource.setUpdateTime(DateUtils.currentDateTime());
        int result = ftpSourceMapper.updateByPrimaryKeySelective(ftpSource);

        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Pagination<FtpSourceManager> getDataSources(Integer currPage, Integer pageSize, FtpSourceManager ftpSource) {
        PageMethod.startPage(currPage, pageSize);
        ftpSource.setStatus(DataSourceConstant.NORMAL_STATUS);
        List<FtpSourceManager> ftpSourceList = ftpSourceMapper.getFtpSourcesByParam(ftpSource);
        Pagination<FtpSourceManager> sourceInfo = new Pagination<FtpSourceManager>(ftpSourceList);
        return sourceInfo;
    }

    @Override
    public boolean deleteDataSource(Integer id) {
        FtpSourceManager param = new FtpSourceManager();
        param.setId(id);
        param.setStatus(DataSourceConstant.DELETE_STATUS);
        int result = ftpSourceMapper.updateByPrimaryKeySelective(param);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean decreaseUseCount(Integer id) {
        int result = ftpSourceMapper.decreaseUseCount(id);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean increaseUseCount(Integer id) {
        int result = ftpSourceMapper.increaseUseCount(id);
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isFtpConnected(FtpSourceManager ftpSourceManager) {
        FTPClient ftpClient = FTPUtil.loginFTP(ftpSourceManager.getFtpHost(), Integer.valueOf(ftpSourceManager.getFtpPort()), ftpSourceManager.getUserName(), ftpSourceManager.getPassWord());
        if (ftpClient != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUniqueNickName(FtpSourceManager ftpSourceManager) {
        List<FtpSourceManager> resultList = ftpSourceMapper.getNormalFtpSourceByNickName(ftpSourceManager.getNickName());

        if(resultList == null || resultList.isEmpty()){ //如果同名数量为0，则名称可使用
            return true;
        }else if(resultList.size() > 1){ //如果同名数量超过1，则名称不可使用
            return false;
        }else if(resultList.get(0).getId() == ftpSourceManager.getId()){ //如果同名数据源是当前数据源，则名称可使用
            return true;
        }else{
            return false;
        }
    }
}
