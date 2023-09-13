package com.student.student_base_project.utils;

import android.view.View;


/***
 * 对话框取消和确定点击事件
 */
public interface MyOnClickListener {
    void onCancel(View view);

    void onConfirm(View view, String v);
}
