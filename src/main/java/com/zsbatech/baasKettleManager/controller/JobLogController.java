package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.JobLog;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务执行日志管理
 */
@Api(protocols = "http,https", tags = {"JobLog"}, value = "/log", description = "任务执行日志管理")
@RestController
@RequestMapping(value = "/log")
public class JobLogController {

    private static Logger logger = LoggerFactory.getLogger(JobLogController.class);

    @ApiOperation(value = "查询日志信息", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
                    @ApiImplicitParam(paramType = "query", name = "job_id", dataType = "Integer", required = false, value = "任务id"),
                    @ApiImplicitParam(paramType = "query", name = "user_id", dataType = "String", required = false, value = "用户id"),
                    @ApiImplicitParam(paramType = "query", name = "job_date", dataType = "String", required = false, value = "任务执行日期")
            }
    )
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<JobLog>> fileUpload(HttpServletRequest request,
                                                 @RequestParam(name = "job_id", required = false) Integer jobId,
                                                 @RequestParam(name = "user_id", required = false) String createUser,
                                                 @RequestParam(name = "job_date", required = false) String executeDate) {
        ResponseData<List<JobLog>> responseData = new ResponseData<>();
        //UniToken uniToken = JWTUtils.validateToken(request);

        return responseData;
    }
}

