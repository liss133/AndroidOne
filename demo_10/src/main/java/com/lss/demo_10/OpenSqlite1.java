package com.lss.demo_10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 李珊珊 on 2018/7/18.
 */
public class OpenSqlite1 extends SQLiteOpenHelper {
    private static final int VERSION = 4;
    private static final String DB_NAME = "data.db";
    public OpenSqlite1(Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean bol = false;
        db.execSQL("create table contacter (code integer primary key autoincrement,name varchar(10),number char(30),image char(100),xing varchar(10))");
        bol = true;
        Log.i("数据库状态：","create"+bol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
