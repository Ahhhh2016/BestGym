package com.student.student_base_project.activity;

import android.widget.TextView;

import com.student.student_base_project.R;
import com.student.student_base_project.db.BankDao;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.SPUtil;

import org.junit.Test;

import butterknife.BindView;

import static org.junit.Assert.*;

public class BookOrderDetailActivityTest {
    @BindView(R.id.tv_yuyuema)
    TextView tvYuyuema;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @Test
    public void onCreate() {
        String phone = (String) SPUtil.get(ConstantUtils.phone, "");

        try {
            int expectedReturn;
            int actualReturn;
            expectedReturn = 0;
            actualReturn = Integer.parseInt(phone);
            assertEquals(
                    "success",
                    expectedReturn,
                    actualReturn);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}