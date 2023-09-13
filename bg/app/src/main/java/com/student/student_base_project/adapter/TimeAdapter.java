package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.TimeDataBean;

import java.util.List;

public class TimeAdapter extends BaseQuickAdapter<TimeDataBean, BaseViewHolder> {

    private int clickPos;

    public int getClickPos() {
        return clickPos;
    }

    public void setClickPos(int clickPos) {
        this.clickPos = clickPos;
    }

    public TimeAdapter(@Nullable List<TimeDataBean> data) {
        super(R.layout.item_time,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TimeDataBean item) {
        helper.setText(R.id.tv_time, item.getTimeTitle());
        if (clickPos == helper.getLayoutPosition()) {
            helper.setBackgroundRes(R.id.tv_time, R.drawable.shape_ddd_corner_5_check);
        } else {
            helper.setBackgroundRes(R.id.tv_time, R.drawable.shape_ddd_corner_5);
        }
    }
}
