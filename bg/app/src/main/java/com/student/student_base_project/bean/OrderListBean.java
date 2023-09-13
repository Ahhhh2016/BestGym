package com.student.student_base_project.bean;

import java.util.List;

public class OrderListBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"orderId":5,"userId":4,"goodsId":1,"goodsNums":1,"orderMoney":10,"orderStatus":0,"address":"北京市朝阳区大东门","startTime":"2021-04-28 10:00","endTime":"2021-04-28 10:00","createTime":"2021-04-27T06:46:51.000+0000","updateTime":"2021-04-27T06:46:51.000+0000","congratulation":null,"mobile":"1585650544","message":null,"goodsName":"牛粪鲜花","goodsUrl":"http://47.99.201.11/image/7316dac1d11e403dbff03ed7d3a7d1d1.jpeg"},{"orderId":4,"userId":4,"goodsId":2,"goodsNums":1,"orderMoney":20,"orderStatus":0,"address":"北京市朝阳区大东门","startTime":"2021-04-28 10:00","endTime":"2021-04-28 10:00","createTime":"2021-04-27T06:46:51.000+0000","updateTime":"2021-04-27T06:46:51.000+0000","congratulation":null,"mobile":"1585650544","message":null,"goodsName":"百合花","goodsUrl":"http://47.99.201.11/image/43579fba59384a98ac7ccf40c3d686a5.jpeg"}]
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
         * orderId : 5
         * userId : 4
         * goodsId : 1
         * goodsNums : 1
         * orderMoney : 10.0
         * orderStatus : 0
         * address : 北京市朝阳区大东门
         * startTime : 2021-04-28 10:00
         * endTime : 2021-04-28 10:00
         * createTime : 2021-04-27T06:46:51.000+0000
         * updateTime : 2021-04-27T06:46:51.000+0000
         * congratulation : null
         * mobile : 1585650544
         * message : null
         * goodsName : 牛粪鲜花
         * goodsUrl : http://47.99.201.11/image/7316dac1d11e403dbff03ed7d3a7d1d1.jpeg
         */

        private int orderId;
        private int userId;
        private int goodsId;
        private int goodsNums;
        private double orderMoney;
        private int orderStatus;
        private String address;
        private String startTime;
        private String endTime;
        private String createTime;
        private String updateTime;
        private Object congratulation;
        private String mobile;
        private Object message;
        private String goodsName;
        private String goodsUrl;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsNums() {
            return goodsNums;
        }

        public void setGoodsNums(int goodsNums) {
            this.goodsNums = goodsNums;
        }

        public double getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(double orderMoney) {
            this.orderMoney = orderMoney;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getCongratulation() {
            return congratulation;
        }

        public void setCongratulation(Object congratulation) {
            this.congratulation = congratulation;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsUrl() {
            return goodsUrl;
        }

        public void setGoodsUrl(String goodsUrl) {
            this.goodsUrl = goodsUrl;
        }
    }
}
