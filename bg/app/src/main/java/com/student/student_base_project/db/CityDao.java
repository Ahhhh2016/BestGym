package com.student.student_base_project.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

public class CityDao {
    private static final String TABLE_NAME = "u_city";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public CityDao(Context context) {

        helper = new DBOpenHelper(context);
    }

    /***
     * 添加
     * @param cityBean
     */
    public void add(CityBean cityBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert into u_city(id,cityname) values (?,?)";
        db.execSQL(sql,new Object[]
                {
                        cityBean.getId(),cityBean.getCityName()
                });
        db.close();
    }


    /***
     * 查询所有
     * @return
     */
    public List<CityBean> queryUserList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CityBean> userBeanList = new ArrayList<>();
        String sql = "select * from u_city";
        Cursor cursor = db.rawQuery(sql, new String[]{
        });
        while(cursor.moveToNext()){
            userBeanList.add(new CityBean(cursor.getLong(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("cityname"))
                    )
            );
        }
        return userBeanList;
    }

    /***
     * 根据插入时间删除联系人
     * @param id
     */
    public void deleteUserData(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("u_city", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    /***
     * 清空数据
     */
    public void deleLexcalData() {
        SQLiteDatabase database = helper.getWritableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

}
