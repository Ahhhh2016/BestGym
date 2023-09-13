package com.student.student_base_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.CardListAdapter;
import com.student.student_base_project.adapter.CollectListAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.CardInfoBean;
import com.student.student_base_project.db.CardInfoDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CardListActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private CardListAdapter adapter;
    private CardInfoDao cardInfoDao;
    private List<CardInfoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        setLeftTv("",true);
        setRootTitle("The membership card item");
        cardInfoDao = new CardInfoDao(CardListActivity.this);

        initRefreshLayout();
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(CardListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new CardListAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CardInfoBean cardInfoBean = (CardInfoBean) adapter.getItem(position);

                startActivity(new Intent(CardListActivity.this, CardOrderDetailActivity.class)
                        .putExtra("cardInfoBean", (Serializable) cardInfoBean)
                );
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(CardListActivity.this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        ClassicsFooter footer = new ClassicsFooter(CardListActivity.this);
        footer.setAccentColor(getResources().getColor(R.color.color_3a));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshData());
        refreshLayout.autoRefresh();
    }

    private void refreshData() {
        refreshLayout.finishRefresh();
        mList = cardInfoDao.queryCardInfoList();
        if (!mList.isEmpty()) {
            adapter.setNewData(mList);
        } else {
            adapter.setNewData(mList);
            adapter.setEmptyView(LayoutInflater.from(CardListActivity.this).inflate(R.layout.empty_layout2, null));
        }
    }

    @Override
    protected void initView() {

    }
}
