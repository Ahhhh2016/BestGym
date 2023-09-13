package com.student.student_base_project.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.db.SubscribeDao;
import com.student.student_base_project.utils.BaseDialog;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SubscribeActivity extends BaseActivity {

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
    @BindView(R.id.tv_current_length)
    TextView tvCurrentLength;

    private long id;
    private SubscribeDao subscribeDao;
    private String type;
    private String price;
    private int cover;
    private String time;
    private String date;
    private String week;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        setLeftTv("", true);
        setRootTitle("New booking");
        id = getIntent().getLongExtra("id", 0);
        type = getIntent().getStringExtra("type");
        price = getIntent().getStringExtra("price");
        cover = getIntent().getIntExtra("cover", 0);
        time = getIntent().getStringExtra("time");

        date = getIntent().getStringExtra("date");
        week = getIntent().getStringExtra("week");

        tvDayWeek.setText("2022." + date + " " + week);
        ivCover.setImageResource(cover);
        tvType.setText(type);
        tvPirce.setText(price);
        tvTime2.setText("Selected：" + time);
        tvPirce.setText(price);

        subscribeDao = new SubscribeDao(SubscribeActivity.this);
    }


    @OnTextChanged({R.id.et_content})
    public void afterTextChanged(Editable s) {
        if (s == null || s.length() <= 0) {
            tvCurrentLength.setText("0");
        } else {
            tvCurrentLength.setText(s.length() + "");
        }
    }

    @OnClick({R.id.tv_detail,R.id.tv_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail:
                //Show Details
                Dialog dialog = BaseDialog.showDialog(SubscribeActivity.this, R.layout.dialog_bottom_layout);
                ImageView ivDel = dialog.findViewById(R.id.iv_del);
                RoundedImageView ivCover = dialog.findViewById(R.id.iv_cover);
                TextView tvPrice = dialog.findViewById(R.id.tv_price);
                TextView tvType = dialog.findViewById(R.id.tv_type);
                tvPrice.setText(price);
                tvType.setText(type);
                ivCover.setImageResource(cover);
                ivDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.tv_commit:
                //Book
//                boolean isSubscribe = subscribeDao.isSubscribe(id);
//                if (isSubscribe) {
//                    ToastUtils.SingleToastUtil(SubscribeActivity.this, "It's already booked. It can't be made twice");
//                    return;
//                }
                commitDialog();
                break;
        }
    }

    //提交对话框
    private void commitDialog() {
        MyDialog myDialog = new MyDialog(SubscribeActivity.this);
        myDialog.setMessage("Confirm the appointment？");
        myDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();
                startActivity(new Intent(SubscribeActivity.this, PayActivity.class)
                        .putExtra("type", type)
                        .putExtra("id",id)
                        .putExtra("time", time)
                        .putExtra("price", price)
                        .putExtra("cover", cover)
                        .putExtra("date", date + week)
                        .putExtra("content", content)
                );
                myDialog.dismiss();
                finish();
            }
        });

        myDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @Override
    protected void initView() {

    }
}
