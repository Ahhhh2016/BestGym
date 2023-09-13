package activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.bean.UserBean;
import com.student.student_base_project.db.UserDao;
import com.student.student_base_project.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_girl)
    RadioButton rbGirl;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.radio_stu)
    RadioButton rbStu;
    @BindView(R.id.radio_teacher)
    RadioButton rbTeacher;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private Context mContext;
    private int sex;
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
        setRootTitle("Register");
        setLeftTv("", true);

        userDao = new UserDao(mContext);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_commit,R.id.radio_teacher,R.id.radio_stu,R.id.rb_girl,R.id.rb_man})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                register();
                break;
            case R.id.radio_stu:
                break;
            case R.id.radio_teacher:
                break;
            case R.id.rb_girl:
                break;
            case R.id.rb_man:
                break;
        }
    }

    private void register() {
        String userName = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String confirmPwd = etConfirmPwd.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.SingleToastUtil(mContext,"The user name cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.SingleToastUtil(mContext,"Mobile phone number cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.SingleToastUtil(mContext,"The password cannot be empty");
            return;
        }
        if (!pwd.equals(confirmPwd)) {
            ToastUtils.SingleToastUtil(mContext,"The two passwords are inconsistent");
            return;
        }

        userDao.add(new UserBean(System.currentTimeMillis(), phone, userName, pwd));
        ToastUtils.SingleToastUtil(mContext,"Register Successfully!");
        finish();
    }
}
