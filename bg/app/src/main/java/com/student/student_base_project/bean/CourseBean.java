package com.student.student_base_project.bean;

public class CourseBean {
    private long id;
    private String type;//类型
    private String time;//日期 操作的时间
    private String price;//价格
    private String status;//状态
    private String number;//预约人数
    private Integer cover;//封面

    public CourseBean() {

    }

    public CourseBean(long id, String type, String time, String price, String status, String number, Integer cover) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.price = price;
        this.status = status;
        this.number = number;
        this.cover = cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }
}
