package com.student.student_base_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;//版本
    private static final String DBNAME = "city.db";//数据库名字
    //创建数据库
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户表
        db.execSQL("create table if not exists u_user(id integer primary key,mobile varchar(20),username varchar(20),password varchar(20))");
        //预约记录表
        db.execSQL("create table if not exists u_subscribe(id integer primary key,typeId integer,type varchar(20),time varchar(20),price varchar(20),cover integer,date varchar(20) ,remark varchar(50),payType integer)");
        //购买卡卷记录表
        db.execSQL("create table if not exists u_card_info(id integer primary key,cardType varchar(20),type varchar(20),price varchar(20),cover integer,cishu varchar(20),description varchar(20),payType integer)");
        //Course 列表
        db.execSQL("create table if not exists u_course_list(id integer primary key,type varchar(20),time varchar(20),price varchar(20),status integer,number varchar(20),cover varchar(20))");

        //银行卡号存储
        db.execSQL("create table if not exists u_bank(id integer primary key,cardNo varchar(20),cardPwd varchar(20))");
        db.execSQL("create table if not exists u_city(id integer primary key,cityname varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
