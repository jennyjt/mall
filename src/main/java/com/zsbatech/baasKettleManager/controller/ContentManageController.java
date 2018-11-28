package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Zhangys
 * Date: 2018/11/20
 */
@Api(protocols = "http,https", tags = {"CatalogManage"}, value = "/catalog", description = "文件目录管理接口")
@RestController
@RequestMapping(value = "/catalog")
public class ContentManageController {

    private static Logger logger = LoggerFactory.getLogger(ContentManageController.class);

    @Autowired
    private CatalogManageService catalogManageService;

    @ApiOperation(value = "删除文件目录", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/deleteCatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Map> deleteFileCatalogs(@RequestBody List<String> fileCatalogs) {
        Map<String, List<String>> map = catalogManageService.deleteCatalogs(fileCatalogs);
        ResponseData<Map> responseData = new ResponseData<>();
        if (map != null) {
            responseData.setOK("删除成功", map);
        } else {
            responseData.setError(500, new HashMap<String, String>().put("删除失败", "删除文件目录失败"));
        }
        return responseData;
    }

    @ApiOperation(value = "创建文件目录", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "创建文件目录"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/createFileCatalogs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Map> createFileCatalogs(@RequestBody List<String> fileCatalogs) {
        Map<String, List<String>> map = catalogManageService.createCatalogs(fileCatalogs);
        ResponseData<Map> responseData = new ResponseData<>();
        if (map != null) {
            responseData.setOK("创建成功", map);
        } else {
            responseData.setError(500, new HashMap<String, String>().put("创建失败", "创建文件目录失败"));
        }
        return responseData;
    }

    @ApiOperation(value = "删除文件", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除文件"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Map> deleteFiles(@RequestBody List<String> files) {
        Map<String, List<String>> map = catalogManageService.deleteFiles(files);
        ResponseData<Map> responseData = new ResponseData<>();
        if (map != null) {
            responseData.setOK("创建成功", map);
        } else {
            responseData.setError(500, new HashMap<String, String>().put("创建失败", "创建文件目录失败"));
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
    @RequestMapping(value = "/queryFilesByfileName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List> queryFilesByfileName(@RequestBody List<String> fileNames) {
        List<String> fileList = catalogManageService.queryFiles(fileNames);
        ResponseData<List> responseData = new ResponseData<>();
        if (fileList != null) {
            responseData.setError("文件未找到");
        } else {
            responseData.setOK("查询成功",fileList);
        }
        return responseData;
    }
}
