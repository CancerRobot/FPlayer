package com.wozipa.android.study.ui.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wozipa.android.study.service.MusicService;

/**
 * Created by Wozipa on 2017/5/23.
 */

public class AlarmReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("活动时间到");

        Toast.makeText(context,"活动时间到，请开始活动计时",Toast.LENGTH_LONG).show();
        intent = new Intent(context, MusicService.class);

        context.stopService(intent);
        context.startService(intent);
    }
}
