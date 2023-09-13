package com.student.student_base_project.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.R;
import com.student.student_base_project.activity.SubscribeActivity;
import com.student.student_base_project.adapter.BookListAdapter;
import com.student.student_base_project.adapter.DateWeekAdapter;
import com.student.student_base_project.base.BaseFragment;
import com.student.student_base_project.bean.CourseBean;
import com.student.student_base_project.bean.DateOrWeekBean;
import com.student.student_base_project.db.CourseListDao;
import com.student.student_base_project.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseFragment extends BaseFragment {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.recycleView2)
    RecyclerView recyclerView2;

    private List<DateOrWeekBean> mDateList = new ArrayList<>();
    private DateWeekAdapter dateWeekAdapter;
    private BookListAdapter bookListAdapter;

    private List<CourseBean> courseBeanList = new ArrayList<>();
    private String date;
    private String week;
    private CourseListDao courseListDao;


    @Override
    protected void setUpView() {
        super.setUpView();
        initRecycleView();

    }

    private void initRecycleView() {
        courseListDao = new CourseListDao(getActivity());
        getDayOrWeek();
        initCourseList();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        dateWeekAdapter = new DateWeekAdapter(mDateList);
        recyclerView.setAdapter(dateWeekAdapter);

        date = mDateList.get(0).getDate();
        week = mDateList.get(0).getWeek();

        dateWeekAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DateOrWeekBean weekBean = (DateOrWeekBean) adapter.getItem(position);
                date = weekBean.getDate();
                week = weekBean.getWeek();
                dateWeekAdapter.setClickPos(position);
                dateWeekAdapter.notifyDataSetChanged();
            }
        });

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(manager2);
        bookListAdapter = new BookListAdapter(courseBeanList);
        recyclerView2.setAdapter(bookListAdapter);

        bookListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //Subscribe
                CourseBean courseBean = (CourseBean) adapter.getItem(position);
                String num = courseBean.getNumber();
                if (Integer.valueOf(num) >= 3) {
                    ToastUtils.SingleToastUtil(getActivity(), "only third times ！");
                    return;
                }
                if (courseBean != null) {
                    startActivity(new Intent(getActivity(), SubscribeActivity.class)
                            .putExtra("id", courseBean.getId())
                            .putExtra("type", courseBean.getType())
                            .putExtra("price", courseBean.getPrice())
                            .putExtra("cover", courseBean.getCover())
                            .putExtra("time", courseBean.getTime())
                            .putExtra("date", date)
                            .putExtra("week", week)
                    )
                    ;
                }
            }
        });
    }

    private void initCourseList() {
        courseBeanList = courseListDao.queryCourseList();
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
    protected int setLayoutResourceID() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initData() {

    }
}
