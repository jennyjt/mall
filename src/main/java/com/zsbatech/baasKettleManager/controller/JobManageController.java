package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.service.impl.JobManageServiceImpl;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/12/5
 */
@Api(protocols = "http,https", tags = {"JobManage"}, value = "/jobManage", description = "job管理控制器")
@RestController
@RequestMapping(value = "/jobManage")
public class JobManageController {

    @Autowired
    private JobManageService jobManageService;

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @ApiOperation(value = "停止job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "停止job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/stopJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> stopJob(@RequestParam String jobName) {
        ResponseData<String> responseData = new ResponseData<>();
        if(jobManageService.stop(jobName)){
            responseData.setOK(200,"job已经停止",jobName);
        }else {
            responseData.setError("job停止失败");
        }
        return responseData;
    }

    @ApiOperation(value = "启动job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "启动job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/startJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> startJob(@RequestParam String jobName) {
        ResponseData<String> responseData = new ResponseData<>();
        String jobFile = fileSyncJobService.selectByJobName(jobName);
        if (jobFile != null) {
            if(jobManageService.executeJob(jobFile) == 0) {
                responseData.set(200, "启动成功", "SUCCESS");
            }else {
                responseData.setError("启动失败");
            }
        }else if(jobFile == null) {
            responseData.setOK(200,"启动失败","未找到job文件");
        }else {
            responseData.setError("启动失败");
        }
        return responseData;
    }

    @ApiOperation(value = "修改job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> modifyJob(@RequestParam String jobName) {
        ResponseData<String> responseData = new ResponseData<>();
        return responseData;
    }

    @ApiOperation(value = "停止job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "停止job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/stopJobs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List> stopJobs(@RequestBody List<String> jobNames) {
        ResponseData<List> responseData = new ResponseData<>();
        if(jobManageService.stopJobs(jobNames)){
            responseData.setOK(200,"job已经停止",jobNames);
        }else {
            responseData.setError("job停止失败");
        }
        return responseData;
    }
}
