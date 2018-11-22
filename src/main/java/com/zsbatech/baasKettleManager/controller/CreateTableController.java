package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.CreateTable;
import com.zsbatech.baasKettleManager.model.DbResponse;

import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateTableController {
    @Autowired
    private CreateTableService createTableService;

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> createTable(@RequestBody CreateTable createTable) {


        ResponseData responseData = createTableService.createTable(createTable);

            return responseData;
        }

}
