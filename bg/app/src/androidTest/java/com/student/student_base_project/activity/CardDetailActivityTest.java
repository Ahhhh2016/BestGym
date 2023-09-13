package com.student.student_base_project.activity;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.student.student_base_project.R;
import com.student.student_base_project.db.CardInfoDao;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.SPUtil;

import org.junit.Test;

import butterknife.BindView;

import static org.junit.Assert.*;

public class CardDetailActivityTest {

    private static final int REQUEST_CARD_NO = 1000;
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.tv_card_type2)
    TextView tvCardType2;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_type2)
    TextView tvType2;
    @BindView(R.id.tv_cishu)
    TextView tvCishu;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_yuanjia)
    TextView tvYuanjia;
    @BindView(R.id.check_box)
    CheckBox checkBox;

    private String cardType;
    private String type;
    private String price;
    private Integer cover;
    private String cishu;
    private String desc;
    private CardInfoDao cardInfoDao;
    private int payType = 1;
    private String cardNo;
    private String cardPwd;
    private TextView etCardNo;
    private TextView etPwd;
    private String zhekouStr;

    @Test
    public void onCreateTest() {
        String cardInfoDao = (String) SPUtil.get(ConstantUtils.phone, "");
        try {
            int expectedReturn;
            int actualReturn;
            expectedReturn = 1;
            actualReturn = Integer.parseInt(cardInfoDao.toString());
            assertEquals(
                    "success",
                    expectedReturn,
                    actualReturn);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void onActivityResultTest() {
        String cardNo = (String) SPUtil.get(ConstantUtils.phone, "");
        try {
            int expectedReturn;
            int actualReturn;
            expectedReturn = 1;
            actualReturn = Integer.parseInt(cardNo.toString());
            assertEquals(
                    "success",
                    expectedReturn,
                    actualReturn);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}