package activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.db.CourseListDao;
import com.student.student_base_project.db.SubscribeDao;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.SPUtil;
import com.student.student_base_project.utils.StringUtils;
import com.student.student_base_project.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    private static final int REQUEST_CARD_NO = 1000;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.check_box)
    CheckBox checkBox;
    private int payType = 1;
    private SubscribeDao subscribeDao;
    private String time;
    private String type;
    private String price;
    private String date;
    private String content;
    private int cover;
    private String cardNo;
    private long id;
    private CourseListDao courseListDao;
    private String zhekouStr;
    private TextView etCardNo;
    private TextView etPwd;
    private String cardPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setLeftTv("", true);
        setRootTitle("Confirm Message");

        id = getIntent().getLongExtra("id", 0);
        type = getIntent().getStringExtra("type");
        time = getIntent().getStringExtra("time");
        price = getIntent().getStringExtra("price");
        date = getIntent().getStringExtra("date");
        content = getIntent().getStringExtra("content");
        cover = getIntent().getIntExtra("cover", 0);

        tvType.setText(type);
        tvTime.setText(date + "  " + time);
        tvPrice.setText(price);
        tvRemark.setText(StringUtils.isEmpty(content) ? "NONE" : content);

        subscribeDao = new SubscribeDao(PayActivity.this);

        cardNo = (String) SPUtil.get("cardNo", "");
        cardPwd = (String) SPUtil.get("cardPwd", "");


        //订阅就修改次数
        courseListDao = new CourseListDao(PayActivity.this);
    }

    @OnClick({R.id.rb_cards, R.id.rb_cash,R.id.tv_commit,R.id.check_box})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_cards:
                payType = 1;
                break;
            case R.id.rb_cash:
                payType = 2;
                break;
            case R.id.tv_commit:
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
                break;
        }
    }

    private void commit2() {
        MyDialog dialog = new MyDialog(PayActivity.this);
        dialog.setContentView(R.layout.dialog_pay_layout2);
        TextView tvCommit = dialog.findViewById(R.id.tv_commit);
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.SingleToastUtil(PayActivity.this,"Thank you for using！");
                subscribeDao.add(new SubscribeBean(System.currentTimeMillis(), (id > 0 ? id : System.currentTimeMillis()), type, time, zhekouStr, R.mipmap.icon_yujia, date, content, payType));
                //已经有人订阅
                SPUtil.put(ConstantUtils.isSubscribe, 1);
                updateCourseListNumber();

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateCourseListNumber() {
        String number = courseListDao.queryNumberById(id);
        if (StringUtils.isEmpty(number)) {
            return;
        }
        int n = Integer.valueOf(number);
        int newNumber = ++n;
        Log.d("Dong", "-----" + newNumber);
        courseListDao.updateUserInfo(String.valueOf(n), id);
    }

    private void commit() {
        MyDialog dialog = new MyDialog(PayActivity.this);
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
                startActivityForResult(new Intent(PayActivity.this, BankListActivity.class),REQUEST_CARD_NO);
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String cardNo = etCardNo.getText().toString().trim();
//                String pwd = etPwd.getText().toString().trim();
//                if (StringUtils.isEmpty(cardNo)) {
//                    ToastUtils.SingleToastUtil(PayActivity.this,"account cannot be empty");
//                    return;
//                }
//                if (StringUtils.isEmpty(pwd)) {
//                    ToastUtils.SingleToastUtil(PayActivity.this,"password cannot be empty");
//                    return;
//                }
//                SPUtil.put("cardNo", cardNo);
                ToastUtils.SingleToastUtil(PayActivity.this,"payment success");
                subscribeDao.add(new SubscribeBean(System.currentTimeMillis(), System.currentTimeMillis(), type, time, zhekouStr, R.mipmap.icon_yujia, date, content,payType));
                //已经有人订阅
                SPUtil.put(ConstantUtils.isSubscribe, 1);
                updateCourseListNumber();
                dialog.dismiss();
                finish();
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
