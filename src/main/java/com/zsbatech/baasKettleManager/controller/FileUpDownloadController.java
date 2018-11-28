package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.baasKettleManager.util.ConfigUtil;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import com.zsbatech.base.model.UniToken;
import com.zsbatech.base.utils.DateUtils;
import com.zsbatech.base.utils.JWTUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

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
        UniToken uniToken = JWTUtils.validateToken(request);

        if(file.isEmpty()){
            responseData.setError("empty file!");
            return responseData;
        }

        //文件要上传的路径
        String path = ConfigUtil.getPropertyValue("file.httpDirectory");
        String oldFileName = file.getOriginalFilename();
        String newFileName = DateUtils.currentTimestamp() + file.getContentType();
        path = path + File.separator + newFileName;
        File targetFile = new File(path);
        //如果目标文件路径不存在就新建
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        try {
            //文件复制
            file.transferTo(targetFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            responseData.setError("fail", "file upload fail");
            return responseData;
        } catch (IOException e) {
            e.printStackTrace();
            responseData.setError("fail", "file upload fail");
            return responseData;
        }

        responseData.setOK("success", "success");
        return responseData;
    }

    @ApiOperation(value = "下载文件", notes = "", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "下载成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public void fileDownload(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "file_id", required = true) Integer fileId) {
        //UniToken uniToken = JWTUtils.validateToken(request);

        String filePath = "D:\\test\\git-cheatsheet.pdf";
        String fileName = "测试呀呀呀有.pdf";

        FileInputStream is = null;
        try {
            File file = new File(filePath);
            //判断文件是否存在
            if(!file.exists()) {
                return;
            }

            response.setContentLengthLong(file.length());
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            //设置文件响应大小
            response.setContentLengthLong(file.length());
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            is = new FileInputStream(file);

            //copy里面关闭流
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

