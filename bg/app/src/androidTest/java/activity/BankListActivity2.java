package activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.BankListAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.CardBean;
import com.student.student_base_project.db.BankDao;
import com.student.student_base_project.utils.MyDialog;

import java.util.List;

import butterknife.BindView;

public class BankListActivity2 extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private Context mContxt;
    private BankDao bankDao;
    private List<CardBean> mList;
    private BankListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list2);
        setLeftTv("", true);
        setRootTitle("Bank list");
        mContxt = BankListActivity2.this;
        bankDao = new BankDao(mContxt);
        initRecycleView();
        initRefreshLayout();
    }


    private void initRefreshLayout() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(BankListActivity2.this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        ClassicsFooter footer = new ClassicsFooter(BankListActivity2.this);
        footer.setAccentColor(getResources().getColor(R.color.color_3a));
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshData());
        refreshLayout.autoRefresh();
    }

    private void refreshData() {
        mList = bankDao.queryBankList();
        adapter.setNewData(mList);
        refreshLayout.finishRefresh();
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContxt, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new BankListAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CardBean cardBean = (CardBean) adapter.getItem(position);
                if (cardBean != null) {
                    long id = cardBean.getId();
                    MyDialog dialog = new MyDialog(mContxt);
                    dialog.setMessage("Whether or not to delete ？");
                    dialog.setOnNegativeListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();

                        }
                    });
                    dialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bankDao.delteBankListById(id);
                            refreshLayout.autoRefresh();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
    @Override
    protected void initView() {

    }
}
