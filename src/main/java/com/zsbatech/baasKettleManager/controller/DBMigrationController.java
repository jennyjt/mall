package com.zsbatech.baasKettleManager.controller;



import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.DbResponse;
import com.zsbatech.baasKettleManager.service.DBMigrationService;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping (value = "/database")
public class DBMigrationController {

    @Autowired
    private DBMigrationService dbMigrationService;


    @RequestMapping(value = "/migrate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> createMigration(@RequestBody DataMig dataMig) {
        ResponseData<String> responseData = new ResponseData<>();

             responseData = dbMigrationService.createMigration(dataMig);
            return responseData;

    }

}



