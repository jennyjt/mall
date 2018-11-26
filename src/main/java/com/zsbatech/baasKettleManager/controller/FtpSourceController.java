package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.FtpSourceManager;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据源管理
 */
@Api(protocols = "http,https", tags = {"DataSource"}, value = "/datasource", description = "数据源管理")
@RestController
@RequestMapping(value = "/datasource")
public class FtpSourceController {

    private static Logger logger = LoggerFactory.getLogger(FtpSourceController.class);


    @ApiOperation(value = "FTP数据源新增", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "新增成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> createSrcDataSourceList(HttpServletRequest request,
                                                        @RequestBody FtpSourceManager ftpSourceManager) {
        ResponseData<String> responseData = new ResponseData<>();

        //UniToken uniToken = JWTUtils.validateToken(request);

        return responseData;
    }
}

