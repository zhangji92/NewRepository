package com.allactivity.sqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by 张继 on 2016/10/27.
 * SQLite数据库
 */

public class SQLiteDome extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每个程序的数据可都是唯一的(每个程序都有自己的数据库)默认的情况下各自互相不干扰
        //创建一个数据库并且打开
        SQLiteDatabase db=openOrCreateDatabase("user.db",MODE_PRIVATE,null);

//        db.execSQL("CREATE table");

    }
}
