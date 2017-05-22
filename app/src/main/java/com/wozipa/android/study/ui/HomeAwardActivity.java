package com.wozipa.android.study.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.wozipa.android.study.R;

public class HomeAwardActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_award);

        ImageView imageView1= (ImageView) findViewById(R.id.award_head_clock);
        imageView1.setImageResource(R.drawable.clock);

        ImageView imageView2= (ImageView) findViewById(R.id.award_head_gift);
        imageView2.setImageResource(R.drawable.gift);

        ImageView imageView3= (ImageView) findViewById(R.id.award_head_notify);
        imageView3.setImageResource(R.drawable.notify1);

        ImageView imageView4= (ImageView) findViewById(R.id.award_exchange_img);
        imageView4.setImageResource(R.drawable.flag );

        ImageView imageView5= (ImageView) findViewById(R.id.award_delete_img);
        imageView5.setImageResource(R.drawable.remove);

        Button button1=(Button)findViewById(R.id.home_award_2_act);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeActActivity.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.home_award_2_punish);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomePunishActivity.class);
                startActivity(intent);
            }
        });
    }
}

