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
        String msg = intent.getStringExtra("任务时间到");
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
