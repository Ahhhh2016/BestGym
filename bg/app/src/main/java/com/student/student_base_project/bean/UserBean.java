package com.student.student_base_project.bean;

public class UserBean {
    private long id;
    private String mobile;
    private String username;
    private String password;

    public UserBean(long id, String mobile, String username, String password) {
        this.id = id;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
