package com.student.student_base_project.net;


import com.student.student_base_project.bean.UploadBean;

public abstract class FileLoadListener {
    public void onProgressUpdate(long bytesRead, long contentLength, boolean done) {
    }

    public void onImgOrFileSuccess(UploadBean.DataBean da) {

    }

    public void onFail() {
    }

    public void onStart() {

    }
}
