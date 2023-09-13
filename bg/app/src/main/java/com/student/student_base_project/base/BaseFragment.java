package com.student.student_base_project.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    //当前是否可见
    private boolean isVisible = false;
    //是否已被加载过一次，第二次就不再去请求数据了
    protected boolean hasLoaded = false;
    //标志位，标志已经初始化完成
    protected boolean isPrepared = false;
    private Bundle arguments;
    public LayoutInflater inflater;
    public View mContentView;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    //当前fragment不可见调用
    protected void onInvisible() {

    }

    //当前fragment 由可见 ---> 不可见时调用
    protected void onVisible() {
        initData();
    }

    protected abstract int setLayoutResourceID();

    protected abstract void initData();

    protected void setUpView() {

    }

    protected void setUpData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContentView = null;
        isPrepared = false;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getActivity());
        hasLoaded = false;
        mContentView = inflater.inflate(setLayoutResourceID(), null,false);
        ButterKnife.bind(this, mContentView);
        arguments = getArguments();
        setUpView();
        return mContentView;
    }


    @Override
    public void onResume() {
        super.onResume();

        setUpData();
        initData();
    }
}
