package com.student.student_base_project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ImagePreviewAdapter extends PagerAdapter {

    List<View> imgs;

    Context mContext;


    public ImagePreviewAdapter(Context context, List<View> imgs) {

        this.mContext = context;
        this.imgs = imgs;


    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() { // 获得size
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = imgs.get(position);

        (container).addView(view);
        return view;
    }
}
