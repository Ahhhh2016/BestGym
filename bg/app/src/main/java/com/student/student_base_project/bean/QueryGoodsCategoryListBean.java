package com.student.student_base_project.bean;

import java.util.List;

public class QueryGoodsCategoryListBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"goodsCategoryId":1,"goodsCategoryName":"百色花","goodsCategoryUrl":null,"status":1,"createTime":"2021-04-27T00:31:10.000+0000"},{"goodsCategoryId":2,"goodsCategoryName":"红色花","goodsCategoryUrl":null,"status":1,"createTime":"2021-04-27T00:31:23.000+0000"},{"goodsCategoryId":3,"goodsCategoryName":"黄色花","goodsCategoryUrl":null,"status":1,"createTime":"2021-04-27T00:31:43.000+0000"}]
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
         * goodsCategoryId : 1
         * goodsCategoryName : 百色花
         * goodsCategoryUrl : null
         * status : 1
         * createTime : 2021-04-27T00:31:10.000+0000
         */

        private int goodsCategoryId;
        private String goodsCategoryName;
        private Object goodsCategoryUrl;
        private int status;
        private String createTime;

        public int getGoodsCategoryId() {
            return goodsCategoryId;
        }

        public void setGoodsCategoryId(int goodsCategoryId) {
            this.goodsCategoryId = goodsCategoryId;
        }

        public String getGoodsCategoryName() {
            return goodsCategoryName;
        }

        public void setGoodsCategoryName(String goodsCategoryName) {
            this.goodsCategoryName = goodsCategoryName;
        }

        public Object getGoodsCategoryUrl() {
            return goodsCategoryUrl;
        }

        public void setGoodsCategoryUrl(Object goodsCategoryUrl) {
            this.goodsCategoryUrl = goodsCategoryUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
