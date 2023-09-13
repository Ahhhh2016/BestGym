package com.student.student_base_project.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.student.student_base_project.R;
import com.student.student_base_project.bean.QueryCommentListBean;

import java.util.List;

public class CommentListAdapter extends BaseQuickAdapter<QueryCommentListBean.DataBean, BaseViewHolder> {
    public CommentListAdapter(@Nullable List<QueryCommentListBean.DataBean> data) {
        super(R.layout.item_comment_layout,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryCommentListBean.DataBean item) {
        helper.setText(R.id.tv_username, item.getUserName())
                .setText(R.id.tv_comment, item.getComment());
    }
}
