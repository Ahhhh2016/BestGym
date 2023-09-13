package com.student.student_base_project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.CourseBean;
import com.student.student_base_project.bean.SubscribeBean;
import com.student.student_base_project.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class CourseListDao {
    private static final String TABLE_NAME = "u_course_list";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public CourseListDao(Context context) {

        helper = new DBOpenHelper(context);
    }


    /***
     * 添加
     * @param courseBean
     */
    public void add(CourseBean courseBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert into u_course_list(id,type,time,price,status,number,cover) values (?,?,?,?,?,?,?)";
        db.execSQL(sql, new Object[]
                {
                        courseBean.getId(), courseBean.getType(), courseBean.getTime(),
                        courseBean.getPrice(), courseBean.getStatus(),
                        courseBean.getNumber(), courseBean.getCover()
                });
        db.close();
    }

    /***
     * 查询所有
     * @return
     */
    public List<CourseBean> queryCourseList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CourseBean> userBeanList = new ArrayList<>();
        String sql = "select * from u_course_list";
        Cursor cursor = db.rawQuery(sql, new String[]{
        });
        while (cursor.moveToNext()) {
            userBeanList.add(new CourseBean(cursor.getLong(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("type")),
                            cursor.getString(cursor.getColumnIndex("time")),
                            cursor.getString(cursor.getColumnIndex("price")),
                            cursor.getString(cursor.getColumnIndex("status")),
                            cursor.getString(cursor.getColumnIndex("number")),
                            cursor.getInt(cursor.getColumnIndex("cover"))
                    )
            );
        }
        return userBeanList;
    }

    /***
     * 查询当前id下的预约次数
     * @param id
     * @return
     */
    public String queryNumberById(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id","number"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        while (cursor.moveToNext()) {
            String number = cursor.getString(cursor.getColumnIndex("number"));
            return number;
        }
        return "";
    }

    /***
     * 修改产品信息
     * @param number
     * @param id
     */
    public void updateUserInfo(String number, long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number",number);
        db.update("u_course_list", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
