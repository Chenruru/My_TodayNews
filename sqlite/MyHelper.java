package test.bwie.com.my_todaynews.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 1.类的用途:
 * 2.@author:吝雪亮
 * 3.@date:2017/4/18.
 */

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "tests.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table channel(_id integer primary key autoincrement,name text , url text , style text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
