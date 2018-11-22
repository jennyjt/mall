package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.model.CreateTable;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.stereotype.Service;

@Service
public class CreateTableServiceImpl implements CreateTableService {


    public ResponseData<String> createTable(CreateTable createTable) {
        ResponseData<String> responseData = new ResponseData<>();

       String tbname = createTable.getTableName();
       responseData.setOK(200,"success","success");

        return responseData;
    }

}
