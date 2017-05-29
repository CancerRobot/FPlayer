package com.wozipa.android.study.controller;

import com.wozipa.android.study.model.Award;
import com.wozipa.android.study.model.Punish;
import com.wozipa.android.study.service.PunishService;

import org.apache.log4j.Logger;

/**
 * Created by xinglinjie on 2017/5/26.
 */

public class PunishController {

    private static final Logger LOGGER=Logger.getLogger(PunishController.class);

    private PunishService service=null;

    public PunishController(){ service=new PunishService(); }

    public Punish create(String name, String cost, String content){
        Punish punish=new Punish(name,Integer.parseInt(cost),content);
        int id=service.create(punish);
        punish.setId(id);
        return punish;
    }

    public void delete(Punish punish){
        service.delete(punish);
    }

    public void edit(Punish punish){
        service.edit(punish);
    }

    public Punish[] listUndone(){
        return service.list();
    }
}
