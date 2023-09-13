package com.student.student_base_project.adapter;

import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.HomeDataBean;
import com.student.student_base_project.bean.QueryProductListBean;

import java.util.List;

public class TabHomeAdapter extends BaseQuickAdapter<HomeDataBean, BaseViewHolder> {
    public TabHomeAdapter(@Nullable List<HomeDataBean> data) {
        super(R.layout.item_tab_home,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setImageResource(R.id.iv_url, item.getImageUrl());
        LinearLayout layoutContainer =helper.getView(R.id.layout_container);
        if (helper.getLayoutPosition() == 3) {
            layoutContainer.setBackground(null);
        }

        if (helper.getLayoutPosition() > 3) {
            helper.setVisible(R.id.view_line, false);
        } else {
            helper.setVisible(R.id.view_line, true);
        }
    }
}
