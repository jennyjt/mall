package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadSetp;
import com.zsbatech.baasKettleManager.vo.FTPDownLoadStepVO;
import com.zsbatech.baasKettleManager.vo.FTPPutStepVO;
import com.zsbatech.baasKettleManager.vo.JobStartStepVO;
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
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-09-11
 */
@Api(protocols = "http,https", tags = {"FileSync"}, value = "/fileSync", description = "ftp文件同步接口")
@RestController
@RequestMapping(value = "/fileSync")
public class FileSyncJobController {

    private static Logger logger = LoggerFactory.getLogger(FileSyncJobController.class);

    @Autowired
    private FileSyncJobService fileSyncJobService;

    @ApiOperation(value = "创建文件下载同步job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "创建文件下载同步job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/createDownloadJobMeta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> createDownloadJobMeta(@RequestBody FTPDownLoadSetp ftpDownLoadStep) {
        ResponseData<String> responseData = new ResponseData<>();
        if(fileSyncJobService.createDownloadJobMeta(ftpDownLoadStep.getJobStartStepVO(),ftpDownLoadStep.getFtpDownLoadStepVO(),ftpDownLoadStep.getFileName())){
            responseData.setOK("创建文件同步job成功","success");
        }else {
            responseData.setError("fail");
        }
        return responseData;
    }

    @ApiOperation(value = "创建文件上传同步job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "创建文件上传同步job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/createPutJobMeta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> createPutJobMeta(@RequestParam JobStartStepVO jobStartStepVO,
                                                       @RequestParam FTPPutStepVO ftpPutStepVO,
                                                       @RequestParam String fileName) {
        ResponseData<String> responseData = new ResponseData<>();
        if(fileSyncJobService.createPutJobMeta(jobStartStepVO,ftpPutStepVO,fileName)){
            responseData.setOK("创建文件上传同步job成功");
        }else {
            responseData.setError("创建文件上传同步job失败");
        }
        return responseData;
    }
    @ApiOperation(value = "ftp间文件同步job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "ftp间文件同步job"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/fileSyncFtpToFtpJobMeta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> fileSyncFtpToFtpJobMeta(@RequestBody FTPDownLoadSetp ftpDownLoadStep) {
        ResponseData<String> responseData = new ResponseData<>();
        if(fileSyncJobService.createDownloadJobMeta(ftpDownLoadStep.getJobStartStepVO(),ftpDownLoadStep.getFtpDownLoadStepVO(),ftpDownLoadStep.getFileName())){
            responseData.setOK("创建文件同步job成功","success");
        }else {
            responseData.setError("fail");
        }
        return responseData;
    }
}

