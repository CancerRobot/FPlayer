package com.wozipa.android.study.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.DngCreator;

import org.apache.log4j.Logger;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class MySqlLite extends SQLiteOpenHelper{

    private static final Logger LOGGER=Logger.getLogger(MySqlLite.class);

    private static volatile MySqlLite SQL_LITE=null;

    public static void initlie(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        SQL_LITE=new MySqlLite(context,name,factory,version);
    }

    public static MySqlLite GetSqlLite()
    {
        return SQL_LITE;
    }

    private MySqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createActionsSql="create table actions(id integer primary key autoincrement,name varchar(20),content text,start varchar(20),end varchar(20),record integer)";
        db.execSQL(createActionsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
