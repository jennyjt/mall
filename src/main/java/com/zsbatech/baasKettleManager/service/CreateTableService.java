package com.zsbatech.baasKettleManager.service;


import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.base.common.ResponseData;

public interface CreateTableService {
    ResponseData<String> createTable(DataMig dataMig);
    ResponseData<String> getColumns(DataMig dataMig);
    ResponseData<String> getTables(DataMig dataMig);
}
