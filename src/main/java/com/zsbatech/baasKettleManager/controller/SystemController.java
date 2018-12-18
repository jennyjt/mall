package com.zsbatech.baasKettleManager.controller;


import com.zsbatech.baasKettleManager.model.SmParamDetail;
import com.zsbatech.baasKettleManager.service.SystemService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Api(protocols = "http,https", tags = {"System"}, value = "/data_center/system", description = "系统管理")
@RestController
@RequestMapping(value = "/data_center/system")
public class SystemController {

    private static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;


    @ApiOperation(value = "码表列表", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = Response.OK, message = "查询码表列表成功"),
    })
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TIMESTAMP, dataType = "Long", required = false, value = "timestamp"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.SIGN, dataType = "String", required = false, value = "sign", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TYPE, dataType = "String", required = true, value = "类型"),
            }
    )
    @RequestMapping(value = "/getByType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<SmParamDetail>> getByType(HttpServletRequest request,
                                                       @RequestParam(name = RequestField.TIMESTAMP, required = false) Long timestamp,
                                                       @RequestParam(name = RequestField.SIGN, required = false) String sign,
                                                       @RequestParam(name = RequestField.TYPE, required = true) String type
    ) throws Exception {
        ResponseData<List<SmParamDetail>> responseData = new ResponseData<>();
        List<SmParamDetail> smParamDetails = systemService.getParamDetailByType(type);
        responseData.setResult(smParamDetails);
        return responseData;
    }

    @ApiOperation(value = "码表转义", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = Response.OK, message = "查询码表成功"),
    })
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TIMESTAMP, dataType = "Long", required = false, value = "timestamp"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.SIGN, dataType = "String", required = false, value = "sign", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TYPE, dataType = "String", required = true, value = "类型"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.CODE, dataType = "String", required = true, value = "参数"),
            }
    )
    @RequestMapping(value = "/getByTypeAndCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<SmParamDetail> getByTypeAndCode(HttpServletRequest request,
                                                    @RequestParam(name = RequestField.TIMESTAMP, required = false) Long timestamp,
                                                    @RequestParam(name = RequestField.SIGN, required = false) String sign,
                                                    @RequestParam(name = RequestField.TYPE, required = true) String type,
                                                    @RequestParam(name = RequestField.CODE, required = true) String code
    ) throws Exception {
        ResponseData<SmParamDetail> responseData = new ResponseData<>();
        SmParamDetail smParamDetail = systemService.getParamDetailByTypeAndCode(type,code);
        responseData.setResult(smParamDetail);
        return responseData;
    }


    @ApiOperation(value = "码表转义内容", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = Response.OK, message = "查询码表转义内容成功"),
    })
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TIMESTAMP, dataType = "Long", required = false, value = "timestamp"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.SIGN, dataType = "String", required = false, value = "sign", defaultValue = "test"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TYPE, dataType = "String", required = true, value = "类型"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.CODE, dataType = "String", required = true, value = "参数"),
            }
    )
    @RequestMapping(value = "/getDescByTypeAndCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> getDescByTypeAndCode(HttpServletRequest request,
                                              @RequestParam(name = RequestField.TIMESTAMP, required = false) Long timestamp,
                                              @RequestParam(name = RequestField.SIGN, required = false) String sign,
                                              @RequestParam(name = RequestField.TYPE, required = true) String type,
                                              @RequestParam(name = RequestField.CODE, required = true) String code
    ) throws Exception {
        ResponseData<String> responseData = new ResponseData<>();
        SmParamDetail smParamDetail = systemService.getParamDetailByTypeAndCode(type,code);
        responseData.setResult(smParamDetail.getParamDesc());
        return responseData;
    }


    @ApiOperation(value = "获取数据库时间", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = Response.OK, message = "获取数据库时间成功"),
    })
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.TIMESTAMP, dataType = "Long", required = false, value = "timestamp"),
                    @ApiImplicitParam(paramType = "query", name = RequestField.SIGN, dataType = "String", required = false, value = "sign", defaultValue = "test"),
            }
    )
    @RequestMapping(value = "/getDbDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> getDbDate(HttpServletRequest request,
                                                     @RequestParam(name = RequestField.TIMESTAMP, required = false) Long timestamp,
                                                     @RequestParam(name = RequestField.SIGN, required = false) String sign
    ) throws Exception {
        ResponseData<String> responseData = new ResponseData<>();
        Date date = systemService.getDbDate();
        responseData.setResult(DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));
        return responseData;
    }

}
