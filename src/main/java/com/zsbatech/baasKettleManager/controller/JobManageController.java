package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.vo.FTPSyncSetp;
import com.zsbatech.baasKettleManager.vo.JobInfo;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<String> modifyJob(@RequestParam FTPSyncSetp ftpSyncSetp) {
        ResponseData<String> responseData = new ResponseData<>();
        if(jobManageService.modifyJob(ftpSyncSetp)) {
            responseData.setOK(200, "修改成功", ftpSyncSetp.getJobName());
        }else {
            responseData.setError(500,"修改失败",ftpSyncSetp.getJobName());
        }
        return responseData;
    }

    @ApiOperation(value = "删除job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> stopJobs(@RequestParam String jobName) {
        ResponseData<String> responseData = new ResponseData<>();
        if(jobManageService.removeJob(jobName)){
            responseData.setOK(200,"job删除成功","SUCCESS");
        }else {
            responseData.setError("job停止失败");
        }
        return responseData;
    }

    @ApiOperation(value = "查询文件job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryJob", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<JobInfo> queryJob(@RequestParam String jobName) {
        ResponseData<JobInfo> responseData = new ResponseData<>();
        JobInfo jobInfo = jobManageService.queryJobInfo(jobName);
          if(jobInfo != null){
              responseData.setOK(200,"查询成功",jobInfo);

        }else {
              responseData.setOK(200,"job不存在",null);
          }
        return responseData;
    }

}
