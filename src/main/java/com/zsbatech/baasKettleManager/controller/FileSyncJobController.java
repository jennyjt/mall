package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.vo.FTPSyncSetp;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    private SaveJobMetaService saveJobMetaService;

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
    public ResponseData<String> createDownloadJobMeta(@RequestBody FTPSyncSetp ftpSyncSetp) {
        ResponseData<String> responseData = new ResponseData<>();
        String fileName = fileSyncJobService.createDownloadJobMeta(ftpSyncSetp.getJobStartStepVO(), ftpSyncSetp.getFtpDownLoadStepVO(), ftpSyncSetp.getFileName());
        if (fileName != null) {
            if (saveJobMetaService.saveFTPJobData(fileName)) {
                responseData.setOK(200, "创建文件同步job成功", "success");
            }else {
                responseData.setError("创建文件同步job失败");
            }
        } else {
            responseData.setError("创建文件同步job失败");
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
    public ResponseData<String> createPutJobMeta(@RequestBody FTPSyncSetp ftpSyncSetp) {
        ResponseData<String> responseData = new ResponseData<>();
        String fileName = fileSyncJobService.createPutJobMeta(ftpSyncSetp.getJobStartStepVO(), ftpSyncSetp.getFtpPutStepVO(), ftpSyncSetp.getFileName());
        if(fileName != null) {
            if(saveJobMetaService.saveFTPJobData(fileName)) {
                responseData.setOK(200, "创建文件上传同步job成功", "SUCCESS");
            }else {
                responseData.setError("创建文件上传同步job失败");
            }
        } else {
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
    public ResponseData<String> fileSyncFtpToFtpJobMeta(@RequestBody FTPSyncSetp ftpSyncSetp) {
        ResponseData<String> responseData = new ResponseData<>();
        String fileName = fileSyncJobService.fileSyncFtpToFtpJobMeta(ftpSyncSetp.getJobStartStepVO(), ftpSyncSetp.getFtpPutStepVO(), ftpSyncSetp.getFtpDownLoadStepVO(), ftpSyncSetp.getFileName());
        if(fileName != null){
            if(saveJobMetaService.saveFTPJobData(fileName)) {
                responseData.setOK(200, "创建ftp文件同步job成功", "success");
            }else {
                responseData.setError("创建ftp文件同步job失败");
            }
        } else {
            responseData.setError("创建ftp文件同步job失败");
        }
        return responseData;
    }
}

