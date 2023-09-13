package com.student.student_base_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.CommentListAdapter;
import com.student.student_base_project.adapter.NoticeListAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.QueryNoticeListBean;
import com.student.student_base_project.bean.QueryProductListBean;
import com.student.student_base_project.net.ApiSubscriber;
import com.student.student_base_project.net.HttpManager;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoticeListActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private List<QueryNoticeListBean.DataBean> mList;
    private NoticeListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        setLeftTv("", true);
        setRootTitle("Notice list");
        queryNoticeList();

        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(NoticeListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new NoticeListAdapter(mList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QueryNoticeListBean.DataBean dataBean = (QueryNoticeListBean.DataBean) adapter.getItem(position);
                if (dataBean != null) {
                    Intent intent = new Intent(NoticeListActivity.this, NoticeDetailActivity.class);
                    intent.putExtra("title", dataBean.getTitle());
                    intent.putExtra("content", dataBean.getNoticeContent());
                    startActivity(intent);
                }

            }
        });
    }

    private void queryNoticeList() {
        HttpManager.getInstance().queryNoticeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<QueryNoticeListBean>() {
                    @Override
                    public void onSuccess(QueryNoticeListBean bean) {
                        mList = bean.getData();
                        if (!mList.isEmpty()) {
                            adapter.setNewData(mList);
                        } else {
                            adapter.setNewData(mList);
                            adapter.setEmptyView(LayoutInflater.from(NoticeListActivity.this).inflate(R.layout.empty_layout2, null));
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onDisposable(Disposable d) {

                    }
                });
    }

    @Override
    protected void initView() {

    }
}
