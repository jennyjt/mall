package com.zsbatech.baasKettleManager.service.impl;


import com.zsbatech.baasKettleManager.dao.SmParamDetailMapper;
import com.zsbatech.baasKettleManager.model.SmParamDetail;
import com.zsbatech.baasKettleManager.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SmParamDetailMapper smParamDetailMapper;

    @Override
    public List<SmParamDetail> getParamDetailByType(String type) throws Exception {
        if(StringUtils.isNotBlank(type)){
            SmParamDetail smParamDetail = new SmParamDetail();
            smParamDetail.setParamType(type);
            return smParamDetailMapper.selectBySelective(smParamDetail);
        }
        return null;
    }

    @Override
    public SmParamDetail getParamDetailByTypeAndCode(String type, String code) throws Exception {
        if(StringUtils.isNotBlank(type)&& StringUtils.isNotBlank(code)){
            SmParamDetail smParamDetail = new SmParamDetail();
            smParamDetail.setParamType(type);
            smParamDetail.setParamCode(code);
            List<SmParamDetail> smParamDetails = smParamDetailMapper.selectBySelective(smParamDetail);
            if(smParamDetails != null && smParamDetails.size() > 0) {
                return smParamDetails.get(0);
            }
        }
        return null;
    }

    @Override
    public Date getSystemDate() throws Exception {
        return new Date();
    }

    @Override
    public Date getDbDate() throws Exception {
        return smParamDetailMapper.getDbDate();
    }
}
