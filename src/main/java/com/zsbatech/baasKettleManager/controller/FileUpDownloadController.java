package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件上传下载管理
 */
@Api(protocols = "http,https", tags = {"FileUpDownload"}, value = "/data_center/file", description = "文件上传下载管理")
@RestController
@RequestMapping(value = "/data_center/file")
public class FileUpDownloadController {

    private static Logger logger = LoggerFactory.getLogger(FileUpDownloadController.class);

    @Autowired
    private FileUpDownloadService fileService;

    @Autowired
    private CatalogManageService catalogService;

    @ApiOperation(value = "上传文件到指定目录-支持多文件上传，无法使用swagger测试", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "上传成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token")
            }
    )
    @RequestMapping(value = "/multipleUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> multipleFilesUpload(HttpServletRequest request) {
        ResponseData<String> responseData = new ResponseData<>();
        UniToken uniToken = JWTUtils.validateTokenAndOrgan(request);

        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");

        if (files == null || files.isEmpty()) {
            responseData.setError("empty file!");
            return responseData;
        }

        String catalogIdStr = request.getParameter("catalog_id");

        String fileDirectory = "";
        Integer catalogId = 0;
        if (catalogIdStr != null) {
            catalogId = Integer.valueOf(catalogIdStr);
            fileDirectory = catalogService.getFullPathByCatalogId(catalogId);
        } else {
            
        }

        boolean result = fileService.multiFilesUpload(files, uniToken.getOrganization(), fileDirectory, catalogId);
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
                    @ApiImplicitParam(paramType = "query", name = "user_id", dataType = "String", required = true, value = "用户")
            }
    )
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseData<String> fileDownload(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(name = "file_id", required = true) Integer fileId,
                                             @RequestParam(name = "user_id", required = true) String userId) {
        //UniToken uniToken = JWTUtils.validateToken(request);
        ResponseData<String> responseData = new ResponseData<>();
        boolean result = fileService.fileDownload(fileId, response, userId);
        if(result) {
            responseData.setOK("success", "success");
        }else{
            responseData.setError("fail", "no such file");
        }

        return responseData;
    }
}

