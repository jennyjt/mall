package com.zsbatech.baasKettleManager.service.impl;

import com.github.pagehelper.page.PageMethod;
//import com.zsbatech.baasKettleManager.model.DbJobInfo;
import com.zsbatech.baasKettleManager.dao.MyProductMapper;
import com.zsbatech.baasKettleManager.model.MyProduct;
import com.zsbatech.baasKettleManager.model.MyProductExample;
import com.zsbatech.baasKettleManager.model.PaginationSetting;
import com.zsbatech.baasKettleManager.service.MyProductService;
import com.zsbatech.base.common.Pagination;
//import com.zsbatech.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class MyProductServiceImpl implements MyProductService {

    @Autowired
    private MyProductMapper productMapper;

    @Override
    public Pagination<MyProduct> getList(PaginationSetting paginationSetting) {
        // TODO Auto-generated method stub
        PageMethod.startPage(paginationSetting.getCurrPage(), paginationSetting.getPageSize());

        MyProductExample productExample = new MyProductExample();
        MyProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andIdIsNotNull();
        List<MyProduct> productList = productMapper.selectByExample(productExample);
/*
  int a = productList.size();
        if (a>0) {
            return true;
        }
        return false;
    }
    */

//        return productList;
        Pagination<MyProduct> pagination=new Pagination<>(productList);
        return pagination;
}
//    @Override
    @Override
    public Pagination<MyProduct> getProductList(Integer currPage,Integer pageSize) {
        PageMethod.startPage(currPage, pageSize);
        List<MyProduct> productList = new ArrayList<MyProduct>();
//jdbk连接：自己写sql语句
        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();


            String sql = "select list_picture_path,pname,price from my_product;";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                MyProduct product = new MyProduct();
                /*product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setListPicturePath("list_picture_path" );*/
                productList.add(product);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//引用mapping方法
        /*
        MyProductExample productExample=new MyProductExample();
        MyProductExample.Criteria criteria=productExample.createCriteria();
        criteria.andIdIsNotNull();
        productList=productMapper.selectByExample(productExample);*/

        Pagination<MyProduct> productInfoPagination = new Pagination<MyProduct>(productList);
        return productInfoPagination;

    }
    }

