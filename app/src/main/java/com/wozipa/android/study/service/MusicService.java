package com.wozipa.android.study.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.wozipa.android.study.R;


/**
 * Created by xinglinjie on 2017/6/2.
 */

public class MusicService extends Service {
    private MediaPlayer player;

    public IBinder onBind(Intent intent){
        return null;
    }

    public void onStart(Intent intent,int startId){
        super.onStart(intent,startId);
        player = MediaPlayer.create(this, R.raw.love_is_true);
        player.setLooping(true);
        player.start();
    }

    public void onDestroy(){
        if(player != null)
        {
            player.stop();
        }
    }
}
