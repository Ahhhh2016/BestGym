package com.student.student_base_project.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.student.student_base_project.MainActivity;
import com.student.student_base_project.R;
import com.student.student_base_project.activity.CardDetailActivity;
import com.student.student_base_project.activity.ImagesPreviewActivity;
import com.student.student_base_project.adapter.TabHomeAdapter;
import com.student.student_base_project.base.BaseFragment;
import com.student.student_base_project.bean.HomeDataBean;
import com.student.student_base_project.utils.SpaceItemDecoration;
import com.student.student_base_project.utils.StringUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TabHomeFragment extends BaseFragment {

    @BindView(R.id.mzbanner)
    MZBannerView mzBannerView;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.text_title)
    TextView tvTitle;

    private List<Integer> bannerList = new ArrayList<>();
    private List<HomeDataBean> mList = new ArrayList<>();
    private TabHomeAdapter adapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_tab_home;
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        tvTitle.setText("Home");

        bannerList.add(R.mipmap.banner1);
        bannerList.add(R.mipmap.banner2);
        bannerList.add(R.mipmap.banner3);
        bannerList.add(R.mipmap.banner4);
        initBanner(bannerList);
        initRcyclViewData();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(StringUtils.dip2px(getActivity(),2)));
        adapter = new TabHomeAdapter(mList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((MainActivity) getActivity()).setIndexSelected(1);
            }
        });
    }

    //init data
    private void initRcyclViewData() {
        HomeDataBean homeDataBean = new HomeDataBean();
        homeDataBean.setImageUrl(R.mipmap.gym);
        homeDataBean.setTitle("Gym");
        homeDataBean.setTypeId(1);

        HomeDataBean homeDataBean2 = new HomeDataBean();
        homeDataBean2.setImageUrl(R.mipmap.runing);
        homeDataBean2.setTitle("Running");
        homeDataBean2.setTypeId(2);

        HomeDataBean homeDataBean3 = new HomeDataBean();
        homeDataBean3.setImageUrl(R.mipmap.skiing);
        homeDataBean3.setTitle("Skiing");
        homeDataBean3.setTypeId(3);

        HomeDataBean homeDataBean4 = new HomeDataBean();
        homeDataBean4.setImageUrl(R.mipmap.swimming);
        homeDataBean4.setTitle("Swimming");
        homeDataBean4.setTypeId(4);

        HomeDataBean homeDataBean5 = new HomeDataBean();
        homeDataBean5.setImageUrl(R.mipmap.yoga);
        homeDataBean5.setTitle("Yoga");
        homeDataBean5.setTypeId(5);


        HomeDataBean homeDataBean6 = new HomeDataBean();
        homeDataBean6.setImageUrl(R.mipmap.boxing);
        homeDataBean6.setTitle("Boxing");
        homeDataBean6.setTypeId(6);

        HomeDataBean homeDataBean7 = new HomeDataBean();
        homeDataBean7.setImageUrl(R.mipmap.bike);
        homeDataBean7.setTitle("Bike");
        homeDataBean7.setTypeId(7);

        HomeDataBean homeDataBean8 = new HomeDataBean();
        homeDataBean8.setImageUrl(R.mipmap.tennis);
        homeDataBean8.setTitle("Tennis");
        homeDataBean8.setTypeId(8);

        mList.add(homeDataBean);
        mList.add(homeDataBean2);
        mList.add(homeDataBean3);
        mList.add(homeDataBean4);
        mList.add(homeDataBean5);
        mList.add(homeDataBean6);
        mList.add(homeDataBean7);
        mList.add(homeDataBean8);
    }

    //初始化banner控件
    private void initBanner(List<Integer> bannerList) {
        mzBannerView.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int i) {
               startActivity(new Intent(getActivity(), ImagesPreviewActivity.class)
                        .putExtra("pos", i)
                        .putExtra("images", (Serializable) bannerList));
            }
        });
        mzBannerView.setIndicatorVisible(false);
        mzBannerView.setPages(bannerList, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView =  view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            Glide.with(getActivity()).load(data).skipMemoryCache(false).error(R.mipmap.banner1).dontAnimate().into(mImageView);
        }
    }

    @OnClick({R.id.layout_limited,R.id.layout_unlimited})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.layout_limited:
                startActivity(new Intent(getActivity(), CardDetailActivity.class).putExtra("type","limited_card"));
                break;
            case R.id.layout_unlimited:
                startActivity(new Intent(getActivity(), CardDetailActivity.class).putExtra("type","unlimited_card"));
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {

    }
}
