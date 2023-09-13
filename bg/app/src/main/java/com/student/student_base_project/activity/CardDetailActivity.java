package com.student.student_base_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.CardInfoBean;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.db.CardInfoDao;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.SPUtil;
import com.student.student_base_project.utils.StringUtils;
import com.student.student_base_project.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class CardDetailActivity extends BaseActivity {

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        setLeftTv("", true);
        setRootTitle("Item card details");
        String type = getIntent().getStringExtra("type");
        if (type.equals("limited_card")) {
            //Limited Cards
            cardType = "Limited number card";
            type = "Bike";
            price = "499";
            cover = R.mipmap.icon_qiche;
            cishu = "30Times";
            desc = "This card can be 40 times";
            tvYuanjia.setVisibility(View.VISIBLE);
        } else {
            //Unlimited Cards
            cardType = "Unlimited number card";
            type = "Yoga";
            price = "499";
            cover = R.mipmap.icon_yujia;
            cishu = "unlimited";
            desc = "This card is valid for 20 days from the date of purchase";
            tvYuanjia.setVisibility(View.INVISIBLE);
        }

        tvCardType.setText(cardType);
        tvCardType2.setText(cardType);
        tvType.setText(type);
        tvType2.setText(type);
        tvPrice.setText(price);
        ivCover.setImageResource(cover);
        tvDesc.setText(desc);
        tvCishu.setText(cishu);

        cardNo = (String) SPUtil.get("cardNo", "");
        cardPwd = (String) SPUtil.get("cardPwd", "");

        cardInfoDao = new CardInfoDao(CardDetailActivity.this);
    }

    @OnClick({R.id.tv_pay,R.id.rb_cards, R.id.rb_cash,R.id.check_box})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_cards:
                payType = 1;
                break;
            case R.id.rb_cash:
                payType = 2;
                break;
            case R.id.tv_pay:
                //Buy Now
                if (payType == 1) {
                    commit();
                } else {
                    commit2();
                }
                break;
            case R.id.check_box:
                boolean isChecked = checkBox.isChecked();
                if (isChecked) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    double zhekou = Double.parseDouble(price) * 0.8;
                    zhekouStr = decimalFormat.format(zhekou);
                    tvPrice.setText("￥"+ zhekouStr);
                } else {
                    zhekouStr = price;
                    tvPrice.setText("￥" + price);
                }
        }
    }
    private void commit2() {
        MyDialog dialog = new MyDialog(CardDetailActivity.this);
        dialog.setContentView(R.layout.dialog_pay_layout2);
        TextView tvCommit = dialog.findViewById(R.id.tv_commit);
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.SingleToastUtil(CardDetailActivity.this,"Thanks for using！");
                cardInfoDao.add(new CardInfoBean(System.currentTimeMillis(), cardType, type, zhekouStr, cover, cishu, desc,payType));
                dialog.dismiss();
                startActivity(new Intent(CardDetailActivity.this, CardListActivity.class));
            }
        });
        dialog.show();
    }

    private void commit() {
        MyDialog dialog = new MyDialog(CardDetailActivity.this);
        dialog.setContentView(R.layout.dialog_pay_layout);
        etCardNo = dialog.findViewById(R.id.et_card_no);
        etPwd = dialog.findViewById(R.id.et_pwd);
        TextView tvCommit = dialog.findViewById(R.id.tv_commit);
        if (!StringUtils.isEmpty(cardNo)) {
            etCardNo.setText(cardNo);
        }
        if (!StringUtils.isEmpty(cardPwd)) {
            etPwd.setText(cardPwd);
        }

        etCardNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(CardDetailActivity.this,BankListActivity.class),REQUEST_CARD_NO);
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String cardNo = etCardNo.getText().toString().trim();
//                String pwd = etPwd.getText().toString().trim();
//                if (StringUtils.isEmpty(cardNo)) {
//                    ToastUtils.SingleToastUtil(CardDetailActivity.this,"account cannot be empty");
//                    return;
//                }
//                if (StringUtils.isEmpty(pwd)) {
//                    ToastUtils.SingleToastUtil(CardDetailActivity.this,"password cannot be empty");
//                    return;
//                }
//                SPUtil.put("cardNo", cardNo);
                ToastUtils.SingleToastUtil(CardDetailActivity.this,"payment success");
                cardInfoDao.add(new CardInfoBean(System.currentTimeMillis(), cardType, type, zhekouStr, cover, cishu, desc,payType));
                dialog.dismiss();
                startActivity(new Intent(CardDetailActivity.this, CardListActivity.class));
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String cardNo = data.getStringExtra("cardNo");
            String cardPwd = data.getStringExtra("cardPwd");
            etCardNo.setText(cardNo);
            etPwd.setText(cardPwd);
        }
    }

    @Override
    protected void initView() {

    }
}
