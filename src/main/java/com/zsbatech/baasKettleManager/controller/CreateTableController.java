package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;

import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(value = "/data_center/database")

@RequestMapping(value = "/a")
public class CreateTableController {
    @Autowired
    private CreateTableService createTableService;

    @ApiOperation(value = "建标", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "建表成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> createTable(@RequestBody DataMig dataMig) {


        ResponseData responseData = createTableService.createTable(dataMig);

            return responseData;
        }




    @ApiOperation(value = "获取列名", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getcolumns", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> getColumns(@RequestBody DataMig dataMig) {


        ResponseData responseData = createTableService.getColumns(dataMig);

        return responseData;
    }


    @ApiOperation(value = "获取表名", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/gettables", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseData<String> getTables(@RequestBody DataMig dataMig) {


        ResponseData responseData = createTableService.getTables(dataMig);

        return responseData;
    }

}
