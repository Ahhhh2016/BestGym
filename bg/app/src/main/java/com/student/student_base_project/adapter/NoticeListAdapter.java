package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.QueryCommentListBean;
import com.student.student_base_project.bean.QueryNoticeListBean;

import java.util.List;

public class NoticeListAdapter extends BaseQuickAdapter<QueryNoticeListBean.DataBean, BaseViewHolder> {
    public NoticeListAdapter(@Nullable List<QueryNoticeListBean.DataBean> data) {
        super(R.layout.item_notice_layout,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryNoticeListBean.DataBean item) {
        helper.setText(R.id.tv_username, item.getTitle())
                .setText(R.id.tv_comment, item.getNoticeContent());
    }
}
