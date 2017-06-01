package com.wozipa.android.study.service;

import android.database.Cursor;

import com.wozipa.android.study.dao.UserDao;
import com.wozipa.android.study.model.User;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinglinjie on 2017/6/1.
 */

public class UserService {
    private static final Logger LOGGER=Logger.getLogger(UserService.class);

    private UserDao dao=new UserDao();

    public int create(User user)
    {
        int id=dao.create(user);
        System.out.println("the id is "+id);
        return id;
    }

    public void delete(User user)
    {
        int id=user.getId();
        if(id<=0)
        {
            throw new NullPointerException("the id value is null ");
        }
        dao.delete(id);
    }

    public void edit(User user)
    {
        dao.edit(user.getId(),user.getAward(),user.getPunish());
    }

    public User[] list()
    {
        List<User> list=new ArrayList<>();
        Cursor cursor=dao.list();
        while(cursor.moveToNext())
        {
            User user=new User();
            user.setId(cursor.getInt(0));
            user.setAward(cursor.getInt(1));
            user.setPunish(cursor.getInt(2));
            list.add(user);
        }
        return list.toArray(new User[list.size()]);
    }
}
