package com.student.student_base_project.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.activity.TutorSubscribeActivity;
import com.student.student_base_project.adapter.DateWeekAdapter;
import com.student.student_base_project.base.BaseFragment;
import com.student.student_base_project.bean.DateOrWeekBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TutorFragment extends BaseFragment {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.layout_bottom_container)
    LinearLayout layoutBottomContainer;
    @BindView(R.id.tv_course)
    TextView tvCourse;

    private DateWeekAdapter dateWeekAdapter;

    private List<DateOrWeekBean> mDateList = new ArrayList<>();
    private boolean isOpen;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_tutor;
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        getDayOrWeek();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        dateWeekAdapter = new DateWeekAdapter(mDateList);
        recyclerView.setAdapter(dateWeekAdapter);

        dateWeekAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dateWeekAdapter.setClickPos(position);
                dateWeekAdapter.notifyDataSetChanged();
            }
        });
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

    @OnClick({R.id.rb_keep, R.id.rb_all,R.id.tv_course,R.id.tv_yuyue})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_keep:
                break;
            case R.id.rb_all:
                break;
            case R.id.rb_shape:
                break;
            case R.id.tv_course:
                isOpen = !isOpen;
                Drawable arrowUp = getResources().getDrawable(R.mipmap.icon_up);
                Drawable arrowDown = getResources().getDrawable(R.mipmap.icon_down);
                if (isOpen) {
                    layoutBottomContainer.setVisibility(View.VISIBLE);
                    tvCourse.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowUp, null);
                    tvCourse.setCompoundDrawablePadding(10);
                } else {
                    layoutBottomContainer.setVisibility(View.GONE);
                    tvCourse.setCompoundDrawablePadding(10);
                    tvCourse.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);
                }

                break;
            case R.id.tv_yuyue:
                //预约
                startActivity(new Intent(getActivity(), TutorSubscribeActivity.class)
                .putExtra("type","Yoga"));
                break;
        }
    }

    @Override
    protected void initData() {

    }
}
