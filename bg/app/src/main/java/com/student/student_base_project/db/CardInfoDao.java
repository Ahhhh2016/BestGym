package com.student.student_base_project.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.student.student_base_project.bean.CardInfoBean;
import com.student.student_base_project.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

public class CardInfoDao {
    private static final String TABLE_NAME = "u_card_info";
    private DBOpenHelper helper;

    //写入 ，不然会是出错，是空指针
    public CardInfoDao(Context context) {

        helper = new DBOpenHelper(context);
    }

    /***
     * 添加
     * @param cardInfoBean
     */
    public void add(CardInfoBean cardInfoBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Insert into u_card_info(id,cardType,type,price,cover,cishu,description,payType) values (?,?,?,?,?,?,?,?)";
        db.execSQL(sql, new Object[]
                {
                        cardInfoBean.getId(), cardInfoBean.getCardType(), cardInfoBean.getType(), cardInfoBean.getPrice(),
                        cardInfoBean.getCover(), cardInfoBean.getCishu(), cardInfoBean.getDescription(), cardInfoBean.getPayType()
                });
        db.close();
    }

    /***
     * 查询所有
     * @return
     */
    public List<CardInfoBean> queryCardInfoList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CardInfoBean> userBeanList = new ArrayList<>();
        String sql = "select * from u_card_info";
        Cursor cursor = db.rawQuery(sql, new String[]{
        });
        while (cursor.moveToNext()) {
            userBeanList.add(new CardInfoBean(cursor.getLong(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("cardType")),
                            cursor.getString(cursor.getColumnIndex("type")),
                            cursor.getString(cursor.getColumnIndex("price")),
                            cursor.getInt(cursor.getColumnIndex("cover")),
                            cursor.getString(cursor.getColumnIndex("cishu")),
                            cursor.getString(cursor.getColumnIndex("description")),
                            cursor.getInt(cursor.getColumnIndex("payType"))
                    )
            );
        }
        return userBeanList;
    }
}
