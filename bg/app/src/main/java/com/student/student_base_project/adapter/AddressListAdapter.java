package com.student.student_base_project.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.QueryAddressListBean;

import java.util.List;

public class AddressListAdapter extends BaseQuickAdapter<QueryAddressListBean.DataBean, BaseViewHolder> {

    public AddressListAdapter(@Nullable List<QueryAddressListBean.DataBean> data) {
        super(R.layout.item_address_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryAddressListBean.DataBean item) {
        helper.setText(R.id.tv_address_name, item.getAddress());
    }
}
