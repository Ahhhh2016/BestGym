package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.CardBean;

import java.util.List;

public class BankListAdapter extends BaseQuickAdapter<CardBean, BaseViewHolder> {
    public BankListAdapter(@Nullable List<CardBean> data) {
        super(R.layout.item_bank_list,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CardBean item) {
        helper.setText(R.id.tv_cardno, item.getCardNo());
    }
}
