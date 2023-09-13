package activity;

import android.content.Context;
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
import com.student.student_base_project.adapter.SubscribeRecordListAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.db.SubscribeDao;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SubscribeRecordListActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private Context mContext;
    private SubscribeDao subscribeDao;
    private List<SubscribeBean> mList = new ArrayList<>();
    private SubscribeRecordListAdapter orderListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_record_list);
        setLeftTv("", true);
        setRootTitle("Booking List");
        mContext = SubscribeRecordListActivity.this;


        subscribeDao = new SubscribeDao(mContext);

        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        orderListAdapter = new SubscribeRecordListAdapter(mList);
        recyclerView.setAdapter(orderListAdapter);
        initRefreshLayout();

        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SubscribeBean subscribeBean = (SubscribeBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.tv_del:
                        MyDialog dialog = new MyDialog(SubscribeRecordListActivity.this);
                        dialog.setMessage("Whether to unsubscribe？");
                        dialog.setOnNegativeListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                subscribeDao.delteSubscribeById(subscribeBean.getId());
                                ToastUtils.SingleToastUtil(SubscribeRecordListActivity.this, "Delete Successfully!");
                                refreshLayout.autoRefresh();
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.tv_go_detail:
                        if (subscribeBean != null) {
                            startActivity(new Intent(mContext, RecordDetailActivity.class)
                                    .putExtra("type", subscribeBean.getType())
                                    .putExtra("time", subscribeBean.getTime())
                                    .putExtra("price", subscribeBean.getPrice())
                                    .putExtra("cover", subscribeBean.getCover())
                                    .putExtra("date", subscribeBean.getDate())
                                    .putExtra("remark", subscribeBean.getRemark())
                            );
                        }
                        break;
                }
            }
        });
    }

    private void initRefreshLayout() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        ClassicsFooter footer = new ClassicsFooter(mContext);
        footer.setAccentColor(getResources().getColor(R.color.color_3a));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshData());
        refreshLayout.autoRefresh();
    }

    private void refreshData() {
        mList = subscribeDao.querySubscribeList();
        if (!mList.isEmpty()) {
            orderListAdapter.setNewData(mList);
        } else {
            orderListAdapter.setNewData(mList);
            orderListAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_layout2, null));
        }
        refreshLayout.finishRefresh();
    }

    @Override
    protected void initView() {

    }
}
