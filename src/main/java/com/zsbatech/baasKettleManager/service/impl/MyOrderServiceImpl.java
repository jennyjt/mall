package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.*;
import com.zsbatech.baasKettleManager.model.*;
import com.zsbatech.baasKettleManager.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.UUID;

@Service
public class MyOrderServiceImpl implements MyOrderService {
    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private MyOrderMapper orderMapper;

    @Override
    public String creatOrder(WriteOrder writeOrder) {
//        OrderDetails od=new OrderDetails();
        MyOrder order=new MyOrder();
        String orderNo=getOrderIdByUUId();
        order.setOrderNo(orderNo);
        order.setCreatTime(new Date());
        order.setUpdateTime(new Date());
        order.setType("待付款");
        order.setPayMethod(writeOrder.getPayWay());
        order.setUserId(writeOrder.getUserId());
//
//



        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?allowMultiQueries=true&serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();

            String sql1 = "select user_name,telephoto,addr from my_user where id="+""+writeOrder.getUserId()+";";
            ResultSet rs1 = stmt.executeQuery(sql1);

            while(rs1.next()){
                OrderUser ou=new OrderUser();
                ou.setUserName(rs1.getString("user_name"));
                ou.setTelephoto(rs1.getString("telephoto"));
                ou.setAddr(rs1.getString("addr"));
                ou.setOrderId(Integer.parseInt(orderNo));
                ou.setUserId(writeOrder.getUserId());
                ou.setCreateTime(new Date());
                ou.setUpdateTime(new Date());
                orderUserMapper.insert(ou);

            }


            String sql2="select id,price,postfee,pname,list_picture_path from my_product where id="+""+writeOrder.getProductId()+";";
            ResultSet rs2=stmt.executeQuery(sql2);

            while(rs2.next()){
                OrderProduct op=new OrderProduct();
                op.setPname(rs2.getString("pname"));
                float unitPrice=rs2.getFloat("price");
                float discountPrice=rs2.getFloat("discount_price");
                op.setUnitPrice(unitPrice);
                float postfee=rs2.getFloat("postFee");
                float payFee=unitPrice*writeOrder.getNum()-postfee-discountPrice;
                op.setTotallPrice(payFee);
                op.setCreateTime(new Date());
                op.setUpdateTime(new Date());
                op.setPicPath(rs2.getString("list_picture_path"));
                op.setPnum(writeOrder.getNum());
                op.setProductId(rs2.getInt("id"));
                op.setOrderId(Integer.parseInt(orderNo));
                orderProductMapper.insert(op);


                order.setPostfee(postfee);
                order.setActualPrice(payFee);
                order.setDiscountPrice(discountPrice);
                order.setProductTotallPrice(unitPrice*writeOrder.getNum());
            }


            rs1.close();
            rs2.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//
        orderMapper.insert(order);
        return orderNo;

    }
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
            if(hashCodeV < 0) {//有可能是负数
                hashCodeV=-hashCodeV;
 }
//         0 代表前面补充0     
//         4 代表长度为4     
//         d 代表参数为正数型
        return machineId+ String.format("%015d", hashCodeV);
    }
}
