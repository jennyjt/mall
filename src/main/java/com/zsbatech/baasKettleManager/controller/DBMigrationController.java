package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.model.JobMeta;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
//@RequestMapping (value = "/data_center/migrate")

@RequestMapping (value = "/a")
public class DBMigrationController {

    @Autowired
    private DBMigrationService dbMigrationService;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> createMigration(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

             responseData = dbMigrationService.createMigration(dataMig);
            return responseData;

    }

    @RequestMapping(value = "/cycle",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> cycleMigration(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

        responseData = dbMigrationService.cycleMigration(dataMig);
        return responseData;

    }

    @RequestMapping(value = "/incr",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<DbResponse> insertupdateMigration(@RequestBody DataMig dataMig) {
        ResponseData<DbResponse> responseData = new ResponseData<>();

        responseData = dbMigrationService.insertupdateMigration(dataMig);
        return responseData;

    }


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<JobMeta>> getJobList(HttpServletRequest request,
                                                        @RequestParam(name = "curr_page", defaultValue = "1") Integer currPage,
                                                        @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize)
                                                        {
        ResponseData<Pagination<JobMeta>> responseData = new ResponseData<>();

        Pagination<JobMeta> result  = dbMigrationService.getJobList(currPage, pageSize);
        if (result != null) {
            responseData.setOK(200, "success", result);
        }else{
            responseData.setError("Fail!");
        }
        return responseData;

    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<JobMeta> getJobDetail(HttpServletRequest request,
                                              @RequestParam(name = "jobId", defaultValue = "1") Integer jobId) {
        ResponseData<JobMeta> responseData = new ResponseData<>();

      JobMeta  result = dbMigrationService.getJobDetail(jobId);
      if (result != null) {
          responseData.setOK(200, "success", result);
      }else{
          responseData.setError("Fail!");
      }
        return responseData;

    }


}



