package activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;

public class VipActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        setLeftTv("", true);
        setRootTitle("The rights and interests of I");

    }

    @Override
    protected void initView() {

    }
}
