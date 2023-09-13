package com.student.student_base_project.utils;

import android.view.View;
import android.widget.EditText;

public interface IRecycleViewSubClickListener {

    /***
     * 加数量点击事件
     * @param view
     * @param parentPos
     * @param etNumber
     */
    void onItemPlusClick(View view, int parentPos, EditText etNumber);

    /***
     * 减法数量点击事件
     * @param view
     * @param parentPos
     * @param etNumber
     */
    void onItemMinusClick(View view, int parentPos, EditText etNumber);

    /***
     * 输入框
     * @param str 输入的内容
     * @param parentPos
     * @param etInputMoney
     */
    void onTextChangedListener(String str, int parentPos, EditText etInputMoney);
}

