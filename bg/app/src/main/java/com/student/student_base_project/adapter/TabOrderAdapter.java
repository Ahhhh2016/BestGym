package com.student.student_base_project.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.bean.QueryShopCartListBean;
import com.student.student_base_project.utils.IRecycleViewSubClickListener;

import java.util.List;

public class TabOrderAdapter extends BaseQuickAdapter<QueryShopCartListBean.DataBean, BaseViewHolder> {

    public TabOrderAdapter(@Nullable List<QueryShopCartListBean.DataBean> data) {
        super(R.layout.item_order,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryShopCartListBean.DataBean item) {
        helper.setText(R.id.tv_alias_name, item.getGoodsName())
                .setText(R.id.tv_desc, item.getGoodsDescript())
                .setText(R.id.tv_price, "￥" + item.getGoodsMoney())
                .addOnClickListener(R.id.tv_go_detail)
                .addOnClickListener(R.id.check_box);
        helper.setChecked(R.id.check_box, item.isIscheck());

        RoundedImageView imageView = helper.getView(R.id.imageView);
        Glide.with(mContext).load(item.getGoodsUrl()).error(R.mipmap.banner1).into(imageView);
        EditText etNumber = helper.getView(R.id.et_number);

        etNumber.setText("" + item.getGoodsNums());
        etNumber.setSelection(etNumber.getText().toString().length());
        helper.getView(R.id.tv_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRecycleViewSubClickListener.onItemPlusClick(v,helper.getAdapterPosition(),etNumber);
            }
        });

        helper.getView(R.id.tv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRecycleViewSubClickListener.onItemMinusClick(v, helper.getAdapterPosition(), etNumber);
            }
        });

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                iRecycleViewSubClickListener.onTextChangedListener(s.toString(),  helper.getAdapterPosition(), etNumber);
            }
        });
    }

    private IRecycleViewSubClickListener iRecycleViewSubClickListener;
    public void setRecycleOnClick(IRecycleViewSubClickListener recycleOnClick) {
        this.iRecycleViewSubClickListener = recycleOnClick;
    }
}
