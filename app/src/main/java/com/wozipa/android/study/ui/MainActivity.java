package com.wozipa.android.study.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wozipa.android.study.R;
import com.wozipa.android.study.database.MySqlLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        ImageView imageView= (ImageView) findViewById(R.id.app_icon);
        imageView.setImageResource(R.drawable.icon);

        Button button= (Button) findViewById(R.id.app_index_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomeActActivity.class);
                startActivity(intent);
            }
        });

        MySqlLite.initlie(MainActivity.this,"fplayer",null,1);
        if(MySqlLite.GetSqlLite().getWritableDatabase()==null)
        {
            System.out.println("the database is null");
        }
        SQLiteDatabase db=MySqlLite.GetSqlLite().getWritableDatabase();
//        String createAwardsSql="create table awards(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
//        db.execSQL(createAwardsSql);
//        String createPunishSql="create table punishs(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
//        db.execSQL(createPunishSql);
        System.out.println("初始化数据库");
    }
}
