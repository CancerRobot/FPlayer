package com.wozipa.android.study.service;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wozipa.android.study.dao.ActionDao;
import com.wozipa.android.study.model.Action;

import org.apache.log4j.Logger;

import java.io.DataOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class ActionService {

    private static final Logger LOGGER=Logger.getLogger(ActionService.class);

    private ActionDao dao=new ActionDao();

    public int create(Action action)
    {
        int id=dao.create(action);
        return id;
    }

    public void delete()
    {

    }

    public void edit()
    {

    }

    public Action[] list()
    {
        List<Action> list=new ArrayList<>();
        Cursor cursor=dao.list();
        while(cursor.moveToNext())
        {
            Action action=new Action();
            action.setId(cursor.getInt(0));
            action.setName(cursor.getString(1));
            action.setContent(cursor.getString(2));
            action.setStart(cursor.getString(3));
            action.setEnd(cursor.getString(4));
            action.setRecord(cursor.getInt(5));
            list.add(action);
        }
        return list.toArray(new Action[list.size()]);
    }
}
