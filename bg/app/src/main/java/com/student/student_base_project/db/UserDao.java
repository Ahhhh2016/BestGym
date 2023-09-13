package com.student.student_base_project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.UserBean;

public class UserDao {
    private static final String TABLE_NAME = "u_user";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public UserDao(Context context) {

        helper = new DBOpenHelper(context);
    }

    /***
     * 添加用户
     * @param userBean
     */
    public void add(UserBean userBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert or ignore into u_user(id,mobile,username,password) values (?,?,?,?)";
        db.execSQL(sql,new Object[]
                {
                        userBean.getId(),userBean.getMobile(),userBean.getUsername(),userBean.getPassword()
                });
        db.close();
    }

    /****
     * 判断用户是否存在
     * @param mobile
     * @param password
     * @return
     */
    public boolean isUserInfo(String mobile, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"mobile", "password"}, "mobile=?", new String[]{mobile}, null, null, "password");
        while (cursor.moveToNext()) {
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            if (password.equals(pwd)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /***
     * 查询用户名
     * @param account
     * @return
     */
    public String queryUserName(String account) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"account","username"}, "account=?", new String[]{account}, null, null, null);
        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            return username;
        }
        return "";
    }

    /***
     * 查询手机号
     * @param account
     * @return
     */
    public String queryMobile(String account) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"account","mobile"}, "account=?", new String[]{account}, null, null, null);
        while (cursor.moveToNext()) {
            String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
            return mobile;
        }
        return "";
    }


    /***
     * 修改用户信息
     * @param userBean
     * @param account
     */
    public void updateUserInfo(UserBean userBean,String account) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mobile",userBean.getMobile());
        values.put("username",userBean.getUsername());
        db.update("u_user", values, "account=?", new String[]{String.valueOf(account)});
        db.close();
    }
}
