package com.student.student_base_project.bean;

public class LoginBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"userId":2,"userName":"008","password":"111111","mobile":"18888888888","sex":1,"age":30,"userType":"student","userPosition":null,"userSchoolName":null,"createTime":"2021-03-05T15:26:25.000+0000","updateTime":"2021-03-05T16:13:20.000+0000","status":1}
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * userId : 2
         * userName : 008
         * password : 111111
         * mobile : 18888888888
         * sex : 1
         * age : 30
         * userType : student
         * userPosition : null
         * userSchoolName : null
         * createTime : 2021-03-05T15:26:25.000+0000
         * updateTime : 2021-03-05T16:13:20.000+0000
         * status : 1
         */

        private int userId;
        private String userName;
        private String password;
        private String mobile;
        private int sex;
        private String age;
        private String userType;
        private Object userPosition;
        private Object userSchoolName;
        private String createTime;
        private String updateTime;
        private int status;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getUserPosition() {
            return userPosition;
        }

        public void setUserPosition(Object userPosition) {
            this.userPosition = userPosition;
        }

        public Object getUserSchoolName() {
            return userSchoolName;
        }

        public void setUserSchoolName(Object userSchoolName) {
            this.userSchoolName = userSchoolName;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
