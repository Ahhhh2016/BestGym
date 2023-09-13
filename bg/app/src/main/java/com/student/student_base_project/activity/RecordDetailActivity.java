package com.student.student_base_project.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;

import butterknife.BindView;

public class RecordDetailActivity extends BaseActivity {
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_price)
    TextView tvPirce;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price2)
    TextView tvPirce2;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_day_week)
    TextView tvDayWeek;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_details);
        setLeftTv("", true);
        setRootTitle("Details");
        String type = getIntent().getStringExtra("type");
        String time = getIntent().getStringExtra("time");
        String price = getIntent().getStringExtra("price");
        String date = getIntent().getStringExtra("date");
        String remark = getIntent().getStringExtra("remark");
        int cover = getIntent().getIntExtra("cover", 0);


        tvDayWeek.setText("2022." + date + " ");
        ivCover.setImageResource(cover);
        tvType.setText(type);
        tvPirce.setText(price);
        tvTime2.setText("Chosen：" + time);
        tvPirce.setText(price);
        etContent.setText(remark);

    }

    @Override
    protected void initView() {

    }
}
