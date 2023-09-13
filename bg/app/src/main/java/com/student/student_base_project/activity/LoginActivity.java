package com.student.student_base_project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.MainActivity;
import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.LoginBean;
import com.student.student_base_project.db.UserDao;
import com.student.student_base_project.net.ApiSubscriber;
import com.student.student_base_project.net.HttpManager;
import com.student.student_base_project.net.MapToRequestBody;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.SPUtil;
import com.student.student_base_project.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.login_pwd_et)
    EditText etPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.forget_pwd_tv)
    TextView tvForgetPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.radio_stu)
    RadioButton rbStu;
    @BindView(R.id.radio_teacher)
    RadioButton rbTeacher;
    @BindView(R.id.radio_admin)
    RadioButton rbAdmin;

    private Context mContext;
    private UserDao userDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        setRootTitle("Login");
        userDao = new UserDao(mContext);
    }


    @OnClick({R.id.tv_login, R.id.tv_register, R.id.forget_pwd_tv,R.id.radio_stu,R.id.radio_teacher,R.id.radio_admin})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.forget_pwd_tv:
                break;
            case R.id.radio_admin:
                break;
            case R.id.radio_teacher:
                break;
            case R.id.radio_stu:
                break;
        }
    }

    private void login() {
        //Login
        String mobile = etMobile.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.SingleToastUtil(mContext, "Mobile phone number cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.SingleToastUtil(mContext, "password cannot be empty");
            return;
        }
        boolean isUser = userDao.isUserInfo(mobile, pwd);
        if (isUser) {
            ToastUtils.SingleToastUtil(mContext, "Login Sucess");
        }

        boolean isFlag = userDao.isUserInfo(mobile, pwd);
        if (!isFlag) {
            ToastUtils.SingleToastUtil(mContext, "user name or password is incorrect");
        } else {
            startActivity(new Intent(mContext, MainActivity.class));
            SPUtil.put(ConstantUtils.isLogin, true);
            SPUtil.put(ConstantUtils.phone, mobile);
            SPUtil.put(ConstantUtils.pwd, pwd);
            finish();
        }
    }


    @Override
    protected void initView() {

    }
}
