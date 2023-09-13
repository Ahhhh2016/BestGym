package activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.CardInfoBean;
import com.student.student_base_project.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class CardOrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.tv_price3)
    TextView tvPrice3;
    @BindView(R.id.tv_price4)
    TextView tvPrice4;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private int payType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_order_detail);
        setLeftTv("", true);
        setRootTitle("Order Details");
        CardInfoBean cardInfoBean = (CardInfoBean) getIntent().getSerializableExtra("cardInfoBean");
        String price = cardInfoBean.getPrice();
        String cardType = cardInfoBean.getCardType();
        long id = cardInfoBean.getId();
        payType = cardInfoBean.getPayType();
        tvPrice.setText(price);
        tvPrice2.setText(price);
        tvPrice3.setText(price);
        tvPrice4.setText(price);
        tvCardType.setText(cardType);
        tvOrderNo.setText("" + id);
        tvTime.setText(StringUtils.getCurrentTime());
        tvPayType.setText(payType == 1 ? "Cards" : "Cash");
        Date date = new Date(id);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tvTime.setText(sd.format(date));
    }

    @Override
    protected void initView() {

    }
}
