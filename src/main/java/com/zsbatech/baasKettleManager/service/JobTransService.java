package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.base.common.ResponseData;

public interface JobTransService {

    ResponseData<String> modifyJob(DataMig dataMig);
    ResponseData<String> modifyTrans(DataMig dataMig);

}
