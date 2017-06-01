package com.wozipa.android.study.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wozipa.android.study.database.MySqlLite;
import com.wozipa.android.study.model.Action;

import org.apache.log4j.Logger;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class ActionDao {

    private static final Logger LOGGER=Logger.getLogger(ActionDao.class);

    private static final String TABLE_NAME="actions";

    SQLiteDatabase database=null;

    public ActionDao()
    {
        MySqlLite sqlLite=MySqlLite.GetSqlLite();
        if(sqlLite==null)
        {
            System.out.println("the sql lite is null");
        }
        database=sqlLite.getWritableDatabase();
    }


    public int create(Action action)
    {
        StringBuffer stringBuffer=new StringBuffer("insert into ").append(TABLE_NAME).append("('name','content','start','end','record') values('")
                .append(action.getName()).append("','")
                .append(action.getContent()).append("','")
                .append(action.getStart()).append("','")
                .append(action.getEnd()).append("',")
                .append(action.getRecord()).append(")");
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
        Cursor cursor=database.rawQuery("select LAST_INSERT_ROWID()",null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void edit(Action action)
    {
        StringBuffer stringBuffer=new StringBuffer();
        database.execSQL(stringBuffer.toString());
    }

    public Cursor list()
    {
        String sql="select * from actions";
        Cursor cursor=database.rawQuery(sql,null);
        return cursor;
    }
}
