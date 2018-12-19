package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/data_center/jobtrans/modify")
public class JobTransController {

        @Autowired
        private JobTransService jobTransService;


    @ApiOperation(value = "修改任务", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "创建成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
        @RequestMapping(value = "/job",method = RequestMethod.POST)
        @ResponseBody
        public ResponseData<String> modifyJob(@RequestBody DataMig dataMig) {
            ResponseData<String> responseData = new ResponseData<>();

            responseData = jobTransService.modifyJob(dataMig);
            return responseData;

        }


}
