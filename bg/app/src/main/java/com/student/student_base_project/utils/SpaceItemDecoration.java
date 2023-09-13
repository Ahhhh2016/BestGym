package com.student.student_base_project.utils;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        //outRect.left = space;
        //outRect.bottom = space;
        if (parent.getChildLayoutPosition(view) > 4) {
            Log.d("Dong", "");
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
