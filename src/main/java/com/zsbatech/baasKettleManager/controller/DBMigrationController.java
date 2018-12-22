package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbJobInfo;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping (value = "/data_center/migrate")



public class DBMigrationController {

    @Autowired
    private DBMigrationService dbMigrationService;
    @Autowired
    private JobTransService jobTransService;



    @ApiOperation(value = "创建任务", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "创建成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/cycle",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> cycleMigration(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();


        if(dataMig.getCreateOrUpdate()==1) {
            responseData = jobTransService.modifyJob(dataMig);
            return responseData;
        }

        boolean result = dbMigrationService.checkUniqueJobName(dataMig.getJobName());
        if(!result){
            responseData.setOK(200,"任务名称已存在!",dataMig.getJobName());
            return responseData;
        }

        responseData = dbMigrationService.cycleMigration(dataMig);
        return responseData;

    }



    @ApiOperation(value = "获取任务列表", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<DbJobInfo>> getJobList(HttpServletRequest request,
                                                        @RequestParam(name = "curr_page", defaultValue = "1") Integer currPage,
                                                        @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize)
                                                        {
        ResponseData<Pagination<DbJobInfo>> responseData = new ResponseData<>();

        Pagination<DbJobInfo> result  = dbMigrationService.getJobList(currPage, pageSize);
        if (result != null) {
            responseData.setOK(200, "success", result);
        }else{
            responseData.setError("Fail!");
        }
        return responseData;

    }




    @ApiOperation(value = "获取任务详情", notes = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})

    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = RequestField.TOKEN, dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<DbJobInfo> getJobDetail(HttpServletRequest request,
                                              @RequestParam(name = "jobName") String jobName) {
        ResponseData<DbJobInfo> responseData = new ResponseData<>();

        DbJobInfo result = dbMigrationService.getJobDetail(jobName);
      if (result.getJobName() != null) {
          responseData.setOK(200, "success", result);
      }else{
          responseData.setError("Fail!");
      }
        return responseData;

    }


}



