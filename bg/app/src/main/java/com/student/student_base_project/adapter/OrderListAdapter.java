package com.student.student_base_project.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.OrderListBean;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {
    public OrderListAdapter(@Nullable List<OrderListBean.DataBean> data) {
        super(R.layout.item_order_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.DataBean item) {
        helper.setText(R.id.tv_alias_name, item.getGoodsName());

        RoundedImageView imageView = helper.getView(R.id.banner_image);
        Glide.with(mContext).load(item.getGoodsUrl()).error(R.mipmap.banner1).into(imageView);
    }
}
