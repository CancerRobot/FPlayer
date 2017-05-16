package com.wozipa.android.study.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.wozipa.android.study.R;

public class HomeActActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);

        Button button1= (Button) findViewById(R.id.home_act_2_award);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeAwardActivity.class);
                startActivity(intent);
            }
        });

        Button button2= (Button) findViewById(R.id.home_act_2_punish);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomePunishActivity.class);
                startActivity(intent);
            }
        });

        Button button3=(Button)findViewById(R.id.home_act_create);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),EditAct.class);
                startActivity(intent);
            }
        });
    }
}

