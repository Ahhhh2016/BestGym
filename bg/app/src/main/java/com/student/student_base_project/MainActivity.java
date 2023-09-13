package com.student.student_base_project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.student.student_base_project.base.BaseActivity;
import com.student.student_base_project.fragment.TabHomeFragment;
import com.student.student_base_project.fragment.TabMineFragment;
import com.student.student_base_project.fragment.TabProductFragment;
import com.student.student_base_project.utils.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private RadioButton rbTabHome;
    private RadioButton rbTabProduct;
    private RadioButton rbShoppingCart;
    private RadioButton rbTabAccount;
    //Store fragment array
    private Fragment[] mFragments;
    private int mIndex = 0;
    private long mExitTime;
    private TabHomeFragment tabHomeFragment;
    private TabProductFragment tabProductFragment;
    private TabMineFragment tabMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        //tab on the bottom
        rbTabHome = findViewById(R.id.rb_tab_home);
        rbTabProduct = findViewById(R.id.rb_tab_product);
        rbShoppingCart = findViewById(R.id.rb_tab_health);
        rbTabAccount = findViewById(R.id.rb_tab_account);

        rbTabHome.setOnClickListener(this);
        rbTabProduct.setOnClickListener(this);
        rbTabAccount.setOnClickListener(this);
        rbShoppingCart.setOnClickListener(this);

        requestPermission();
        initFragment();
    }
    private void initFragment() {
        //Homepage
        tabHomeFragment = new TabHomeFragment();
        //Choose
        tabProductFragment = new TabProductFragment();
        //My
        tabMineFragment = new TabMineFragment();
        //Add data
        mFragments = new Fragment[]{tabHomeFragment, tabProductFragment/*,tabShoppingCartFragment*/, tabMineFragment};
        //Begin transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //Add home
        ft.add(R.id.framelayout, tabHomeFragment).commit();
        //set 0
        setIndexSelected(0);
    }

    public void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        if (index == 1) {
            rbTabProduct.setChecked(true);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //Hidden
        ft.hide(mFragments[mIndex]);

        if (!mFragments[index].isAdded()) {
            ft.add(R.id.framelayout, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();

        mIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_tab_home:
                setIndexSelected(0);
                break;
            case R.id.rb_tab_product:
                setIndexSelected(1);
                break;
       /*     case R.id.rb_tab_health:
                setIndexSelected(2);
                break;*/
            case R.id.rb_tab_account:
                setIndexSelected(2);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) { // If the interval between two keystrokes is greater than 2000 ms, the system does not exit
                ToastUtils.SingleToastUtil(this, "Press again to exit the program");
                mExitTime = System.currentTimeMillis();// mExitTime
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {

    }

    private void requestPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(data -> {

                })
                .start();
    }
}
