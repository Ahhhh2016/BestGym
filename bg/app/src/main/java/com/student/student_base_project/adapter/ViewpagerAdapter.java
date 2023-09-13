package com.student.student_base_project.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragment;
    private List<String> mTitles;
    private FragmentTransaction mCurTransaction;
    private FragmentManager mFragmentManager;

    public ViewpagerAdapter(FragmentManager fm, List<Fragment> mFragment, List<String> mTitles) {
        super(fm);
        mFragmentManager = fm;
        this.mFragment = mFragment;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     //   super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }


    /**
     * 清除缓存fragment
     * @param container ViewPager
     */
    public void clear(ViewGroup container){
//        if (this.mCurTransaction == null) {
//            this.mCurTransaction = this.mFragmentManager.beginTransaction();
//        }
//
//        for (int i = 0; i < mFragment.size(); i++) {
//            long itemId = this.getItemId(i);
//            String name = makeFragmentName(container.getId(), itemId);
//            Fragment fragment = this.mFragmentManager.findFragmentByTag(name);
//            if (fragment != null) {//根据对应的ID，找到fragment，删除
//                mCurTransaction.hide(fragment);
//            }
//        }
//        mCurTransaction.commitNowAllowingStateLoss();
    }

    /**
     * 等同于FragmentPagerAdapter的makeFragmentName方法，
     * 由于父类的该方法是私有的，所以在此重新定义
     * @param viewId
     * @param id
     * @return
     */
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
