package com.student.student_base_project.activity;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.BankListAdapter;
import com.student.student_base_project.bean.CardBean;
import com.student.student_base_project.db.BankDao;

import org.junit.Test;

import java.util.List;

import butterknife.BindView;

import static org.junit.Assert.*;

public class BankListActivity2Test {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private Context mContxt;
    private BankDao bankDao;
    private List<CardBean> mList;
    private BankListAdapter adapter;

    @Test
    public void onCreate2() {
        bankDao = new BankDao(mContxt);
        try {
            int expectedReturn;
            int actualReturn;
            expectedReturn = 0;
            actualReturn = Integer.parseInt(bankDao.toString());
            assertEquals(
                    "success",
                    expectedReturn,
                    actualReturn);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}