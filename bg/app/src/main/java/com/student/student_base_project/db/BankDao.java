package com.student.student_base_project.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.CardBean;
import com.student.student_base_project.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

public class BankDao {
    private static final String TABLE_NAME = "u_bank";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public BankDao(Context context) {

        helper = new DBOpenHelper(context);
    }

    /***
     * 添加
     * @param cardBean
     */
    public void add(CardBean cardBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert into u_bank(id,cardNo,cardPwd) values (?,?,?)";
        db.execSQL(sql, new Object[]
                {
                        cardBean.getId(), cardBean.getCardNo(), cardBean.getCardPwd()
                });
        db.close();
    }


    /***
     * 查询所有
     * @return
     */
    public List<CardBean> queryBankList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CardBean> userBeanList = new ArrayList<>();
        String sql = "select * from u_bank";
        Cursor cursor = db.rawQuery(sql, new String[]{
        });
        while (cursor.moveToNext()) {
            userBeanList.add(new CardBean(cursor.getLong(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("cardNo")),
                            cursor.getString(cursor.getColumnIndex("cardPwd"))
                    )
            );
        }
        return userBeanList;
    }


    /***
     * 删除卡
     * @param id
     */
    public void delteBankListById(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("u_bank", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
