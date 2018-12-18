package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DbManagement;
import com.zsbatech.baasKettleManager.service.DataSouceManageService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import com.zsbatech.base.model.UniToken;
import com.zsbatech.base.utils.JWTUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据源管理
 */
@Api(protocols = "http,https", tags = {"DataSource"}, value = "/data_center/datasource", description = "数据源管理")
@RestController
@RequestMapping(value = "/data_center/datasource")
public class DataSourceController {

    private static Logger logger = LoggerFactory.getLogger(DataSourceController.class);

    @Autowired
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
    public ResponseData<String> createDataSource(HttpServletRequest request,
                                                        @RequestBody DbManagement dbConnection) {
        ResponseData<String> responseData = new ResponseData<>();

        UniToken uniToken = JWTUtils.validateToken(request);

        dbConnection.setCreateUser(String.valueOf(uniToken.getUserId()));

        boolean result = dbManage.createDataSource(dbConnection);
        if(result){
            responseData.setOK("success", "success");
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
    public ResponseData<String> updateDataSource(HttpServletRequest request,
                                                  @RequestBody DbManagement dbConnection) {
        ResponseData<String> responseData = new ResponseData<>();

        boolean result = dbManage.updateDataSource(dbConnection);
        if(result){
            responseData.setOK("success", "success");
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
            }
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<DbManagement>> getDataSourceList(HttpServletRequest request,
                                                                       @RequestParam(name = "curr_page", defaultValue = "1") Integer currPage,
                                                                       @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(name = "link_name", required = false) String linkName,
                                                                       @RequestParam(name = "user_id", required = false) String createUser) {
        ResponseData<Pagination<DbManagement>> responseData = new ResponseData<>();

        DbManagement param = new DbManagement();
        param.setLinkName(linkName);
        param.setCreateUser(createUser);
        Pagination<DbManagement> result = dbManage.getDataSources(currPage, pageSize, param);
        if(result != null){
            responseData.setOK("success", result);
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "数据源删除", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> deleteDataSource(HttpServletRequest request,
                                                                    @RequestParam(name = "id", required = false) String dataSourceId) {
        ResponseData<String> responseData = new ResponseData<>();

        boolean result = dbManage.deleteDataSource(Integer.valueOf(dataSourceId));
        if(result){
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }
}

