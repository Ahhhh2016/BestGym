package com.student.student_base_project.activity;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.student.student_base_project.R;
import com.student.student_base_project.adapter.BankListAdapter;
import com.student.student_base_project.bean.CardBean;
import com.student.student_base_project.db.BankDao;

import org.junit.Test;

import java.util.List;

import butterknife.BindView;

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

import static org.junit.Assert.*;

public class BankListActivityTest {

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

    @Test
    public void onCreate1() {
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