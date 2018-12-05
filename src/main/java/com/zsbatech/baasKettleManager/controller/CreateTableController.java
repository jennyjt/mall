package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;

import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dataCenter/database")
public class CreateTableController {
    @Autowired
    private CreateTableService createTableService;

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> createTable(@RequestBody DataMig dataMig) {


        ResponseData responseData = createTableService.createTable(dataMig);

            return responseData;
        }

    @RequestMapping(value = "/getcolumns", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> getColumns(@RequestBody DataMig dataMig) {


        ResponseData responseData = createTableService.getColumns(dataMig);

        return responseData;
    }

}
