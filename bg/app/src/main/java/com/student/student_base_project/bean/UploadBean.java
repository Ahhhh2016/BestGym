package com.student.student_base_project.bean;

public class UploadBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"path":"7650b2e84b8845149bd0ef561025d329.jpeg","url":"http://47.99.201.11/image/7650b2e84b8845149bd0ef561025d329.jpeg"}
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
         * path : 7650b2e84b8845149bd0ef561025d329.jpeg
         * url : http://47.99.201.11/image/7650b2e84b8845149bd0ef561025d329.jpeg
         */

        private String path;
        private String url;
        private String diagnosticReport;

        public String getDiagnosticReport() {
            return diagnosticReport;
        }

        public void setDiagnosticReport(String diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
