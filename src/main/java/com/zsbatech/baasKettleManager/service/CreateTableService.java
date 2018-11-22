package com.zsbatech.baasKettleManager.service;

import com.zsbatech.baasKettleManager.model.CreateTable;

import com.zsbatech.base.common.ResponseData;

public interface CreateTableService {
    ResponseData<String> createTable(CreateTable createTable);
}
