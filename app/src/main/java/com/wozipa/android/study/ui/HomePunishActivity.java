package com.wozipa.android.study.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wozipa.android.study.R;
import com.wozipa.android.study.controller.PunishController;
import com.wozipa.android.study.controller.UserController;
import com.wozipa.android.study.model.Punish;
import com.wozipa.android.study.model.User;
import com.wozipa.android.study.ui.id.ActivityIds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomePunishActivity extends AppCompatActivity  {

    public static final String INTENT_MODE="intent.mode";
    public static final String CREATE_MODE="create";
    public static final String CHANGE_MODE="change";

    private ListView listView=null;
    private List<Punish> punishList=new ArrayList<Punish>();
    private List<User> userList=new ArrayList<User>();
    private List<Map<String,Object>> rowList=new ArrayList<Map<String,Object>>();
    private PunishesAdapter adapter=null;
    private Map<Integer,Boolean> checkBoxState=new HashMap<>();

    private PunishController controller=new PunishController();
    private UserController userController=new UserController();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_punish);

        ImageView imageView1= (ImageView) findViewById(R.id.punish_head_clock);
        imageView1.setImageResource(R.drawable.clock);

        ImageView imageView2= (ImageView) findViewById(R.id.punish_head_gift);
        imageView2.setImageResource(R.drawable.gift);

        ImageView imageView3= (ImageView) findViewById(R.id.punish_head_notify);
        imageView3.setImageResource(R.drawable.notify1);

        ImageView imageView4= (ImageView) findViewById(R.id.punish_exchange_img);
        imageView4.setImageResource(R.drawable.flag );

        ImageView imageView5= (ImageView) findViewById(R.id.punish_delete_img);
        imageView5.setImageResource(R.drawable.remove);

        TextView punishTv=(TextView) findViewById(R.id.user_punish);
        User[] users=userController.listUndone();
        for(User user:users)
        {
            userList.add(user);
        }
        User u=userList.get(0);
        char[] punishCs=Integer.toString(u.getPunish()).toCharArray();
        punishTv.setText(punishCs,0,punishCs.length);

        Button button1=(Button)findViewById(R.id.home_punish_2_act);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeActActivity.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.home_punish_2_award);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),HomeAwardActivity.class);
                intent.putExtra(INTENT_MODE,CREATE_MODE);
                startActivity(intent);
            }
        });

        //get the create button and  add click listener to it
        Button createBtn= (Button) findViewById(R.id.home_punish_create);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EditPunish.class);
//                startActivity(intent);
                intent.putExtra(INTENT_MODE,CREATE_MODE);
                startActivityForResult(intent,ActivityIds.CREATE_PUNISH);
            }
        });

        Button deleteBtn= (Button) findViewById(R.id.home_punish_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> keys=checkBoxState.keySet();
                for(Integer pos:keys)
                {
                    Punish punish= (Punish) adapter.getItem(pos);
                    controller.delete(punish);
                }
                adapter.notifyDataSetChanged();;
            }
        });

        //init the list view and configuration it
        listView= (ListView) findViewById(R.id.home_punish_listview);
        adapter=new PunishesAdapter();
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("click the item");
                Intent intent = new Intent(getApplicationContext(), EditPunish.class);
                Punish punish= (Punish) adapter.getItem(position);
                punishList.remove(punish);
                intent.putExtra(INTENT_MODE,CHANGE_MODE);
                intent.putExtra(EditPunish.PUNISH_ID,punish.getId());
                intent.putExtra(EditPunish.PUNISH_NAME,punish.getName());
                intent.putExtra(EditPunish.PUNISH_CONTENT,punish.getContent());
                intent.putExtra(EditPunish.PUNISH_COST,punish.getCost());
                startActivityForResult(intent, ActivityIds.CREATE_PUNISH);
            }
        });
        Punish[] punishes=controller.listUndone();
        for(Punish punish:punishes)
        {
            punishList.add(punish);
        }
        adapter.notifyDataSetChanged();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("the result is start");
        if(data==null)
        {
            return;
        }
        Bundle b=data.getExtras();

        if (resultCode == ActivityIds.CREATE_PUNISH) {
            Punish punish=new Punish();
            punish.setId(b.getInt(EditPunish.PUNISH_ID));
            punish.setName(b.getString(EditPunish.PUNISH_NAME));
            punish.setCost(b.getInt(EditPunish.PUNISH_COST));
            punish.setContent(b.getString(EditPunish.PUNISH_CONTENT));
            punishList.add(punish);
            adapter.notifyDataSetChanged();
        }
    }

    public void addPunish2List(Punish punish) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public com.google.android.gms.appindexing.Action getIndexApiPunish() {
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
        AppIndex.AppIndexApi.start(client, getIndexApiPunish());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiPunish());
        client.disconnect();
    }

    public void initPunishes()
    {
        Punish[] punishes=controller.listUndone();
        if(punishes!=null && punishes.length>=1)
        {
            for(Punish punish:punishes)
            {
                punishList.add(punish);
//                HashMap row=new HashMap<String,Object>();
//                row.put(R.id.punish_id,punish.getId());
//                row.put(R.id.punish_name,punish.getName());
//                row.put(R.id.punish_cost,punish.getCost());
//                row.put(R.id.punish_content,punish.getContent());
//                rowList.add(row);
            }
            adapter.notifyDataSetChanged();
        }
        else
        {
            System.out.println("the punish is null or empty");
        }
    }

    class PunishesAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return punishList.size();
        }

        @Override
        public Object getItem(int position) {
            return punishList.get(position);
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
                LayoutInflater inflater = HomePunishActivity.this.getLayoutInflater();
                //因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.activity_punish_list_item, null);
            }else{
                view = convertView;
            }
            Punish m = punishList.get(position);
            System.out.println(m.toString());

            CheckBox cbx = (CheckBox)view.findViewById(R.id.punish_checkBox);
            cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

            TextView idTv = (TextView)view.findViewById(R.id.punish_id);
            char[] idCs=Integer.toString(m.getId()).toCharArray();
            idTv.setText(idCs,0,idCs.length);
            idTv.setTextSize(20);

            TextView nameTv = (TextView)view.findViewById(R.id.punish_name);
            char[] nameCs=m.getName().toCharArray();
            nameTv.setText(nameCs,0,nameCs.length);
            nameTv.setTextSize(20);

            TextView costTv = (TextView)view.findViewById(R.id.punish_cost);
            char[] costCs=Integer.toString(m.getCost()).toCharArray();
            costTv.setText(costCs,0,costCs.length);
            costTv.setTextSize(20);

            TextView contentTv= (TextView) view.findViewById(R.id.punish_content);
            char[] contentCs=m.getContent().toCharArray();
            contentTv.setText(contentCs,0,contentCs.length);
            contentTv.setTextSize(20);

            return view;
        }
    }
}
