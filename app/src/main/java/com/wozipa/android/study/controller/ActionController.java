package com.wozipa.android.study.controller;

import android.app.Service;

import com.wozipa.android.study.model.Action;
import com.wozipa.android.study.service.ActionService;

import org.apache.log4j.Logger;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class ActionController {

    private static final Logger LOGGER=Logger.getLogger(ActionController.class);

    private ActionService service=null;

    public ActionController()
    {
        service=new ActionService();
    }

    public Action create(String name,String content,String start,String end,String reward)
    {
        Action action=new Action(name,content,start,end,Integer.parseInt(reward));

        int id=service.create(action);
        action.setId(id);
        return action;
    }

    public void delete()
    {

    }

    public void edit(String id,String name,String content,String start,String time,String reward)
    {

    }

    public Action[] listUndone()
    {
        return service.list();
    }


}
