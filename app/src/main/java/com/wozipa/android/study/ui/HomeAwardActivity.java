package com.wozipa.android.study.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.wozipa.android.study.controller.AwardController;
import com.wozipa.android.study.model.Award;
import com.wozipa.android.study.ui.id.ActivityIds;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeAwardActivity extends AppCompatActivity  {

    private static final Logger LOGGER=Logger.getLogger(HomeAwardActivity.class);

    public static final String INTENT_MODE="intent.mode";
    public static final String CREATE_MODE="createMode";
    public static final String CHANGE_MODE="changeMode";

    private ListView listView=null;
    private List<Award> awardList=new ArrayList<Award>();
    private List<Map<String,Object>> rowList=new ArrayList<Map<String,Object>>();
    private AwardsAdapter adapter=null;
    private Map<Integer,Boolean> checkBoxState=new HashMap<>();

    private AwardController controller=new AwardController();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;

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

        final Button button1=(Button)findViewById(R.id.home_award_2_act);
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

        Button createBtn= (Button) findViewById(R.id.home_award_create);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click the item");
                Intent intent = new Intent(getApplicationContext(), EditAward.class);
                intent.putExtra(INTENT_MODE,CREATE_MODE);
                startActivityForResult(intent, ActivityIds.CREATE_AWARD);
            }
        });

        Button deleteBtn= (Button) findViewById(R.id.home_award_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Integer> keySet=checkBoxState.keySet();
                for(Integer position:keySet)
                {
                    Award award= (Award) adapter.getItem(position);
                    boolean result=controller.delete(award);
                    if(result)
                    {
                        awardList.remove(award);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        listView= (ListView) findViewById(R.id.awradListView);
        adapter=new AwardsAdapter();
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("click the item");
                Intent intent = new Intent(getApplicationContext(), EditAward.class);
                Award award= (Award) adapter.getItem(position);
                awardList.remove(award);
                intent.putExtra(INTENT_MODE,CHANGE_MODE);
                intent.putExtra(EditAward.AWARD_ID,award.getId());
                intent.putExtra(EditAward.AWARD_NAME,award.getName());
                intent.putExtra(EditAward.AWARD_CONTENT,award.getContent());
                intent.putExtra(EditAward.AWARD_COST,award.getCost());
                startActivityForResult(intent, ActivityIds.CREATE_AWARD);
            }
        });
        //init the value of the data
        Award[] awards=controller.listUndone();
        for(Award award:awards)
        {
            awardList.add(award);
        }
        adapter.notifyDataSetChanged();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null)
        {
            return;
        }
        Bundle b=data.getExtras();
        if(resultCode==ActivityIds.CREATE_AWARD)
        {
            //when add or edit the award result
            int awardId=b.getInt(EditAward.AWARD_ID);
            String awardName=b.getString(EditAward.AWARD_NAME);
            String awardContent=b.getString(EditAward.AWARD_CONTENT);
            int awardCost=b.getInt(EditAward.AWARD_COST);
            Award award=new Award(awardName,awardCost,awardContent);
            award.setId(awardId);
            awardList.add(award);
            adapter.notifyDataSetChanged();
        }
    }

    public void addAward2List(Award award) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public com.google.android.gms.appindexing.Action getIndexApiAward() {
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
        AppIndex.AppIndexApi.start(client, getIndexApiAward());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAward());
        client.disconnect();
    }

    public void initAwards()
    {
        Award[] awards=controller.listUndone();
        if(awards!=null && awards.length>=1)
        {
            for(Award award:awards)
            {
                awardList.add(award);
//                HashMap row=new HashMap<String,Object>();
//                row.put(R.id.award_id,award.getId());
//                row.put(R.id.award_name,award.getName());
//                row.put(R.id.award_cost,award.getCost());
//                row.put(R.id.award_content,award.getContent());
//                rowList.add(row);
            }
            adapter.notifyDataSetChanged();
        }
        else
        {
            System.out.println("the award is null or empty");
        }
    }

    class AwardsAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return awardList.size();
        }

        @Override
        public Object getItem(int position) {
            return awardList.get(position);
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
                LayoutInflater inflater = HomeAwardActivity.this.getLayoutInflater();
                //因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.activity_award_list_item, null);
            }else{
                view = convertView;
            }
            Award m = awardList.get(position);
            System.out.println(m.toString());

            CheckBox cbx = (CheckBox)view.findViewById(R.id.award_checkBox);
            cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        checkBoxState.put(position,true);
                    }
                    else
                    {
                        checkBoxState.remove(position);
                    }
                }
            });

            TextView idTv = (TextView)view.findViewById(R.id.award_id);
            char[] idCs=Integer.toString(m.getId()).toCharArray();
            idTv.setText(idCs,0,idCs.length);
            idTv.setTextSize(20);

            TextView nameTv = (TextView)view.findViewById(R.id.award_name);
            char[] nameCs=m.getName().toCharArray();
            nameTv.setText(nameCs,0,nameCs.length);
            nameTv.setTextSize(20);

            TextView costTv = (TextView)view.findViewById(R.id.award_cost);
            char[] costCs=Integer.toString(m.getCost()).toCharArray();
            costTv.setText(costCs,0,costCs.length);
            costTv.setTextSize(20);

            TextView contentTv= (TextView) view.findViewById(R.id.award_content);
            char[] contentCs=m.getContent().toCharArray();
            contentTv.setText(contentCs,0,contentCs.length);
            contentTv.setTextSize(20);

            return view;
        }
    }
}

