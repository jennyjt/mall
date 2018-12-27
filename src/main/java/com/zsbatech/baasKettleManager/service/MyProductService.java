package com.zsbatech.baasKettleManager.service;

//import com.lmax.disruptor.dsl.ProducerType;
import com.zsbatech.baasKettleManager.model.MyProduct;
import com.zsbatech.baasKettleManager.model.PaginationSetting;
import com.zsbatech.base.common.Pagination;
//import com.zsbatech.base.common.ResponseData;

public interface MyProductService {
//    boolean getList(MyProduct product);
//    List<MyProduct> getList(MyProduct product);
    Pagination<MyProduct> getList(PaginationSetting paginationSetting);
    Pagination<MyProduct> getProductList(Integer currPage,Integer pageSize);
}
