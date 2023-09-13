package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.CardInfoBean;

import java.util.List;

public class CardListAdapter extends BaseQuickAdapter<CardInfoBean, BaseViewHolder> {

    public CardListAdapter(@Nullable List<CardInfoBean> data) {
        super(R.layout.item_card_list,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CardInfoBean item) {
        helper.setText(R.id.tv_card_type, item.getCardType())
                .setText(R.id.tv_card_type2, item.getCardType())
                .setText(R.id.tv_type, item.getType())
                .setText(R.id.tv_price, item.getPrice());
    }
}
