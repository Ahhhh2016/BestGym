package com.student.student_base_project.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.student.student_base_project.R;
import com.student.student_base_project.manager.AppManager;
import com.student.student_base_project.utils.LoadingDailog;

import butterknife.ButterKnife;


public abstract class BaseActivity extends FragmentActivity {

    private View rootTitle;
    private TextView backTv;
    public LoadingDailog loadingDailog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getInstance().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        rootTitle = findViewById(R.id.root_title);
        if (rootTitle != null) {
            backTv = rootTitle.findViewById(R.id.button_backward);
            if (backTv != null) {
                backTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        }
        initView();
    }

    protected abstract void initView();

    /***
     * 设置标题
     * @param title
     */
    public void setRootTitle(String title) {
        TextView tvTitle = rootTitle.findViewById(R.id.text_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
    }


    public void setRightTitle(String title) {
        TextView tvRight = rootTitle.findViewById(R.id.title_ensure);
        tvRight.setText(title);
        tvRight.setVisibility(View.VISIBLE);


        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightClick();
            }
        });
    }

    public void setRightClick() {

    }


    /***
     * 设置标题
     * @param title  标题文字
     * @param bgColor  背景颜色
     */
    public void setRootTitle(String title,int bgColor) {
        TextView tvTitle = rootTitle.findViewById(R.id.text_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
        rootTitle.setBackgroundColor(getResources().getColor(bgColor));
    }

    /***
     * 设置标题
     * @param title  标题文字
     * @param bgColor 背景颜色
     * @param tvColor 文字颜色
     */
    public void setRootTitle(String title,int bgColor,int tvColor) {
        TextView tvTitle = rootTitle.findViewById(R.id.text_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
        rootTitle.setBackgroundColor(getResources().getColor(bgColor));
        tvTitle.setTextColor(getResources().getColor(tvColor));
    }

    /***
     * 设置返回按钮
     * @param title  标题
     * @param isVisible  是否隐藏
     */
    public void setLeftTv(String title, boolean isVisible) {
        backTv.setText(TextUtils.isEmpty(title) ? "" : title);
        backTv.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }


    /***
     * 终止正在加载对话框
     * @param  activity 上下文
     */
    public void stopIOSDialogLoading(final Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDailog != null && loadingDailog.isShowing() && !activity.isFinishing()) {
                    loadingDailog.dismiss();
                    loadingDailog = null;
                }
            }
        }, 0);
    }

    /****
     * 启动对话框
     * @param mContext
     */
    public void startIOSDialogLoading(Context mContext, String msg) {
        LoadingDailog.Builder builder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(false).setCancelOutside(false);
        if (null == loadingDailog) {
            loadingDailog = builder.create();
            if (!loadingDailog.isShowing()) {
                loadingDailog.show();
            }
        }
    }

}
