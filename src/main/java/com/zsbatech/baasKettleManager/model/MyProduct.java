package com.zsbatech.baasKettleManager.model;

import java.util.Date;

public class MyProduct {
    private Integer id;

    private String pname;

    private Float price;

    private String type;

    private Float postfee;

    private String parameters;

    private String videoPath;

    private String listPicturePath;

    private Integer monthSales;

    private Date creatTime;

    private Date updateTime;

    private String datailPicturePath;

    private String spec;

    private Float discountPrice;

    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getPostfee() {
        return postfee;
    }

    public void setPostfee(Float postfee) {
        this.postfee = postfee;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters == null ? null : parameters.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public String getListPicturePath() {
        return listPicturePath;
    }

    public void setListPicturePath(String listPicturePath) {
        this.listPicturePath = listPicturePath == null ? null : listPicturePath.trim();
    }

    public Integer getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Integer monthSales) {
        this.monthSales = monthSales;
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

    public String getDatailPicturePath() {
        return datailPicturePath;
    }

    public void setDatailPicturePath(String datailPicturePath) {
        this.datailPicturePath = datailPicturePath == null ? null : datailPicturePath.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}