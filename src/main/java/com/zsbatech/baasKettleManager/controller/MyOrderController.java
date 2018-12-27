package com.zsbatech.baasKettleManager.controller;

import com.alibaba.fastjson.JSON;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.MyOrderService;
import com.zsbatech.baasKettleManager.service.MyProductService;
import com.zsbatech.baasKettleManager.service.MyUserService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/order")

public class MyOrderController {

    @Autowired
    private MyOrderService orderService;

    //获取所有商品的图片，名称和价格
    @RequestMapping(value = "/creatOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> createDataSource(HttpServletRequest request,
                                                       @RequestBody WriteOrder writeorder) {
        ResponseData<String> responseData = new ResponseData<>();
        String result= orderService.creatOrder(writeorder);



        if(result!=null){
    //            responseData.setOK("success", "获取列表成功！");
                responseData.setOK("success", result);
//            responseData.setOK(200, "success", result);
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

}
