package com.student.student_base_project.bean;

import java.util.List;

public class QueryCollectListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"goodsId":1,"goodsCategoryId":1,"goodsName":"牛粪鲜花","goodsMoney":10,"goodsUrl":"http://47.99.201.11/image/7316dac1d11e403dbff03ed7d3a7d1d1.jpeg","goodsDescript":"鲜花插在牛粪上才能更美丽","inventoryNums":999,"goodsStatus":1,"createTime":"2021-04-24T03:31:08.000+0000"}]
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private List<DataBean> data;
    private boolean exception;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public static class DataBean {
        /**
         * goodsId : 1
         * goodsCategoryId : 1
         * goodsName : 牛粪鲜花
         * goodsMoney : 10.0
         * goodsUrl : http://47.99.201.11/image/7316dac1d11e403dbff03ed7d3a7d1d1.jpeg
         * goodsDescript : 鲜花插在牛粪上才能更美丽
         * inventoryNums : 999
         * goodsStatus : 1
         * createTime : 2021-04-24T03:31:08.000+0000
         */

        private int goodsId;
        private int goodsCategoryId;
        private String goodsName;
        private double goodsMoney;
        private String goodsUrl;
        private String goodsDescript;
        private int inventoryNums;
        private int goodsStatus;
        private String createTime;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsCategoryId() {
            return goodsCategoryId;
        }

        public void setGoodsCategoryId(int goodsCategoryId) {
            this.goodsCategoryId = goodsCategoryId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public double getGoodsMoney() {
            return goodsMoney;
        }

        public void setGoodsMoney(double goodsMoney) {
            this.goodsMoney = goodsMoney;
        }

        public String getGoodsUrl() {
            return goodsUrl;
        }

        public void setGoodsUrl(String goodsUrl) {
            this.goodsUrl = goodsUrl;
        }

        public String getGoodsDescript() {
            return goodsDescript;
        }

        public void setGoodsDescript(String goodsDescript) {
            this.goodsDescript = goodsDescript;
        }

        public int getInventoryNums() {
            return inventoryNums;
        }

        public void setInventoryNums(int inventoryNums) {
            this.inventoryNums = inventoryNums;
        }

        public int getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(int goodsStatus) {
            this.goodsStatus = goodsStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
