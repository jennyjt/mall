package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.baasKettleManager.service.FtpSouceManageService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * FTP数据源管理
 */
@Api(protocols = "http,https", tags = {"FtpSource"}, value = "/dataCenter/ftpsource", description = "FTP数据源管理")
@RestController
@RequestMapping(value = "/dataCenter/ftpsource")
public class FtpSourceController {

    private static Logger logger = LoggerFactory.getLogger(FtpSourceController.class);

    @Autowired
    private FtpSouceManageService ftpService;

    @ApiOperation(value = "FTP数据源新增", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "新增成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> createFtpDataSource(HttpServletRequest request,
                                                        @RequestBody FtpSourceManager ftpSourceManager) {
        ResponseData<String> responseData = new ResponseData<>();

        //UniToken uniToken = JWTUtils.validateToken(request);
        boolean result = ftpService.createDataSource(ftpSourceManager);
        if(result){
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "FTP数据源修改", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> updateFtpDataSource(HttpServletRequest request,
                                                    @RequestBody FtpSourceManager ftpSourceManager) {
        ResponseData<String> responseData = new ResponseData<>();

        boolean result = ftpService.updateDataSource(ftpSourceManager);
        if(result){
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "FTP数据源查询", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<FtpSourceManager>> getFtpDataSourceList(HttpServletRequest request,
                                                                       @RequestParam(name = "curr_page", defaultValue = "1") Integer currPage,
                                                                       @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(name = "ftp_host", required = false) String ftpHost,
                                                                       @RequestParam(name = "user_id", required = false) String createUser) {
        ResponseData<Pagination<FtpSourceManager>> responseData = new ResponseData<>();

        FtpSourceManager param = new FtpSourceManager();
        param.setFtpHost(ftpHost);
        param.setCreateUser(createUser);
        Pagination<FtpSourceManager> result = ftpService.getDataSources(currPage, pageSize, param);
        if(result != null){
            responseData.setOK("success", result);
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }
}

