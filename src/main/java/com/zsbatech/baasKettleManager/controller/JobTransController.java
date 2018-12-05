package com.zsbatech.baasKettleManager.controller;

import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.service.JobTransService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/dataCenter/jobtrans/modify")
public class JobTransController {

        @Autowired
        private JobTransService jobTransService;

        @RequestMapping(value = "/job",method = RequestMethod.POST)
        @ResponseBody
        public ResponseData<String> modifyJob(@RequestBody DataMig dataMig) {
            ResponseData<String> responseData = new ResponseData<>();

            responseData = jobTransService.modifyJob(dataMig);
            return responseData;

        }

         @RequestMapping(value = "/trans",method = RequestMethod.POST)
          @ResponseBody
         public ResponseData<String> modifyTrans(@RequestBody DataMig dataMig) {
            ResponseData<String> responseData = new ResponseData<>();

             responseData = jobTransService.modifyTrans(dataMig);
             return responseData;

    }
}
