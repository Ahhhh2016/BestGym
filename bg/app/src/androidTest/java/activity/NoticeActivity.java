package activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.student.student_base_project.R;
import com.student.student_base_project.base.BaseActivity;

public class NoticeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setLeftTv("", true);
        setRootTitle("Message");

    }

    @Override
    protected void initView() {

    }
}
