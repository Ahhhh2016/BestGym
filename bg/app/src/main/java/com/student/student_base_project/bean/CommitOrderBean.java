package com.student.student_base_project.bean;

public class CommitOrderBean {

    /**
     * createTime : 2021-04-24T03:31:08.000+0000
     * goodsCategoryId : 1
     * goodsDescript : 鲜花插在牛粪上才能更美丽
     * goodsId : 1
     * goodsMoney : 10.0
     * goodsName : 牛粪鲜花
     * goodsNums : 1
     * goodsStatus : 1
     * goodsUrl : http://47.99.201.11/image/7316dac1d11e403dbff03ed7d3a7d1d1.jpeg
     * inventoryNums : 1000
     * ischeck : true
     * shopCartId : 3
     */

    private String createTime;
    private int goodsCategoryId;
    private String goodsDescript;
    private int goodsId;
    private double goodsMoney;
    private String goodsName;
    private int goodsNums;
    private int goodsStatus;
    private String goodsUrl;
    private int inventoryNums;
    private boolean ischeck;
    private int shopCartId;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsDescript() {
        return goodsDescript;
    }

    public void setGoodsDescript(String goodsDescript) {
        this.goodsDescript = goodsDescript;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public double getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(double goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(int goodsNums) {
        this.goodsNums = goodsNums;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public int getInventoryNums() {
        return inventoryNums;
    }

    public void setInventoryNums(int inventoryNums) {
        this.inventoryNums = inventoryNums;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public int getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(int shopCartId) {
        this.shopCartId = shopCartId;
    }
}
