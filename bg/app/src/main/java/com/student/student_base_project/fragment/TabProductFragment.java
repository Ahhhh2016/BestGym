package com.student.student_base_project.fragment;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.TitleAdapter;
import com.student.student_base_project.adapter.ViewpagerAdapter;
import com.student.student_base_project.base.BaseFragment;
import java.util.ArrayList;

import butterknife.BindView;

public class TabProductFragment extends BaseFragment {
    @BindView(R.id.text_title)
    TextView tvTitle;
    @BindView(R.id.title_ensure)
    TextView tvRight;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;


    private TitleAdapter titleAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTitle = new ArrayList<>();


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_tab_product;
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        tvTitle.setText("Booking");
        initTabLayout();
    }

    private void initTabLayout() {
        mTitle.add("Course");
        mTitle.add("Tutor");
        mTitle.add("Others");
        //设置数据
        mFragments.add(new CourseFragment());
        mFragments.add(new TutorFragment());
        mFragments.add(new OthersFragment());
        ViewpagerAdapter adapter = new ViewpagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitle);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
    }
}
