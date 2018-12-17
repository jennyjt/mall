package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.JobMetaVO;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FTP数据源管理接口实现类
 */
@Service
public class FtpSourceManageServiceImpl implements FtpSouceManageService {

    @Autowired
    private JobMetaVOMapper jobMetaVOMapper;

    @Autowired
    private FTPPutStepVOMapper ftpPutStepVOMapper;

    @Autowired
    private FTPDownLoadStepVOMapper ftpDownLoadStepVOMapper;

    @Autowired
    private FtpSourceManageVOMapper ftpSourceManageVOMapper;
    @Autowired
    private FtpSourceManagerMapper ftpSourceMapper;

    @Override
    public boolean createDataSource(FtpSourceManager ftpSource) {
        ftpSource.setCreateTime(DateUtils.currentDateTime());
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
        List<FtpSourceManager> ftpSourceList = ftpSourceMapper.getFtpSourcesByParam(ftpSource);
        Pagination<FtpSourceManager> sourceInfo = new Pagination<FtpSourceManager>(ftpSourceList);
        return sourceInfo;
    }

    public boolean isSourceUsed(String nickName) {
        boolean isSourceUsed = false;
        int sourceId = ftpSourceManageVOMapper.selectByName(nickName).getId();
        List<FTPDownLoadStepVO> ftpDownLoadStepVOS= ftpDownLoadStepVOMapper.selectBySourceId(sourceId);
        Set<Integer> sourceIdSet = new HashSet<>();
        for(FTPDownLoadStepVO ftpDownLoadStepVO:ftpDownLoadStepVOS){
           sourceIdSet.add(ftpDownLoadStepVO.getFtpSourceId());
        }
        List<FTPPutStepVO> ftpPutStepVOS= ftpPutStepVOMapper.selectBySourceId(sourceId);
        for(FTPPutStepVO ftpPutStepVO:ftpPutStepVOS){
            sourceIdSet.add(ftpPutStepVO.getFtpSourceId());
        }
       JobMetaVO jobMetaVO = jobMetaVOMapper.selectById(new ArrayList<>(sourceIdSet));
        if(jobMetaVO != null){
            isSourceUsed = true;
        }
        return isSourceUsed;
    }
}
