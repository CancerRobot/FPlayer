package com.wozipa.android.study.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.wozipa.android.study.util.Utils;

import org.apache.log4j.chainsaw.Main;

public class EditAct extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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
               new DatePickerDialog(EditAct.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       StringBuilder sb=new StringBuilder();
                       sb.append(year).append("-").append(month).append("-").append(dayOfMonth);
                       char[] result=sb.toString().toCharArray();
                       startEt.setText(result,0,result.length);
                   }
               },2017,1,1).show();
            }
        });

//        endEt.setClickable(true);
        endEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StringBuilder sb=new StringBuilder();
                        sb.append(year).append("-").append(month).append("-").append(dayOfMonth);
                        char[] result=sb.toString().toCharArray();
                        endEt.setText(result,0,result.length);
                    }
                },2017,1,1).show();
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
                String record=endEt.getText().toString();
                System.out.println("button click");
                boolean flag=Utils.checkStrings(name,content,start,end,record);
                System.out.println(flag);
                if(!flag)
                {
                    new AlertDialog.Builder(EditAct.this).setTitle("错误").setMessage("请将参数填写完整").show();
                }
                //
                actionController.create(name,content,start,end,record);
            }
        });

        Button button2 = (Button) findViewById(R.id.edit_act_delete);

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
