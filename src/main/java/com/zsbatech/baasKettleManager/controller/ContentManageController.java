package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.FileCatalogDO;
import com.zsbatech.baasKettleManager.model.FilesDO;
import com.zsbatech.baasKettleManager.service.CatalogManageService;
import com.zsbatech.baasKettleManager.vo.FileCatalogVO;
import com.zsbatech.baasKettleManager.vo.FileQueryVO;
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
@Api(protocols = "http,https", tags = {"CatalogManage"}, value = "/data_center/catalog", description = "文件目录管理接口")
@RestController
@RequestMapping(value = "/data_center/catalog")
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
        List<FilesDO> fileList = catalogManageService.queryFiles(fileQueryVO.getFileCatalog(), fileQueryVO.getCode());
        ResponseData<List> responseData = new ResponseData<>();
        if (fileList != null && fileList.size() != 0) {
            responseData.setOK(200,"查询成功", fileList);
        } else if(fileList == null || fileList.size() == 0) {
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
    @RequestMapping(value = "/queryCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<String>> queryCatalog( @RequestParam(name = "code") String code,@RequestParam(name = "fileName",required = false) String fileName) {
        List<String> catalogList = catalogManageService.queryCataLog( code,fileName);
        ResponseData<List<String>> responseData = new ResponseData<>();
        if (catalogList != null && catalogList.size() != 0) {
            responseData.setOK(200, "查询成功", catalogList);
        } else if(catalogList == null || catalogList.size() == 0){
            responseData.setOK(200,"目录不存在",catalogList);
        }else {
            responseData.setError("查询错误");
        }
        return responseData;
    }

    @ApiOperation(value = "获取ftp目录结构", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "获取ftp目录结构"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryFtpCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<List<FileCatalogVO>> queryFtpCatalog(@RequestParam(name = "nickName") String nickName) {
        List<FileCatalogVO> catalogList = catalogManageService.getFtpCatalog(nickName);
        ResponseData<List<FileCatalogVO>> responseData = new ResponseData<>();
        if (catalogList != null && catalogList.size() != 0) {
            responseData.setOK(200, "查询成功", catalogList);
        }else if(catalogList == null || catalogList.size() == 0) {
            responseData.setOK(200,"没有目录存在",catalogList);
        }else {
            responseData.setError("查询错误");
        }
        return responseData;
    }

    @ApiOperation(value = "获取目录的子目录", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "获取目录的子目录"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/queryNodeCatalog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<FileCatalogVO> queryNodeCatalog(@RequestParam(name = "id") Integer id) {
        FileCatalogVO fileCatalogVO = catalogManageService.queryNodeCatalog(id);
        ResponseData<FileCatalogVO> responseData = new ResponseData<>();
        if (fileCatalogVO != null ) {
            responseData.setOK(200, "查询成功", fileCatalogVO);
        }else if(fileCatalogVO == null) {
            responseData.setOK(200,"没有目录存在",null);
        }else {
            responseData.setError("查询错误");
        }
        return responseData;
    }
}
