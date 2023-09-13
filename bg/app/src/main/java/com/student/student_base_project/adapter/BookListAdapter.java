package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.CourseBean;
import com.student.student_base_project.bean.DateOrWeekBean;

import java.util.List;

public class BookListAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {

    public BookListAdapter(@Nullable List<CourseBean> data) {
        super(R.layout.item_book_list,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CourseBean item) {
        helper.setText(R.id.tv_type, item.getType())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_price, "￡"+item.getPrice())
                .setText(R.id.tv_number, "The reservation number" + item.getNumber() + "/" + (item.getNumber().equals("0") ? "1" : item.getNumber()))
                .setText(R.id.tv_status, item.getStatus());
        helper.setImageResource(R.id.iv_cover, item.getCover());
    }
}
