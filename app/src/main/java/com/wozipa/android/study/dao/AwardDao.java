package com.wozipa.android.study.dao;

/**
 * Created by Administrator on 2017/5/23.
 */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wozipa.android.study.database.MySqlLite;
import com.wozipa.android.study.model.Award;

import org.apache.log4j.Logger;

public class AwardDao {
    private static final Logger LOGGER=Logger.getLogger(AwardDao.class);

    private static final String TABLE_NAME="awards";

    SQLiteDatabase database=null;

    public AwardDao(){ database=MySqlLite.GetSqlLite().getWritableDatabase(); }

    public int create(Award award){
        StringBuffer stringBuffer=new StringBuffer("insert into").append(TABLE_NAME).append("('name','cost','content')values('")
                .append(award.getName()).append("','")
                .append(award.getCost()).append("','")
                .append(award.getContent()).append("','");
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
        Cursor cursor=database.rawQuery("select LAST_INSERT_ROWID()",null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void edit(Award award){
        StringBuffer stringBuffer=new StringBuffer();
        database.execSQL(stringBuffer.toString());
    }

    public Cursor list(){
        String sql="select * from awards";
        Cursor cursor=database.rawQuery(sql,null);
        return cursor;
    }
}
