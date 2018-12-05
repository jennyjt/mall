package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.service.FileUpDownloadService;
import com.zsbatech.baasKettleManager.vo.FileQueryVO;
import com.zsbatech.baasKettleManager.vo.FilesVO;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件交换管理
 */
@Api(protocols = "http,https", tags = {"FileSwitch"}, value = "/file", description = "文件交换管理")
@RestController
@RequestMapping(value = "/file")
public class FileSwitchController {

    private static Logger logger = LoggerFactory.getLogger(FileSwitchController.class);

    @Autowired
    private FileUpDownloadService fileService;

    @Autowired
    private CatalogManageService catalogManageService;

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

    @ApiOperation(value = "查询文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询文件"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List> queryFilesByCatalog(@RequestBody FileQueryVO fileQueryVO) {
        List<FilesVO> fileList = catalogManageService.queryFiles(fileQueryVO.getFileCatalog(), fileQueryVO.getCode());
        ResponseData<List> responseData = new ResponseData<>();
        if (fileList != null && fileList.size() != 0) {
            responseData.setOK(200,"查询成功", fileList);
        } else if(fileList == null && fileList.size() == 0) {
            responseData.setOK(200,"文件不存在",null);
        }else {
            responseData.setError("查询错误");
        }
        return responseData;
    }

    @ApiOperation(value = "查询目录", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询目录"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryCatalogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<String>> queryCatalog( @RequestParam(name = "code") String code,@RequestParam(name = "fileName",required = false) String fileName) {
        List<String> catalogList = catalogManageService.queryCataLog( code,fileName);
        ResponseData<List<String>> responseData = new ResponseData<>();
        if (catalogList != null && catalogList.size() != 0) {
            responseData.setOK(200, "查询成功", catalogList);
        } else if(catalogList ==null && catalogList.size() == 0){
            responseData.setOK(200,"目录不存在",catalogList);
        }else {
            responseData.setError("查询错误");
        }
        return responseData;
    }
}

