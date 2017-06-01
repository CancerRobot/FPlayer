package com.wozipa.android.study.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wozipa.android.study.database.MySqlLite;
import com.wozipa.android.study.model.User;

import org.apache.log4j.Logger;

/**
 * Created by xinglinjie on 2017/6/1.
 */

public class UserDao {
    private static final Logger LOGGER=Logger.getLogger(ActionDao.class);

    private static final String TABLE_NAME="user";

    SQLiteDatabase database=null;

    public UserDao()
    {
        MySqlLite sqlLite=MySqlLite.GetSqlLite();
        if(null==sqlLite)
        {
            System.out.println("the sql is null");
        }
        database=sqlLite.getWritableDatabase();
    }

    public int create(User user)
    {
        StringBuffer stringBuffer=new StringBuffer("insert to").append(TABLE_NAME)
                .append("('id','award','punish') value(")
                .append(user.getId()).append(",")
                .append(user.getAward()).append(",")
                .append(user.getPunish()).append(")");
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
        Cursor cursor=database.rawQuery("select LAST_INSERT_ROWID()",null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void edit(int id,int award,int punish)
    {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("update ").append(TABLE_NAME)
                .append(" set award=").append(award)
                .append(",punish=").append(punish)
                .append("where id=").append(id);
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
    }

    public void delete(int id)
    {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("delete from ").append(TABLE_NAME)
                .append(" where id=").append(id);
        System.out.println(id);
        database.execSQL(stringBuffer.toString());
    }

    public Cursor list()
    {
        String sql="select * form user";
        Cursor cursor=database.rawQuery(sql,null);
        return cursor;
    }
}
