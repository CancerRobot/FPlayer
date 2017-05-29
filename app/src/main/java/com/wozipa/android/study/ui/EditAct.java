package com.wozipa.android.study.ui;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wozipa.android.study.R;
import com.wozipa.android.study.controller.ActionController;
import com.wozipa.android.study.ui.id.ActivityIds;
import com.wozipa.android.study.ui.util.DateTimePickDialogUtil;
import com.wozipa.android.study.ui.util.TimePickerDialogUtil;
import com.wozipa.android.study.util.StringUtils;
import com.wozipa.android.study.util.Utils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditAct extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private static final Logger LOGGER=Logger.getLogger(EditAct.class);

    private String initStartDateTime = "2017年1月1日 12:00"; // 初始化开始时间
    private String initEndDateTime = "2017年1月1日 12:00"; // 初始化结束时间

    private String initStartTime = "12:00";
    private String initEndTime = "12:00";

    public static final String ACTION_ID="action_id";
    public static final String ACTION_NAME="action_name";
    public static final String ACTION_CONTENT="action_content";
    public static final String ACTION_START="action_start";
    public static final String ACTION_END="action_end";
    public static final String ACTION_RECORD="action_record";

    private GoogleApiClient client;
    private ActionController actionController=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_act);
        System.out.println("start to make the action page");
        final EditText nameEt = (EditText) findViewById(R.id.nameET);
        final EditText contentEt = (EditText) findViewById(R.id.contentET);
        final EditText startEt = (EditText) findViewById(R.id.startET);
        final EditText endEt = (EditText) findViewById(R.id.endET);
        final EditText recoredEt = (EditText) findViewById(R.id.recordET);


//        startEt.setClickable(true);
        startEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("the start edit text is clicked");
//                TimePickerDialogUtil timePickerDialog = new TimePickerDialogUtil(
//                        EditAct.this, initStartTime);
//                timePickerDialog.timePicKDialog(startEt);
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        EditAct.this, initStartDateTime);
                dateTimePicKDialog.dateTimePicKDialog(startEt);
            }
        });

//        endEt.setClickable(true);
        endEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("the start edit text is clicked");
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        EditAct.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(endEt);
            }
        });

        Button finshBtn = (Button) findViewById(R.id.edit_act_finish);
        finshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEt.getText().toString();
                String content=contentEt.getText().toString();
                String start=startEt.getText().toString();
                String end=endEt.getText().toString();
                String record=recoredEt.getText().toString();
                System.out.println("button click");
                boolean flag=Utils.checkStrings(name,content,start,end,record);
                System.out.println(flag);
                if(!flag)
                {
                    new AlertDialog.Builder(EditAct.this).setTitle("错误").setMessage("请将参数填写完整").show();
                }
                //
                com.wozipa.android.study.model.Action action=actionController.create(name,content,start,end,record);
                //set one alarm for start
                AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date date=sdf.parse(action.getStart());
                    Intent alarmIntent=new Intent();
                    alarmIntent.setAction("com.Android.AlarmManager.action.BACK_ACTION");
                    alarmIntent.putExtra("Message", "任务"+action.getName()+"开始进行计时");
                    alarmManager.set(AlarmManager.RTC,date.getTime(), PendingIntent.getBroadcast(EditAct.this, 0,alarmIntent,0));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //
                Intent intent=getIntent();
                intent.putExtra(ACTION_ID,action.getId());
                intent.putExtra(ACTION_NAME,action.getName());
                intent.putExtra(ACTION_CONTENT,action.getContent());
                intent.putExtra(ACTION_START,action.getStart());
                intent.putExtra(ACTION_END,action.getEnd());
                intent.putExtra(ACTION_RECORD,action.getRecord());
                setResult(ActivityIds.AWRAD_CREATW,intent);
                EditAct.this.finish();
            }
        });

        Button button2 = (Button) findViewById(R.id.edit_act_delete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEt.setText("".toCharArray(),0,0);
                contentEt.setText("".toCharArray(),0,0);
                startEt.setText("".toCharArray(),0,0);
                endEt.setText("".toCharArray(),0,0);
                recoredEt.setText("".toCharArray(),0,0);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {

        Thing object = new Thing.Builder()
                .setName("EditAct Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
        actionController=new ActionController();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
