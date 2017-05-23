package com.wozipa.android.study.service;

import android.database.Cursor;

import com.wozipa.android.study.dao.AwardDao;
import com.wozipa.android.study.model.Award;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class AwardService {
    private static final Logger LOGGER=Logger.getLogger(AwardService.class);

    private AwardDao dao=new AwardDao();

    public int create(Award award){
        int id=dao.create(award);
        return id;
    }

    public void delete(){

    }

    public void edit(){

    }

    public Award[] list(){
        List<Award> list=new ArrayList<>();
        Cursor cursor=dao.list();
        while(cursor.moveToNext()){
            Award award=new Award();
            award.setId(cursor.getInt(0));
            award.setName(cursor.getString(1));
            award.setCost(cursor.getInt(2));
            award.setContent(cursor.getString(3));
            list.add(award);
        }

        return list.toArray(new Award[list.size()]);
    }
}
