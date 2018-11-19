package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DbConnection;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import com.zsbatech.base.model.UniToken;
import com.zsbatech.base.utils.JWTUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源管理
 */
@Api(protocols = "http,https", tags = {"dataSource"}, value = "/kettle/datasource", description = "数据源管理")
@RestController
@RequestMapping(value = "/kettle/datasource")
public class DataSourceController {

    private static Logger logger = LoggerFactory.getLogger(DataSourceController.class);

    private DataSouceManageService dbManage;

    @ApiOperation(value = "数据源新增", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "新增成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Integer> createDataSource(HttpServletRequest request,
                                                     @RequestBody DbConnection dbConnection) {
        ResponseData<Integer> responseData = new ResponseData<>();

        //UniToken uniToken = JWTUtils.validateToken(request);

        boolean result = dbManage.createDataSource(dbConnection);
        if(result){
            responseData.setOK("success", dbConnection.getId());
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "数据源修改", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Integer> updateDataSource(HttpServletRequest request,
                                                  @RequestBody DbConnection dbConnection) {
        ResponseData<Integer> responseData = new ResponseData<>();

        //UniToken uniToken = JWTUtils.validateToken(request);

        boolean result = dbManage.updateDataSource(dbConnection);
        if(result){
            responseData.setOK("success", dbConnection.getId());
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "数据源查询", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = "job_id", dataType = "String", required = true, value = "jobId"),
                    @ApiImplicitParam(paramType = "query", name = "step_id", dataType = "String", required = false, value = "stepId"),
            }
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<DbConnection>> getDataSource(HttpServletRequest request,
                                                          @RequestParam(name = "job_id", required = true) Integer jobId,
                                                          @RequestParam(name = "step_id", required = true) Integer stepId) {
        ResponseData<List<DbConnection>> responseData = new ResponseData<>();

        //UniToken uniToken = JWTUtils.validateToken(request);

        List<DbConnection> result = dbManage.getDataSources(jobId, stepId);
        if(result != null){
            responseData.setOK("success", result);
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }
}

