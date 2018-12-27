package com.zsbatech.baasKettleManager.controller;


import com.zsbatech.baasKettleManager.model.DataMig;
import com.zsbatech.baasKettleManager.model.MyUser;
import com.zsbatech.baasKettleManager.model.WriteCart;
import com.zsbatech.baasKettleManager.service.AddInCartService;
import com.zsbatech.baasKettleManager.service.CreateTableService;
import com.zsbatech.base.common.ResponseData;
import com.zsbatech.base.constants.RequestField;
import com.zsbatech.base.constants.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")


public class AddInCart {
    @Autowired
    private AddInCartService addInCartService;

    @RequestMapping(value = "/addin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> addData(HttpServletRequest request,
                                        @RequestBody WriteCart CartInput) {
        ResponseData<String> responseData = new ResponseData<>();

        //校验数据源是否能够连接
        boolean result = userService.add(user);



        if(result){
            responseData.setOK("success", "添加成功！");
        }else{
            responseData.setError("fail");
        }
        System.out.println();
        return responseData;
    }
