package com.wozipa.android.study.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wozipa.android.study.R;

public class EditAct extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_act);

        Button button1=(Button)findViewById(R.id.edit_act_finish);

        Button button2=(Button)findViewById(R.id.edit_act_delete);

    }
}
