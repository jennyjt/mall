package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传下载管理
 */
@Api(protocols = "http,https", tags = {"FileUpDownload"}, value = "/file", description = "文件上传下载管理")
@RestController
@RequestMapping(value = "/file")
public class FileUpDownloadController {

    private static Logger logger = LoggerFactory.getLogger(FileUpDownloadController.class);

    @Autowired
    private FileUpDownloadService fileService;

    @ApiOperation(value = "上传文件到指定机器的指定目录", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "上传成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> fileUpload(HttpServletRequest request,
                                           @RequestParam(name = "file", required = true) MultipartFile file) {
        ResponseData<String> responseData = new ResponseData<>();
        //UniToken uniToken = JWTUtils.validateToken(request);

        if(file.isEmpty()){
            responseData.setError("empty file!");
            return responseData;
        }

        boolean result = fileService.fileUpload(file, "2");
        if(result) {
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail", "upload fail");
        }

        return responseData;
    }

    @ApiOperation(value = "下载文件", notes = "", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "下载成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "query", name = "file_id", dataType = "String", required = true, value = "文件id"),
                    @ApiImplicitParam(paramType = "query", name = "issuer", dataType = "String", required = true, value = "用户")
            }
    )
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseData<String> fileDownload(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(name = "file_id", required = true) Integer fileId,
                                             @RequestParam(name = "issuer", required = true) String issuer) {
        //UniToken uniToken = JWTUtils.validateToken(request);
        ResponseData<String> responseData = new ResponseData<>();
        boolean result = fileService.fileDownload(fileId, response, issuer);
        if(result) {
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail", "no such file");
        }

        return responseData;
    }
}

