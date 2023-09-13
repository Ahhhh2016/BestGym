package com.student.student_base_project.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.student.student_base_project.R;
import com.student.student_base_project.activity.BankListActivity;
import com.student.student_base_project.activity.BankListActivity2;
import com.student.student_base_project.activity.CardListActivity;
import com.student.student_base_project.activity.InfoActivity;
import com.student.student_base_project.activity.LoginActivity;
import com.student.student_base_project.activity.MyBookListActivity;
import com.student.student_base_project.activity.NoticeActivity;
import com.student.student_base_project.activity.SubscribeRecordListActivity;
import com.student.student_base_project.activity.VipActivity;
import com.student.student_base_project.application.MyApplication;
import com.student.student_base_project.base.BaseFragment;
import com.student.student_base_project.manager.AppManager;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.MyDialog;
import com.student.student_base_project.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TabMineFragment extends BaseFragment {
    @BindView(R.id.text_title)
    TextView tvTitle;
    @BindView(R.id.layout_logout)
    LinearLayout layoutLogout;
    @BindView(R.id.tv_title)
    TextView tvUsername;
    @BindView(R.id.layout_notice)
    LinearLayout layoutNotice;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_tab_mine;
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        tvTitle.setText("Membership");
        String username = (String) SPUtil.get(ConstantUtils.phone, "");
        tvUsername.setText(username);
    }

    @OnClick({ R.id.layout_logout,R.id.layout_subscribe_list,R.id.layout_mybook,R.id.layout_vip,R.id.layout_notice,R.id.layout_info,R.id.layout_card_list,R.id.layoutBank})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.layout_logout:
                logout();
                break;
            case R.id.layout_subscribe_list:
                startActivity(new Intent(getActivity(), SubscribeRecordListActivity.class));
                break;
            case R.id.layout_mybook:
                //购买记录
                startActivity(new Intent(getActivity(), MyBookListActivity.class));
                break;
            case R.id.layout_vip:
                //vip
                startActivity(new Intent(getActivity(), VipActivity.class));
                break;
            case R.id.layout_notice:
                //notice
                startActivity(new Intent(getActivity(), NoticeActivity.class));
                break;
            case R.id.layout_info:
                startActivity(new Intent(getActivity(), InfoActivity.class));
                 break;
            case R.id.layout_card_list:
                startActivity(new Intent(getActivity(), CardListActivity.class));
                break;
            case R.id.layoutBank:
                startActivity(new Intent(getActivity(), BankListActivity2.class));
                break;
        }
    }


    private void logout() {
        MyDialog dialog = new MyDialog(getActivity());
        dialog.setMessage("Log Out or Not？");
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.clearAllData();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                AppManager.getInstance().finishAllActivity();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void initData() {

    }
}
