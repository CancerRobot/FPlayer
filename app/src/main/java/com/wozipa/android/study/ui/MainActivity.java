package com.wozipa.android.study.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wozipa.android.study.R;
import com.wozipa.android.study.controller.UserController;
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

//        Button timeBtn=findViewById(R.id.)

        MySqlLite.initlie(MainActivity.this,"fplayer",null,1);
        SQLiteDatabase db=MySqlLite.GetSqlLite().getWritableDatabase();
        if(db==null)
        {
            System.out.println("the database is null");
        }


//        String createUserSql="create table user(id integer primary key autoincrement,award integer,punish integer)";
//        db.execSQL(createUserSql);
//        String createAwardsSql="create table awards(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
//        db.execSQL(createAwardsSql);
//        String createPunishSql="create table punishes(id integer primary key autoincrement,name varchar(20),cost integer,content text)";
//        db.execSQL(createPunishSql);
        int num=0;
        Cursor cursor=db.rawQuery("select * from user",null);
        num = cursor.getCount();
        if(0 == num)
        {
            UserController userController=new UserController();
            userController.create("0","0");
            System.out.println("初始化用户");
        }
        System.out.println("初始化数据库");


    }
}
