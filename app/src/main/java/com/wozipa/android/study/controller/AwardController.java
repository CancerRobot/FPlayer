package com.wozipa.android.study.controller;

import com.wozipa.android.study.model.Award;
import com.wozipa.android.study.service.AwardService;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/5/23.
 */

public class AwardController {

    private static final Logger LOGGER=Logger.getLogger(AwardController.class);

    private AwardService service=null;

    public AwardController(){ service=new AwardService(); }

    public Award create(String name, String cost, String content){
        Award award=new Award(name,Integer.parseInt(cost),content);
        int id=service.create(award);
        award.setId(id);
        return award;
    }

    public boolean delete(Award award){
        try {
            service.delete(award);
            return true;
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void edit(Award award){
        service.edit(award);
    }

    public Award[] listUndone(){
        return service.list();
    }

}
