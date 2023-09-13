package activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.RoundedImage.RoundedImageView;
import com.student.student_base_project.adapter.DateWeekAdapter;
import com.student.student_base_project.adapter.TimeAdapter;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.DateOrWeekBean;
import com.student.student_base_project.bean.TimeDataBean;
import com.student.student_base_project.db.SubscribeDao;
import com.student.student_base_project.utils.BaseDialog;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.SpaceItemDecoration;
import com.student.student_base_project.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TutorSubscribeActivity extends BaseActivity {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.recycleView2)
    RecyclerView recyclerView2;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;

    private List<DateOrWeekBean> mDateList = new ArrayList<>();
    private DateWeekAdapter dateWeekAdapter;
    private List<TimeDataBean> mTimeList = new ArrayList<>();
    private TimeAdapter timeAdapter;
    private SubscribeDao subscribeDao;

    private String time;
    private String date;
    private String week;
    private int cover;
    private String price;
    private String typeS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_subscribe);
        setLeftTv("", true);
        setRootTitle("New booking");

        getDayOrWeek();
        initTimeData();
        LinearLayoutManager manager = new LinearLayoutManager(TutorSubscribeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        dateWeekAdapter = new DateWeekAdapter(mDateList);
        recyclerView.setAdapter(dateWeekAdapter);

        String type = getIntent().getStringExtra("type");
        if (type.equals("bike")) {
            //others
            cover = R.mipmap.icon_qiche;
            typeS = "Bike";
            price = "599";
        } else {
            cover = R.mipmap.icon_yujia;
            typeS = "Yoga";
            price = "799";
        }
        ivCover.setImageResource(cover);
        tvType.setText(typeS);
        tvPrice.setText(price);
        tvPrice2.setText(price);

        date = mDateList.get(0).getDate();
        week = mDateList.get(0).getWeek();
        dateWeekAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dateWeekAdapter.setClickPos(position);
                dateWeekAdapter.notifyDataSetChanged();
            }
        });

        time = mTimeList.get(0).getTimeValue();
        GridLayoutManager manager2 = new GridLayoutManager(TutorSubscribeActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new SpaceItemDecoration(StringUtils.dip2px(TutorSubscribeActivity.this,2)));
        recyclerView2.setLayoutManager(manager2);
        timeAdapter = new TimeAdapter(mTimeList);
        recyclerView2.setAdapter(timeAdapter);


        timeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TimeDataBean timeDataBean = (TimeDataBean) adapter.getItem(position);
                if (timeDataBean != null) {
                    tvTime2.setText("Selected：" + timeDataBean.getTimeValue());
                    time = timeDataBean.getTimeValue();
                }
                timeAdapter.setClickPos(position);
                timeAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.tv_detail,R.id.tv_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail:
                //查看详情
                Dialog dialog = BaseDialog.showDialog(TutorSubscribeActivity.this, R.layout.dialog_bottom_layout);
                ImageView ivDel = dialog.findViewById(R.id.iv_del);
                RoundedImageView ivCover = dialog.findViewById(R.id.iv_cover);
                TextView tvPrice = dialog.findViewById(R.id.tv_price);
                TextView tvType = dialog.findViewById(R.id.tv_type);
                tvPrice.setText(price);
                tvType.setText(typeS);
                ivCover.setImageResource(cover);
                ivDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.tv_commit:
                commitDialog();
                break;
        }
    }


    //提交对话框
    private void commitDialog() {
        MyDialog myDialog = new MyDialog(TutorSubscribeActivity.this);
        myDialog.setMessage("Confirm the appointment？");
        myDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();
                startActivity(new Intent(TutorSubscribeActivity.this, PayActivity.class)
                        .putExtra("type", "Yoga")
                        .putExtra("time", time)
                        .putExtra("price", "799")
                        .putExtra("cover", R.mipmap.icon_yujia)
                        .putExtra("date", date + week)
                        .putExtra("content", content)
                );
                finish();
                myDialog.dismiss();
            }
        });

        myDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    private void initTimeData() {
        TimeDataBean timeDataBean = new TimeDataBean();
        timeDataBean.setTimeTitle("11:00");
        timeDataBean.setTimeValue("11:00~12:00");

        TimeDataBean timeDataBean2 = new TimeDataBean();
        timeDataBean2.setTimeTitle("12:00");
        timeDataBean2.setTimeValue("12:00~13:00");

        TimeDataBean timeDataBean3 = new TimeDataBean();
        timeDataBean3.setTimeTitle("13:00");
        timeDataBean3.setTimeValue("13:00~14:00");

        TimeDataBean timeDataBean4 = new TimeDataBean();
        timeDataBean4.setTimeTitle("14:00");
        timeDataBean4.setTimeValue("14:00~15:00");

        TimeDataBean timeDataBean5 = new TimeDataBean();
        timeDataBean5.setTimeTitle("15:00");
        timeDataBean5.setTimeValue("15:00~16:00");

        TimeDataBean timeDataBean6 = new TimeDataBean();
        timeDataBean6.setTimeTitle("16:00");
        timeDataBean6.setTimeValue("16:00~17:00");

        TimeDataBean timeDataBean7 = new TimeDataBean();
        timeDataBean7.setTimeTitle("17:00");
        timeDataBean7.setTimeValue("17:00~18:00");

        TimeDataBean timeDataBean8 = new TimeDataBean();
        timeDataBean8.setTimeTitle("18:00");
        timeDataBean8.setTimeValue("18:00~19:00");

        TimeDataBean timeDataBean9 = new TimeDataBean();
        timeDataBean9.setTimeTitle("19:00");
        timeDataBean9.setTimeValue("19:00~20:00");

        TimeDataBean timeDataBean10 = new TimeDataBean();
        timeDataBean10.setTimeTitle("20:00");
        timeDataBean10.setTimeValue("20:00~21:00");

        TimeDataBean timeDataBean11 = new TimeDataBean();
        timeDataBean11.setTimeTitle("21:00");
        timeDataBean11.setTimeValue("21:00~22:00");

        mTimeList.add(timeDataBean);
        mTimeList.add(timeDataBean2);
        mTimeList.add(timeDataBean3);
        mTimeList.add(timeDataBean4);
        mTimeList.add(timeDataBean5);
        mTimeList.add(timeDataBean6);
        mTimeList.add(timeDataBean7);
        mTimeList.add(timeDataBean8);
        mTimeList.add(timeDataBean9);
        mTimeList.add(timeDataBean10);
        mTimeList.add(timeDataBean11);
    }

    //根据日期取得星期几
    public static String getWeek(Date date){
        String[] weeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }
    //获取后10天的日期
    private void getDayOrWeek() {
        mDateList.clear();
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM.dd");
        for (int i = 0; i < 10; i++) {
            calendar1.add(Calendar.DATE, i);
            //获取周几
            String week = getWeek(calendar1.getTime());
            //获取日期
            String day = sdf1.format(calendar1.getTime());
            DateOrWeekBean dateOrWeekBean = new DateOrWeekBean();
            dateOrWeekBean.setDate(day);
            dateOrWeekBean.setWeek(week);
            mDateList.add(dateOrWeekBean);

            //重置日期，不然会倍增日期
            calendar1.add(Calendar.DATE, -i);
        }
    }

    @Override
    protected void initView() {

    }
}
