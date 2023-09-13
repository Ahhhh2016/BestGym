package activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.SPUtil;

import butterknife.BindView;

public class BookOrderDetailActivity extends BaseActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_order_detail);
        setLeftTv("", true);
        setRootTitle("Booking record");
        SubscribeBean subscribeBean = (SubscribeBean) getIntent().getSerializableExtra("subscribeBean");

        String phone = (String) SPUtil.get(ConstantUtils.phone, "");
        long id = subscribeBean.getId();
        int payType = subscribeBean.getPayType();
        String time = subscribeBean.getTime();
        String date = subscribeBean.getDate();
        String remark = subscribeBean.getRemark();
        String type = subscribeBean.getType();

        tvYuyuema.setText("code：" + id);
        tvMobile.setText(phone);
        tvRemark.setText(remark);
        tvType.setText(type);
        tvTime.setText("2022." + date + time);
        tvPayType.setText(payType == 1 ? "Cards" : "Cash");
    }

    @Override
    protected void initView() {

    }
}
