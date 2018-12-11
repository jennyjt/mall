package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.service.JobManageService;
import com.zsbatech.baasKettleManager.service.SaveJobMetaService;
import com.zsbatech.baasKettleManager.service.SaveTransMetaService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/data_center/job/test")
public class JobTestController {

    @Autowired
    private SaveJobMetaService saveJobMetaService;

    @Autowired
    private JobManageService jobExcuteService;
    @Autowired
    SaveTransMetaService saveTransMetaService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> createMigration(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
        System.out.println(dataMig.getSrcTable());
        jobExcuteService.stop(dataMig.getSrcTable());
        responseData.setOK(200, "success", "success");
        return responseData;
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> saveByDb(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();
//        saveJobMetaService.saveFtpIncrJobByDB(dataMig.getJobName());
        saveJobMetaService.saveIncrJobByDB("job");
        responseData.setOK(200, "success", "success");
        return responseData;
    }
}
