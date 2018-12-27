package com.zsbatech.baasKettleManager.controller;


import com.zsbatech.baasKettleManager.model.Cart;
import com.zsbatech.baasKettleManager.model.OrderProduct;
import com.zsbatech.baasKettleManager.model.WriteCart;
import com.zsbatech.baasKettleManager.service.CartService;
import com.zsbatech.base.common.ResponseData;
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


public class CartController {
    @Autowired
    private CartService CartService;

    @RequestMapping(value = "/addin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<String> addIn(HttpServletRequest request,
                                        @RequestBody Cart cart) {
        ResponseData<String> responseData = new ResponseData<>();

        //校验数据源是否能够连接
        int a=CartService.add(cart);
        if (a>0) {
            responseData.setOK("success", "添加成功！");
        } else {
            responseData.setError("fail");
        }
        return responseData;
    }



    @RequestMapping(value = "/settle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<OrderProduct> Settle(HttpServletRequest request,
                                        @RequestBody Cart cart) {
        ResponseData<OrderProduct> responseData = new ResponseData<>();

        //校验数据源是否能够连接
        OrderProduct result = CartService.selectBuy(cart);


        if (result!=null) {
            responseData.setOK("success", result);

        } else {
            responseData.setError("fail");
        }
        return responseData;
    }
}
