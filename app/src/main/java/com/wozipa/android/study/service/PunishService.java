package com.wozipa.android.study.service;

import android.database.Cursor;

import com.wozipa.android.study.dao.PunishDao;
import com.wozipa.android.study.model.Punish;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinglinjie on 2017/5/26.
 */

public class PunishService {

    private static final Logger LOGGER=Logger.getLogger(PunishService.class);

    private PunishDao dao=new PunishDao();

    public int create(Punish punish){
        int id=dao.create(punish);
        return id;
    }

    public void delete(){

    }

    public void edit(){

    }

    public Punish[] list(){
        List<Punish> list=new ArrayList<>();
        Cursor cursor=dao.list();
        while(cursor.moveToNext()){
            Punish punish=new Punish();
            punish.setId(cursor.getInt(0));
            punish.setName(cursor.getString(1));
            punish.setCost(cursor.getInt(2));
            punish.setContent(cursor.getString(3));
            list.add(punish);
        }

        return list.toArray(new Punish[list.size()]);
    }
}
