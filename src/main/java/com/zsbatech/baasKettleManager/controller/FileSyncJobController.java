package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileSyncJobService;
import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.vo.FTPSyncSetp;
import com.zsbatech.baasKettleManager.vo.JobInfo;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018-09-11
 */
@Api(protocols = "http,https", tags = {"FileSync"}, value = "/data_center/fileSync", description = "ftp文件同步接口")
@RestController
@RequestMapping(value = "/data_center/fileSync")
public class FileSyncJobController {

    private static Logger logger = LoggerFactory.getLogger(FileSyncJobController.class);

    @Autowired
    private JobManageService jobManageService;

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
        String fileName = fileSyncJobService.createDownloadJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getJobName(),ftpSyncSetp.getSrcId());
        if (fileName != null) {
            if (saveJobMetaService.saveFTPJobData(fileName,0,ftpSyncSetp.getSrcId())) {
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
        String fileName = fileSyncJobService.createPutJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(), ftpSyncSetp.getJobName(),ftpSyncSetp.getDstId());
        if(fileName != null) {
            if(saveJobMetaService.saveFTPJobData(fileName,ftpSyncSetp.getDstId(),0)) {
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
        String fileName = fileSyncJobService.fileSyncFtpToFtpJobMeta(ftpSyncSetp.getJobStartStepDO(), ftpSyncSetp.getFtpPutStepDO(),ftpSyncSetp.getSrcId(), ftpSyncSetp.getFtpDownLoadStepDO(), ftpSyncSetp.getDstId(),ftpSyncSetp.getJobName());
        if(fileName != null){
            if(saveJobMetaService.saveFTPJobData(fileName,ftpSyncSetp.getDstId(),ftpSyncSetp.getSrcId())) {
                responseData.setOK(200, "创建ftp文件同步job成功", "success");
            }else {
                responseData.setError("创建ftp文件同步job失败");
            }
        } else {
            responseData.setError("创建ftp文件同步job失败");
        }
        return responseData;
    }

    @ApiOperation(value = "查询文件同步job", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询文件同步job成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryJobInfoByPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<JobInfo>> queryJobInfoByPage(int page , @RequestParam int count) {
        ResponseData<List<JobInfo>> responseData = new ResponseData<>();
        List<JobInfo> jobInfoList = jobManageService.queryJobInfoByPage(page,count);
        if(jobInfoList != null && jobInfoList.size() != 0){
                responseData.setOK(200, "查询文件同步job", jobInfoList);
            }else if(jobInfoList == null || jobInfoList.size() == 0){
                responseData.setOK(200,"job文件不存在",null);
        } else {
            responseData.setError("查询文件同步job失败");
        }
        return responseData;
    }
}

