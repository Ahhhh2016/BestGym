package com.student.student_base_project.bean;

import java.io.Serializable;

public class SubscribeBean implements Serializable {
    private long id;//当前生产的id
    private long typeId;//预约类型id
    private String type;//类型
    private String time;//日期 操作的时间
    private String price;//价格
    private Integer cover;//封面
    private String date;//年月日
    private String remark;//备注
    private int payType;

    public SubscribeBean(long id, long typeId, String type, String time, String price, Integer cover, String date,String remark,int payType) {
        this.id = id;
        this.typeId = typeId;
        this.type = type;
        this.time = time;
        this.price = price;
        this.cover = cover;

        this.date = date;
        this.remark = remark;
        this.payType = payType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
