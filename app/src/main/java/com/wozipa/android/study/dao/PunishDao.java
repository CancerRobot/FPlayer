package com.wozipa.android.study.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wozipa.android.study.database.MySqlLite;
import com.wozipa.android.study.model.Punish;

import org.apache.log4j.Logger;

/**
 * Created by xinglinjie on 2017/5/26.
 */

public class PunishDao {

    private static final Logger LOGGER=Logger.getLogger(PunishDao.class);

    public static final String TABLE_NAME="punishs";

    SQLiteDatabase database=null;

    public PunishDao(){ database= MySqlLite.GetSqlLite().getWritableDatabase(); }

    public int create(Punish punish){
        StringBuffer stringBuffer=new StringBuffer("insert into ").append(TABLE_NAME).append("('name','cost','content') values('")
                .append(punish.getName()).append("',")
                .append(punish.getCost()).append(",'")
                .append(punish.getContent()).append("')");
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
        Cursor cursor=database.rawQuery("select LAST_INSERT_ROWID()",null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void edit(int id,String name,String content,int cost){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("update ").append(TABLE_NAME).append(" set name='").append(name).append("',content='").append(content).append("',cost=").append(cost)
                .append(" where id=").append(id);
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
    }

    public Cursor list(){
        String sql="select * from punishs";
        Cursor cursor=database.rawQuery(sql,null);
        return cursor;
    }

    public void delete(int id)
    {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("delete from ").append(TABLE_NAME).append(" where id =").append(id);
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
    }
}
