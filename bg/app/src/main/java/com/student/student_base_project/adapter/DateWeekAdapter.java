package com.student.student_base_project.adapter;

import android.graphics.Color;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.DateOrWeekBean;

import java.util.List;

public class DateWeekAdapter extends BaseQuickAdapter<DateOrWeekBean, BaseViewHolder> {

    private int clickPos ;

    public int getClickPos() {
        return clickPos;
    }

    public void setClickPos(int clickPos) {
        this.clickPos = clickPos;
    }

    public DateWeekAdapter(@Nullable List<DateOrWeekBean> data) {
        super(R.layout.item_date_week,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DateOrWeekBean item) {
        helper.setText(R.id.tv_week, item.getWeek())
                .setText(R.id.tv_day, item.getDate());
        LinearLayout layoutContainer = helper.getView(R.id.layout_container);
        if (helper.getLayoutPosition() == getClickPos()) {
            layoutContainer.setBackground(mContext.getResources().getDrawable(R.drawable.tv_red_bg));
            helper.setTextColor(R.id.tv_week, Color.parseColor("#ffffff"));
            helper.setTextColor(R.id.tv_day, Color.parseColor("#ffffff"));
        } else {
            layoutContainer.setBackground(null);
            helper.setTextColor(R.id.tv_week, Color.parseColor("#222222"));
            helper.setTextColor(R.id.tv_day, Color.parseColor("#0c0c0c"));
        }
    }
}
