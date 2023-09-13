package com.student.student_base_project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.widget.PreviewViewPager;
import com.student.student_base_project.R;
import com.student.student_base_project.adapter.ImagePreviewAdapter;
import com.student.student_base_project.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ImagesPreviewActivity extends BaseActivity {
    @BindView(R.id.preview_vp)
    PreviewViewPager viewPager;
    private int pos;
    private List<Integer> images;
    private List<View> imageViews=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_preview);
        pos = getIntent().getIntExtra("pos", 0);
        images = (List<Integer>) getIntent().getSerializableExtra("images");
        if (images.isEmpty()) {
            return;
        }
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView=new ImageView(this);
            Glide.with(this).load(images.get(i)).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            imageViews.add(imageView);
        }
        viewPager.setAdapter(new ImagePreviewAdapter(this,imageViews));
        viewPager.setCurrentItem(pos);
    }

    @Override
    protected void initView() {

    }
}
