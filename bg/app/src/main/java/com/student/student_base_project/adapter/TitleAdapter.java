package com.student.student_base_project.adapter;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.QueryGoodsCategoryListBean;

import java.util.List;

public class TitleAdapter extends BaseQuickAdapter<QueryGoodsCategoryListBean.DataBean, BaseViewHolder> {

    private int isClickPos;

    public int getIsClickPos() {
        return isClickPos;
    }

    public void setIsClickPos(int isClickPos) {
        this.isClickPos = isClickPos;
    }

    public TitleAdapter(@Nullable List<QueryGoodsCategoryListBean.DataBean> data) {
        super(R.layout.item_title, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryGoodsCategoryListBean.DataBean item) {
        helper.setText(R.id.tv_title, item.getGoodsCategoryName());
        if (helper.getAdapterPosition() == isClickPos) {
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.colorPrimary));
            helper.setBackgroundColor(R.id.tv_title, Color.parseColor("#D9DCE2"));
        } else {
            helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.text_2a));
            helper.setBackgroundColor(R.id.tv_title, Color.parseColor("#00000000"));
        }
    }
}
