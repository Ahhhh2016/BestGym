package com.student.student_base_project.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.CityBean;
import com.student.student_base_project.bean.SubscribeBean;

import java.util.ArrayList;
import java.util.List;

public class SubscribeDao {
    private static final String TABLE_NAME = "u_subscribe";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public SubscribeDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    /***
     * 添加
     * @param subscribeBean
     */
    public void add(SubscribeBean subscribeBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert into u_subscribe(id,typeId,type,time,price,cover,date,remark,payType) values (?,?,?,?,?,?,?,?,?)";
        db.execSQL(sql, new Object[]
                {
                        subscribeBean.getId(), subscribeBean.getTypeId(), subscribeBean.getType(),
                        subscribeBean.getTime(), subscribeBean.getPrice(),
                        subscribeBean.getCover(), subscribeBean.getDate(), subscribeBean.getRemark(),
                        subscribeBean.getPayType()
                });
        db.close();
    }

    /***
     * 查询所有
     * @return
     */
    public List<SubscribeBean> querySubscribeList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<SubscribeBean> userBeanList = new ArrayList<>();
        String sql = "select * from u_subscribe";
        Cursor cursor = db.rawQuery(sql, new String[]{
        });
        while (cursor.moveToNext()) {
            userBeanList.add(new SubscribeBean(cursor.getLong(cursor.getColumnIndex("id")),
                            cursor.getLong(cursor.getColumnIndex("typeId")),
                            cursor.getString(cursor.getColumnIndex("type")),
                            cursor.getString(cursor.getColumnIndex("time")),
                            cursor.getString(cursor.getColumnIndex("price")),
                            cursor.getInt(cursor.getColumnIndex("cover")),
                            cursor.getString(cursor.getColumnIndex("date")),
                            cursor.getString(cursor.getColumnIndex("remark")),
                            cursor.getInt(cursor.getColumnIndex("payType"))
                    )
            );
        }
        return userBeanList;
    }

    /***
     * 根据id删除数据
     * @param id
     */
    public void delteSubscribeById(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("u_subscribe", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    /****
     * 判断是否预约过
     * @param _id
     * @return
     */
    public boolean isSubscribe(long _id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"typeId"}, "typeId=?", new String[]{String.valueOf(_id)}, null, null, null);
        while (cursor.moveToNext()) {
            Long typeId = cursor.getLong(cursor.getColumnIndex("typeId"));
            if (_id == typeId) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
