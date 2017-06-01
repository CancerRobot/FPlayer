package com.wozipa.android.study.controller;

import com.wozipa.android.study.model.User;
import com.wozipa.android.study.service.UserService;

import org.apache.log4j.Logger;

/**
 * Created by xinglinjie on 2017/6/1.
 */

public class UserController {
    private static final Logger LOGGER=Logger.getLogger(UserController.class);

    private UserService service=null;

    public UserController(){ service = new UserService(); }

    public User create(int award,int punish)
    {
        User user=new User(award,punish);
        int id=service.create(user);
        user.setId(id);
        return user;
    }

    public boolean delete(User user){
        try {
            service.delete(user);
            return true;
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void edit(User user){
        service.edit(user);
    }

    public User[] listUndone(){
        return service.list();
    }
}
