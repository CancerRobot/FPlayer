package com.wozipa.android.study.ui.util;

import android.widget.Chronometer;

/**
 * Created by xinglinjie on 2017/6/1.
 */

public class TimeThread extends Thread{

    private int min;
    private int sec;
    private volatile  boolean work=true;
    private Chronometer chronometer=null;

    public TimeThread(Chronometer chronometer)
    {
        this.chronometer=chronometer;
    }


    @Override
    public void run() {
        if(work)
        {
            if(sec==59){
                min++;
                sec=0;
            }
            else {
                sec++;
            }
            char[] times=(min+":"+sec).toCharArray();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown()
    {
        this.work=false;
    }
}
