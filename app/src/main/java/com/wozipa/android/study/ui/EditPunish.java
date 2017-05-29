package com.wozipa.android.study.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wozipa.android.study.R;
import com.wozipa.android.study.controller.PunishController;
import com.wozipa.android.study.model.Award;
import com.wozipa.android.study.model.Punish;
import com.wozipa.android.study.ui.id.ActivityIds;
import com.wozipa.android.study.util.Utils;

public class EditPunish extends AppCompatActivity {

    public static final String PUNISH_ID="punish_id";
    public static final String PUNISH_NAME="punish_name";
    public static final String PUNISH_COST="punish_cost";
    public static final String PUNISH_CONTENT="puinsh_content";

    private GoogleApiClient client;
    private PunishController punishController=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_punish);

        System.out.println("start to edit the punish page");
        punishController=new PunishController();
        final String mode=getIntent().getStringExtra(HomePunishActivity.INTENT_MODE);
        final EditText punish_name=(EditText)findViewById(R.id.punish_name);
        final EditText punish_cost=(EditText)findViewById(R.id.punish_cost);
        final EditText punish_content=(EditText)findViewById(R.id.punish_content);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null && !bundle.isEmpty())
        {
            char[] name=bundle.getString(PUNISH_NAME).toCharArray();
            punish_name.setText(name,0,name.length);
            char[] cost=String.valueOf(bundle.getInt(PUNISH_COST)).toCharArray();
            punish_cost.setText(cost,0,cost.length);
            char[] content=bundle.getString(PUNISH_CONTENT).toCharArray();
            punish_content.setText(content,0,content.length);
        }

        Button finishBtn=(Button)findViewById(R.id.edit_punish_finish);
        finishBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name=punish_name.getText().toString();
                String cost=punish_cost.getText().toString();
                String content=punish_content.getText().toString();
                System.out.println("button click");
                boolean flag= Utils.checkStrings(name,cost,content);
                System.out.println(flag);
                if(!flag){
                    new AlertDialog.Builder(EditPunish.this).setTitle("错误").setMessage("请将参数填写完整").show();
                }
                Punish punish=null;
                if(mode.equals(HomePunishActivity.CREATE_MODE))
                {
                    punish=punishController.create(name,cost,content);
                }
                else if(mode.equals(HomePunishActivity.CHANGE_MODE))
                {
                    System.out.println(cost);
                    int id=getIntent().getExtras().getInt(PUNISH_ID);
                    punish=new Punish(name,Integer.parseInt(cost),content);
                    punish.setId(id);
                    punishController.edit(punish);
                }
                Intent intent=getIntent();
                intent.putExtra(PUNISH_ID,punish.getId());
                intent.putExtra(PUNISH_NAME,punish.getName());
                intent.putExtra(PUNISH_COST,punish.getCost());
                intent.putExtra(PUNISH_CONTENT,punish.getContent());
                setResult(ActivityIds.CREATE_PUNISH,intent);
                EditPunish.this.finish();
            }
        });

        Button button2=(Button)findViewById(R.id.edit_punish_exit);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomePunishActivity.class);
                startActivity(intent);
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//    public Punish getIndexApiAction() {
//
//        Thing object = new Thing.Builder()
//                .setName("EditAct Page")
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Punish.Builder(Punish.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Punish.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//        punishController=new PunishController();
//    }
//
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }
}
