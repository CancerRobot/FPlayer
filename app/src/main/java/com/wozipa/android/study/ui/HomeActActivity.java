package com.wozipa.android.study.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wozipa.android.study.R;
import com.wozipa.android.study.controller.ActionController;
import com.wozipa.android.study.controller.UserController;
import com.wozipa.android.study.model.Action;
import com.wozipa.android.study.model.User;
import com.wozipa.android.study.service.MusicService;
import com.wozipa.android.study.ui.id.ActivityIds;
import com.wozipa.android.study.ui.util.TimeThread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeActActivity extends AppCompatActivity {

    private ListView listView = null;
    private List<Action> actionList=new ArrayList<Action>();
    private List<User> userList=new ArrayList<User>();
    private List<Map<String,Object>> rowList=new ArrayList<Map<String,Object>>();
    private ActionsAdapater adapater=null;
    private Map<Integer,Boolean> checkBoxState=new HashMap<>();

    private ActionController controller=new ActionController();
    private UserController userController = new UserController();

    private final int ADD_PUNISH = 100;
    private final int LATEST = 5;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private boolean timing=false;
    private TimeThread thread=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);

        ImageView imageView1= (ImageView) findViewById(R.id.act_head_clock);
        imageView1.setImageResource(R.drawable.clock);

        ImageView imageView2= (ImageView) findViewById(R.id.act_head_gift);
        imageView2.setImageResource(R.drawable.gift);

        ImageView imageView3= (ImageView) findViewById(R.id.act_head_notify);
        imageView3.setImageResource(R.drawable.notify1);

        ImageView imageView4= (ImageView) findViewById(R.id.act_clock_img);
        imageView4.setImageResource(R.drawable.time2);

        ImageView imageView5= (ImageView) findViewById(R.id.act_create_img);
        imageView5.setImageResource(R.drawable.createpage);

        ImageView imageView6= (ImageView) findViewById(R.id.act_BE_img);
        imageView6.setImageResource(R.drawable.time1);

        listView = (ListView) findViewById(R.id.actionList);
        adapater=new ActionsAdapater();
        listView.setAdapter(adapater);
        initActions();

        Button button1 = (Button) findViewById(R.id.home_act_2_award);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeAwardActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.home_act_2_punish);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomePunishActivity.class);
                startActivity(intent);
            }
        });

        Button createBtn = (Button) findViewById(R.id.home_act_create);
        createBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditAct.class);
                startActivityForResult(intent, ActivityIds.CREATE_ACT);
            }
        });

        final Chronometer chronometer= (Chronometer) findViewById(R.id.chronometer1);
        final Button timeBtn = (Button) findViewById(R.id.home_act_time);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                stopService(intent);
                if(checkBoxState.size() == 1)
                {
                    Set<Integer> keySet=checkBoxState.keySet();
                    for(Integer position:keySet) {
                        Action action=(Action) adapater.getItem(position);
                        String[] splitStart = action.getStart().split(":");
                        String[] splitEnd = action.getEnd().split(":");
                        int duration = (Integer.parseInt(splitEnd[0]) - Integer.parseInt(splitStart[0])) * 60
                                + (Integer.parseInt(splitEnd[1]) - Integer.parseInt(splitStart[1]) );
                        System.out.println(duration);

                        User[] users = userController.listUndone();
                        for(User user:users)
                        {
                            userList.add(user);
                        }
                        User u=userList.get(0);
                        int award = u.getAward();
                        int punish = u.getPunish();

                        if (timing)
                        {
                            chronometer.stop();
                            String[] split = chronometer.getText().toString().split(":");
                            int min = Integer.parseInt(split[0]);
                            System.out.println(min);

                            if(min < duration)
                            {
                                System.out.println("活动时间不足");
                                User user = new User(award+min/duration*action.getRecord(),punish+ADD_PUNISH);
                                user.setId(1);
                                userController.edit(user);
                                new AlertDialog.Builder(HomeActActivity.this).setTitle("活动时间不足").setMessage("发放奖励点和惩罚点").show();
                            }
                            else
                            {
                                System.out.println("发放奖励点");
                                User user = new User(award+action.getRecord(),punish);
                                user.setId(1);
                                userController.edit(user);
                                new AlertDialog.Builder(HomeActActivity.this).setTitle("活动完成").setMessage("发放奖励点").show();
                            }
                            char[] time = "00:00".toCharArray();
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            chronometer.setText(time, 0, time.length);

                            timing = false;
                        }
                        else
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                            String currentTime = sdf.format(new java.util.Date());
                            String[] splitCurr = currentTime.split(":");
                            int late = ( Integer.parseInt(splitCurr[0]) - Integer.parseInt(splitStart[0]) ) * 60
                                    + ( Integer.parseInt(splitCurr[1]) - Integer.parseInt(splitStart[1]) );
                            if(late < 0)
                            {
                                System.out.println("活动为开始");
                                new AlertDialog.Builder(HomeActActivity.this).setMessage("活动未开始").show();
                            }
                            else if(late > LATEST)
                            {
                                System.out.println("活动迟到");
                                User user = new User(award,punish+ADD_PUNISH);
                                user.setId(1);
                                userController.edit(user);
                                new AlertDialog.Builder(HomeActActivity.this).setTitle("迟到").setMessage("已发放惩罚点").show();
                            }
                            else
                            {
                                System.out.println("活动开始");
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                chronometer.start();
                                timing = true;
                                new AlertDialog.Builder(HomeActActivity.this).setTitle("活动开始").setMessage("开始计时").show();
                            }
                        }
                    }
                }
                else
                {
                    new AlertDialog.Builder(HomeActActivity.this).setMessage("请选择一项活动").show();
                }

            }
        });

        //add click listern to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle b=data.getExtras();
        if (resultCode == ActivityIds.CREATE_ACT) {
            Action action=new Action();
            action.setId(b.getInt(EditAct.ACTION_ID));
            action.setName(b.getString(EditAct.ACTION_NAME));
            action.setContent(b.getString(EditAct.ACTION_CONTENT));
            action.setStart(b.getString(EditAct.ACTION_START));
            action.setEnd(b.getString(EditAct.ACTION_END));
            action.setRecord(b.getInt(EditAct.ACTION_RECORD));
            actionList.add(action);
            adapater.notifyDataSetChanged();
        }
        else if(resultCode==ActivityIds.CREATE_AWARD)
        {

        }
    }

    public void addAction2List(Action action) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public com.google.android.gms.appindexing.Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("HomeAct Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new com.google.android.gms.appindexing.Action.Builder(com.google.android.gms.appindexing.Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(com.google.android.gms.appindexing.Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void initActions()
    {
        Action[] actions=controller.listUndone();
        if(actions!=null && actions.length>=1)
        {
            for(Action action:actions)
            {
                actionList.add(action);
//            HashMap row=new HashMap<String,Object>();
//            row.put(R.id.action_id,action.getId());
//            row.put(R.id.action_name,action.getName());
//            row.put(R.id.action_content,action.getContent());
//            row.put(R.id.action_time,action.getEnd());
//            rowList.add(row);
            }
            adapater.notifyDataSetChanged();
        }
        else
        {
            System.out.println("the action is null or empty");
        }
    }


    class ActionsAdapater extends BaseAdapter
    {
        @Override
        public int getCount() {
            return actionList.size();
        }

        @Override
        public Object getItem(int position) {
            return actionList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            //布局不变，数据变
            //如果缓存为空，我们生成新的布局作为1个item
            if(convertView==null){
                LayoutInflater inflater = HomeActActivity.this.getLayoutInflater();
                //因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.activity_action_list_item, null);
            }else{
                view = convertView;
            }
            Action m = actionList.get(position);
            System.out.println(m.toString());

            CheckBox sbx = (CheckBox) view.findViewById(R.id.act_select);
            sbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        checkBoxState.put(position,true);
                    }
                    else
                    {
                        checkBoxState.remove(position);
                    }
                }
            });

            TextView idTv = (TextView)view.findViewById(R.id.action_id);
            char[] idCs=Integer.toString(m.getId()).toCharArray();
            idTv.setText(idCs,0,idCs.length);
            idTv.setTextSize(20);

            TextView nameTv = (TextView)view.findViewById(R.id.action_name);
            char[] nameCs=m.getName().toCharArray();
            nameTv.setText(nameCs,0,nameCs.length);
            nameTv.setTextSize(20);

            TextView recordTv = (TextView)view.findViewById(R.id.action_record);
            char[] recordCs=Integer.toString(m.getRecord()).toCharArray();
            recordTv.setText(recordCs,0,recordCs.length);
            recordTv.setTextSize(20);

            TextView startTv= (TextView) view.findViewById(R.id.action_time);
            char[] startCs=m.getStart().toCharArray();
            startTv.setText(startCs,0,startCs.length);
            startTv.setTextSize(20);

            return view;
        }
    }
}





