package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.service.CatalogManageService;
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
        if (map != null && map.size() != 0) {
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
        if (map != null && map.size() != 0) {
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
        if (map != null && map.size() != 0) {
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
    @RequestMapping(value = "/queryFilesByCatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List> queryFilesByCatalog(@RequestBody FileQueryVO fileQueryVO) {
        List<FilesVO> fileList = catalogManageService.queryFiles(fileQueryVO.getFileCatalog(), fileQueryVO.getCode());
        ResponseData<List> responseData = new ResponseData<>();
        if (fileList != null && fileList.size() != 0) {
            responseData.setOK("查询成功", fileList);
        } else {
            responseData.setError("文件未找到");
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
    @RequestMapping(value = "/queryCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Map<String, List<String>>> queryCatalog( @RequestParam(name = "code") String code,@RequestParam(name = "fileName",required = false) String fileName) {
        Map<String, List<String>> cataLogMap = catalogManageService.queryCataLog( code,fileName);
        ResponseData<Map<String, List<String>>> responseData = new ResponseData<>();
        if (cataLogMap != null && cataLogMap.size() != 0) {
            responseData.setOK(200, "查询成功", cataLogMap);
        } else {
            responseData.setError("查询失败");
        }
        return responseData;
    }
}
