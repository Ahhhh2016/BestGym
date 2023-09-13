package com.student.student_base_project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.BankListAdapter;
import com.student.student_base_project.adapter.CardListAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.CardBean;
import com.student.student_base_project.db.BankDao;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.SPUtil;
import com.student.student_base_project.utils.StringUtils;
import com.student.student_base_project.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BankListActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.et_card_no)
    EditText etCardNo;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private Context mContxt;
    private BankDao bankDao;
    private List<CardBean> mList;
    private BankListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        setLeftTv("", true);
        setRootTitle("Bank List");
        mContxt = BankListActivity.this;

        bankDao = new BankDao(mContxt);
        mList = bankDao.queryBankList();
        initRecycleView();
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
                    String cardNo = cardBean.getCardNo();
                    String pwd = cardBean.getCardPwd();
                    SPUtil.put("cardNo", cardNo);
                    SPUtil.put("cardPwd", pwd);
                    Intent intent = new Intent(mContxt, BankListActivity.class);
                    intent.putExtra("cardNo", cardNo);
                    intent.putExtra("cardPwd", pwd);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    @OnClick({R.id.tv_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                commit();
                break;
        }
    }


    private void commit() {
        String cardNo = etCardNo.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (StringUtils.isEmpty(cardNo)) {
            ToastUtils.SingleToastUtil(mContxt,"account cannot be empty");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            ToastUtils.SingleToastUtil(mContxt,"password cannot be empty");
            return;
        }
        bankDao.add(new CardBean(System.currentTimeMillis(), cardNo, pwd));
        SPUtil.put("cardNo", cardNo);
        SPUtil.put("cardPwd", pwd);
        Intent intent = new Intent(mContxt, BankListActivity.class);
        intent.putExtra("cardNo", cardNo);
        intent.putExtra("cardPwd", pwd);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void initView() {

    }
}
