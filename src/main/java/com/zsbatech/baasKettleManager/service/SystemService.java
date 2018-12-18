package com.zsbatech.baasKettleManager.service;


import com.zsbatech.baasKettleManager.model.SmParamDetail;

import java.util.Date;
import java.util.List;

public interface SystemService {

    public List<SmParamDetail> getParamDetailByType(String type) throws Exception;

    public SmParamDetail getParamDetailByTypeAndCode(String type, String code) throws Exception;

    public Date getSystemDate() throws Exception;

    public Date getDbDate() throws Exception;

}
