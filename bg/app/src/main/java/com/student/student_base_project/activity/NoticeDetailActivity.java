package com.student.student_base_project.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;

import butterknife.BindView;

public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        setRootTitle("Details");
        setLeftTv("", true);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        tvContent.setText(content);
        tvTitle.setText(title);
    }

    @Override
    protected void initView() {

    }
}
