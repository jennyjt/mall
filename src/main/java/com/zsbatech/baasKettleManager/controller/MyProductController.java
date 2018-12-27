package com.zsbatech.baasKettleManager.controller;


import com.zsbatech.baasKettleManager.model.MyProduct;
import com.zsbatech.baasKettleManager.model.PaginationSetting;
import com.zsbatech.baasKettleManager.service.MyProductService;
import com.zsbatech.base.common.Pagination;
import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/product")
public class MyProductController {


    @Autowired
    private MyProductService productService;

    //获取所有商品的图片，名称和价格
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<MyProduct>> createDataSource(HttpServletRequest request,
                                                 @RequestBody PaginationSetting paginationSetting) {
        ResponseData<Pagination<MyProduct>> responseData = new ResponseData<>();

//        boolean result=productService.getList(product);
//        List<MyProduct> result = productService.getList(product);
        Pagination<MyProduct> result=productService.getList(paginationSetting);



        if(result!=null){
//            responseData.setOK("success", "获取列表成功！");
//            responseData.setOK("success", JSON.toJSONString(result));
            responseData.setOK(200, "success", result);
        }else{
            responseData.setError("fail");
        }
        return responseData;
    }

    @RequestMapping(value = "/allList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseData<Pagination<MyProduct>> getProductList(HttpServletRequest request,
                                                              @RequestParam(name = "curr_page", required = false,defaultValue = "1") Integer currPage,
                                                              @RequestParam(name = "page_size", required = false,defaultValue = "10") Integer pageSize)
    {
        ResponseData<Pagination<MyProduct>> responseData = new ResponseData<>();

        Pagination<MyProduct> result  = productService.getProductList(currPage, pageSize);
        if (result != null) {
            responseData.setOK(200, "success", result);
        }else{
            responseData.setError("Fail!");
        }
        return responseData;

    }
}




