package com.wozipa.android.study.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        System.out.println("create the tables need");
        String createUserSql="create table user(id integer primary key autoincrement,award integer,punish integer)";
        db.execSQL(createUserSql);
        String createActionsSql="create table actions(id integer primary key autoincrement,name varchar(20),content text,start varchar(20),end varchar(20),record integer)";
        db.execSQL(createActionsSql);
        String createAwardsSql="create table awards(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
        db.execSQL(createAwardsSql);
        String createPunishSql="create table punishs(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
        db.execSQL(createPunishSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
