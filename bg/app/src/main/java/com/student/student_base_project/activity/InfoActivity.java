package com.student.student_base_project.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;

import butterknife.BindView;

public class InfoActivity extends BaseActivity {

    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setLeftTv("", true);
        setRootTitle("Membership Course Log");
        tvDesc.setText("There is no course log yet.");
    }

    @Override
    protected void initView() {

    }
}
