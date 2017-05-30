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
import com.wozipa.android.study.model.Award;
import com.wozipa.android.study.ui.id.ActivityIds;
import com.wozipa.android.study.util.Utils;
import com.wozipa.android.study.controller.AwardController;

import org.apache.log4j.Logger;

public class EditAward extends AppCompatActivity {

    private static final Logger LOGGER=Logger.getLogger(EditAward.class);

    public static final String AWARD_ID="award_id";
    public static final String AWARD_NAME="award_name";
    public static final String AWARD_COST="award_cost";
    public static final String AWARD_CONTENT="award_content";

    private GoogleApiClient client;
    private AwardController awardController=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_award);
        System.out.println("start to edit the award page");
        //get the data from the award home page
        awardController=new AwardController();

        final EditText award_name=(EditText)findViewById(R.id.award_name);
        final EditText award_cost=(EditText)findViewById(R.id.award_cost);
        final EditText award_content=(EditText)findViewById(R.id.award_content);
        Bundle bundle=this.getIntent().getExtras();
        final String mode=bundle.getString(HomeAwardActivity.INTENT_MODE);
        if(bundle!=null && !bundle.isEmpty())
        {
            char[] name=bundle.getString(AWARD_NAME).toCharArray();
            award_name.setText(name,0,name.length);
            char[] cost=String.valueOf(bundle.getInt(AWARD_COST)).toCharArray();
            award_cost.setText(cost,0,cost.length);
            char[] content=bundle.getString(AWARD_CONTENT).toCharArray();
            award_content.setText(content,0,content.length);
        }
        Button finishBtn=(Button)findViewById(R.id.edit_award_finish);
        //add the click listern to button
        finishBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name=award_name.getText().toString();
                String cost=award_cost.getText().toString();
                String content=award_content.getText().toString();
                System.out.println("button click");
                boolean flag= Utils.checkStrings(name,cost,content);
                System.out.println(flag);
                if(!flag){
                    new AlertDialog.Builder(EditAward.this).setTitle("错误").setMessage("请将参数填写完整").show();
                }
                Award award=null;
                if(mode.equals(HomeAwardActivity.CREATE_MODE))
                {
                    award=awardController.create(name,cost,content);
                }
                else
                {
                    int id=getIntent().getExtras().getInt(AWARD_ID);
                    award=new Award(name,Integer.parseInt(cost),content);
                    award.setId(id);
                    awardController.edit(award);
                }
                Intent intent=getIntent();
                intent.putExtra(AWARD_ID,award.getId());
                intent.putExtra(AWARD_NAME,award.getName());
                intent.putExtra(AWARD_COST,award.getCost());
                intent.putExtra(AWARD_CONTENT,award.getContent());
                setResult(ActivityIds.AWRAD_CREATW,intent);
                EditAward.this.finish();
            }
        });

        Button button2=(Button)findViewById(R.id.edit_award_exit);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeAwardActivity.class);
                startActivity(intent);
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//    public Award getIndexApiAction() {
//
//        Thing object = new Thing.Builder()
//                .setName("EditAct Page")
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Award.Builder(Award.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Award.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
        awardController=new AwardController();
    }
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
