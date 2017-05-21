package com.wozipa.android.study.dao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

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
        database=MySqlLite.GetSqlLite().getWritableDatabase();
    }


    public void create(Action action)
    {
        StringBuffer stringBuffer=new StringBuffer("insert into ").append(TABLE_NAME).append("('name','content','start','end','record' values(")
                .append(action.getName()).append(",")
                .append(action.getContent()).append(",")
                .append(action.getStart()).append(",")
                .append(action.getEnd()).append(",")
                .append(action.getRecord()).append(")");
        database.execSQL(stringBuffer.toString());
    }

    public void edit(Action action)
    {
        StringBuffer stringBuffer=new StringBuffer();
        database.execSQL(stringBuffer.toString());
    }
}
