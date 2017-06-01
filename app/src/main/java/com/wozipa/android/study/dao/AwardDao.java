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
        StringBuffer stringBuffer=new StringBuffer("insert into ").append(TABLE_NAME).append("('name','cost','content') values('")
                .append(award.getName()).append("',")
                .append(award.getCost()).append(",'")
                .append(award.getContent()).append("')");
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
        Cursor cursor=database.rawQuery("select LAST_INSERT_ROWID()",null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void edit(int id,String name,String content,int cost){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("update ").append(TABLE_NAME)
                .append(" set name='").append(name)
                .append("',content='").append(content)
                .append("',cost=").append(cost)
                .append(" where id=").append(id);
        System.out.println(stringBuffer.toString());
        database.execSQL(stringBuffer.toString());
    }

    public Cursor list(){
        String sql="select * from awards";
        Cursor cursor=database.rawQuery(sql,null);
        return cursor;
    }

    /**
     * @author wozipa
     * @date  2017-5-29
     * delete on award accound the id
     * @param id
     */
    public void delete(int id)
    {
        StringBuffer sb=new StringBuffer();
        sb.append("delete from ").append(TABLE_NAME).append(" where id = ").append(id);
        System.out.println(id);
        database.execSQL(sb.toString());
    }
}
