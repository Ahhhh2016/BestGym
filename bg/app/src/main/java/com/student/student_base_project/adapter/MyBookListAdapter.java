package com.student.student_base_project.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.SubscribeBean;

import java.util.List;

public class MyBookListAdapter extends BaseQuickAdapter<SubscribeBean, BaseViewHolder> {
    public MyBookListAdapter(@Nullable List<SubscribeBean> data) {
        super(R.layout.item_order_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubscribeBean item) {
        helper.setText(R.id.tv_alias_name, item.getType())
        .setText(R.id.tv_price,item.getPrice())
        .setText(R.id.tv_desc,item.getTime())
        .setText(R.id.tv_go_detail,"Acount paid ").addOnClickListener(R.id.tv_del);


        RoundedImageView imageView = helper.getView(R.id.banner_image);
        Glide.with(mContext).load(item.getCover()).error(R.mipmap.banner1).into(imageView);
    }
}
