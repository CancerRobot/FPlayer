package com.wozipa.android.study.ui.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Wozipa on 2017/5/23.
 */

public class AlarmReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("活动时间到");
        String msg = intent.getStringExtra("Message");
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
