package com.wozipa.android.study.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wozipa.android.study.R;

public class EditAward extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_award);

        Button button1=(Button)findViewById(R.id.edit_award_finish);

        Button button2=(Button)findViewById(R.id.edit_award_exit);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeAwardActivity.class);
                startActivity(intent);
            }
        });
    }
}
