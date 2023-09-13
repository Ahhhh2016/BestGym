package com.student.student_base_project.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.QueryCollectListBean;
import com.student.student_base_project.bean.QueryProductListBean;

import java.util.List;

public class CollectListAdapter extends BaseQuickAdapter<QueryCollectListBean.DataBean, BaseViewHolder> {
    public CollectListAdapter(@Nullable List<QueryCollectListBean.DataBean> data) {
        super(R.layout.item_tab_home,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryCollectListBean.DataBean item) {
        helper.setText(R.id.tv_alias_name, item.getGoodsName())
                .setText(R.id.tv_desc, item.getGoodsDescript())
                .setText(R.id.tv_price, "￥" + item.getGoodsMoney())
                .addOnClickListener(R.id.tv_go_detail);

        helper.setVisible(R.id.tv_go_detail, false);

        RoundedImageView imageView = helper.getView(R.id.banner_image);
        Glide.with(mContext).load(item.getGoodsUrl()).error(R.mipmap.banner1).into(imageView);
    }
}
