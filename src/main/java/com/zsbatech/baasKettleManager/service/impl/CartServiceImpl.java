package com.zsbatech.baasKettleManager.service.impl;

import com.zsbatech.baasKettleManager.dao.CartMapper;
import com.zsbatech.baasKettleManager.dao.MyProductMapper;
import com.zsbatech.baasKettleManager.model.Cart;
import com.zsbatech.baasKettleManager.model.OrderProduct;
import com.zsbatech.baasKettleManager.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    private MyProductMapper productMapper;
    @Override
    public int add(Cart cart) {
        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());
        return cartMapper.insert(cart);
    }
    @Override
    public OrderProduct selectBuy(Cart cart){
        OrderProduct op=new OrderProduct();
        op.setProductId(cart.getProductId());
        op.setPnum(cart.getNum());
        op.setPicPath(productMapper.selectByPrimaryKey(cart.getProductId()).getListPicturePath());
        op.setPname(productMapper.selectByPrimaryKey(cart.getProductId()).getPname());
        op.setPnum(cart.getNum());
        float unitPrice=productMapper.selectByPrimaryKey(cart.getProductId()).getPrice();
        float discount=productMapper.selectByPrimaryKey(cart.getProductId()).getDiscountPrice();
        float postFee=productMapper.selectByPrimaryKey(cart.getProductId()).getPostfee();
        op.setUnitPrice(unitPrice);
        op.setTotallPrice(unitPrice*cart.getNum()-discount-postFee);
        System.out.println(op);
        return op;
    }
}
