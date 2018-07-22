package com.lss.demo_08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 李珊珊 on 2018/7/15.
 */
public class OpenSqlite extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DB_NAME = "data.db";

    public OpenSqlite(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean bol = false;
        db.execSQL("create table goods (code integer primary key autoincrement,name varchar(10),number int(10),price int (10))");
        bol = true;
        Log.i("数据库状态：","create"+bol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
