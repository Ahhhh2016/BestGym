package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.CourseBean;

import java.util.List;

public class OtherAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {

    public OtherAdapter(@Nullable List<CourseBean> data) {
        super(R.layout.item_other, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CourseBean item) {
        helper.setImageResource(R.id.iv_cover, item.getCover())
                .setText(R.id.tv_type, item.getType())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_price, item.getPrice())
                .setText(R.id.tv_yuyue, item.getStatus()).addOnClickListener(R.id.tv_yuyue);
    }
}
