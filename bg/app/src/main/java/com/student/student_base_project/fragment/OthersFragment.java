package com.student.student_base_project.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.activity.TutorSubscribeActivity;
import com.student.student_base_project.adapter.BookListAdapter;
import com.student.student_base_project.adapter.DateWeekAdapter;
import com.student.student_base_project.adapter.OtherAdapter;
import com.student.student_base_project.base.BaseFragment;
import com.student.student_base_project.bean.CourseBean;
import com.student.student_base_project.bean.DateOrWeekBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OthersFragment extends BaseFragment {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.recycleView2)
    RecyclerView recyclerView2;

    private List<DateOrWeekBean> mDateList = new ArrayList<>();
    private DateWeekAdapter dateWeekAdapter;
    private List<CourseBean> otherList = new ArrayList<>();

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_others;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        initRecycleView();

    }

    private void initRecycleView() {
        initBookListData();
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

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(manager2);
        OtherAdapter otherAdapter = new OtherAdapter(otherList);
        recyclerView2.setAdapter(otherAdapter);
        otherAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), TutorSubscribeActivity.class)
                        .putExtra("type", "bike"));
            }
        });
    }

    //初始化列表数据
    private void initBookListData() {
        CourseBean courseBean = new CourseBean();
        courseBean.setCover(R.mipmap.icon_qiche);
        courseBean.setId(System.currentTimeMillis());
        courseBean.setNumber("0");
        courseBean.setPrice("￥599");
        courseBean.setStatus("Subscribe");
        courseBean.setTime("10:00 | 11:00 | ... | 21:00 | multi-period");
        courseBean.setType("Bike");
        otherList.add(courseBean);

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

    @OnClick({R.id.rb_keep, R.id.rb_all})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_keep:
                break;
            case R.id.rb_all:
                break;
        }
    }


    @Override
    protected void initData() {

    }
}
