package com.zsbatech.baasKettleManager.model;

public class WriteOrder {
    private Integer userId;
    private Integer productId;
    private Integer num;
    private Boolean isNeedInvoice;
    private String payWay;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getNeedInvoice() {
        return isNeedInvoice;
    }

    public void setNeedInvoice(Boolean needInvoice) {
        isNeedInvoice = needInvoice;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}
