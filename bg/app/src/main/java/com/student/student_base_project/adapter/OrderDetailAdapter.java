package com.student.student_base_project.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.CommitOrderBean;
import com.student.student_base_project.bean.QueryProductListBean;

import java.util.List;

public class OrderDetailAdapter extends BaseQuickAdapter<CommitOrderBean, BaseViewHolder> {
    public OrderDetailAdapter(@Nullable List<CommitOrderBean> data) {
        super(R.layout.item_order_details,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,CommitOrderBean item) {
        helper.setText(R.id.tv_alias_name, item.getGoodsName())
                .setText(R.id.tv_desc, item.getGoodsDescript())
                .setText(R.id.tv_price, "￥" + item.getGoodsMoney())
                .setText(R.id.tv_go_detail, "Quantities：  x" + item.getGoodsNums());

        RoundedImageView imageView = helper.getView(R.id.banner_image);
        Glide.with(mContext).load(item.getGoodsUrl()).error(R.mipmap.banner1).into(imageView);
    }
}
