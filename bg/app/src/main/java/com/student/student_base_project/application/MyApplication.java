package com.student.student_base_project.application;

import android.app.Application;

import com.student.student_base_project.R;
import com.student.student_base_project.bean.CourseBean;
import com.student.student_base_project.db.CardInfoDao;
import com.student.student_base_project.db.CourseListDao;
import com.student.student_base_project.utils.ConstantUtils;
import com.student.student_base_project.utils.SPUtil;

import java.util.List;


public class MyApplication extends Application {

    private CourseListDao courseListDao;

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.init(this);
        courseListDao = new CourseListDao(this);
        boolean isSubscribe = SPUtil.contains(ConstantUtils.isSubscribe);
        if (isSubscribe) {
            int isSub = (int) SPUtil.get(ConstantUtils.isSubscribe, 0);
            if (isSub != 1) {
                //预约过
                initBookListData();
            }
        } else {
            List<CourseBean> mList = courseListDao.queryCourseList();
            if (mList.isEmpty()) {
                initBookListData();
            }
        }
    }


    private static MyApplication application;
    public static MyApplication getInstance() {
        if (application == null) {
            application = new MyApplication();
        }
        return application;
    }

    //获取当前登录状态
    public boolean isLogin() {
        boolean isFlag = SPUtil.contains(ConstantUtils.isLogin);
        if (isFlag) {
            boolean isL = (boolean) SPUtil.get(ConstantUtils.isLogin, false);
            return isL;
        }
        return false;
    }

    public static void clearAllData() {
        SPUtil.remove(ConstantUtils.isLogin);
    }

    //初始化Course列表数据
    private void initBookListData() {

        CourseBean courseBean = new CourseBean();
        courseBean.setCover(R.mipmap.img_course);
        courseBean.setId(1);
        courseBean.setNumber("0");
        courseBean.setPrice("499");
        courseBean.setStatus("Subscribe");
        courseBean.setTime("10:00~11:00");
        courseBean.setType("Boxing");
        courseListDao.add(courseBean);

        CourseBean courseBean2 = new CourseBean();
        courseBean2.setCover(R.mipmap.img_course);
        courseBean2.setId(2);
        courseBean2.setNumber("0");
        courseBean2.setPrice("499");
        courseBean2.setStatus("Subscribe");
        courseBean2.setTime("11:00~12:00");
        courseBean2.setType("Boxing");
        courseListDao.add(courseBean2);

        CourseBean courseBean3 = new CourseBean();
        courseBean3.setCover(R.mipmap.img_course);
        courseBean3.setId(3);
        courseBean3.setNumber("0");
        courseBean3.setPrice("499");
        courseBean3.setStatus("Subscribe");
        courseBean3.setTime("12:00~13:00");
        courseBean3.setType("Boxing");
        courseListDao.add(courseBean3);

        CourseBean courseBean4 = new CourseBean();
        courseBean4.setCover(R.mipmap.img_course);
        courseBean4.setId(4);
        courseBean4.setNumber("0");
        courseBean4.setPrice("499");
        courseBean4.setStatus("Subscribe");
        courseBean4.setTime("13:00~14:00");
        courseBean4.setType("Boxing");
        courseListDao.add(courseBean4);


        CourseBean courseBean5 = new CourseBean();
        courseBean5.setCover(R.mipmap.img_course);
        courseBean5.setId(5);
        courseBean5.setNumber("0");
        courseBean5.setPrice("499");
        courseBean5.setStatus("Subscribe");
        courseBean5.setTime("14:00~15:00");
        courseBean5.setType("Boxing");
        courseListDao.add(courseBean5);


        CourseBean courseBean6 = new CourseBean();
        courseBean6.setCover(R.mipmap.img_course);
        courseBean6.setId(6);
        courseBean6.setNumber("0");
        courseBean6.setPrice("499");
        courseBean6.setStatus("Subscribe");
        courseBean6.setTime("15:00~16:00");
        courseBean6.setType("Boxing");
        courseListDao.add(courseBean6);

        CourseBean courseBean7 = new CourseBean();
        courseBean7.setCover(R.mipmap.img_course);
        courseBean7.setId(7);
        courseBean7.setNumber("0");
        courseBean7.setPrice("499");
        courseBean7.setStatus("Subscribe");
        courseBean7.setTime("16:00~17:00");
        courseBean7.setType("Boxing");
        courseListDao.add(courseBean7);

        CourseBean courseBean8 = new CourseBean();
        courseBean8.setCover(R.mipmap.img_course);
        courseBean8.setId(8);
        courseBean8.setNumber("0");
        courseBean8.setPrice("499");
        courseBean8.setStatus("Subscribe");
        courseBean8.setTime("17:00~18:00");
        courseBean8.setType("Boxing");
        courseListDao.add(courseBean8);

        CourseBean courseBean9 = new CourseBean();
        courseBean9.setCover(R.mipmap.img_course);
        courseBean9.setId(9);
        courseBean9.setNumber("0");
        courseBean9.setPrice("499");
        courseBean9.setStatus("Subscribe");
        courseBean9.setTime("18:00~19:00");
        courseBean9.setType("Boxing");
        courseListDao.add(courseBean9);

        CourseBean courseBean10 = new CourseBean();
        courseBean10.setCover(R.mipmap.img_course);
        courseBean10.setId(10);
        courseBean10.setNumber("0");
        courseBean10.setPrice("499");
        courseBean10.setStatus("Subscribe");
        courseBean10.setTime("19:00~20:00");
        courseBean10.setType("Boxing");
        courseListDao.add(courseBean10);
    }

}
