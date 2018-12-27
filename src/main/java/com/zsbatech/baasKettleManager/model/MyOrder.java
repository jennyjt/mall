package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class MyOrder {
    private Integer id;

    private String orderNo;

    private String type;

    private String payMethod;

    private Float discountPrice;

    private Float postfee;

    private Float actualPrice;

    private Date creatTime;

    private Date updateTime;

    private Integer userId;

    private Float productTotallPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public Float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Float getPostfee() {
        return postfee;
    }

    public void setPostfee(Float postfee) {
        this.postfee = postfee;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getProductTotallPrice() {
        return productTotallPrice;
    }

    public void setProductTotallPrice(Float productTotallPrice) {
        this.productTotallPrice = productTotallPrice;
    }
}