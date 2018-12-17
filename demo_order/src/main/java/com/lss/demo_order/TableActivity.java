package com.lss.demo_order;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    private ListView listView;
    private int hid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        listView = (ListView) findViewById(R.id.listViewt);
        Intent intent = getIntent();
        hid = intent.getIntExtra("hid",0);
        new MyTask().execute(IpUtils.TABLEPATH);
    }

    class MyTask extends AsyncTask<String,Void,List<Table>> {

        @Override
        protected List<Table> doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");

                hc.setReadTimeout(5000);

                BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
                String str = "";
                StringBuffer sf = new StringBuffer();
                while((str = br.readLine())!=null){
                    sf.append(str);
                }
                return getJsonToList(sf.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Table> list) {
            //list为返回来的数据
            final List<Table> list1 = list;
            listView.setAdapter(new Table_Adapter(list,TableActivity.this));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    int bid = list1.get(position).getId();
                    //Toast.makeText(TableActivity.this,bid+ "", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(TableActivity.this);
                    builder.setTitle("信息确认")
                            .setMessage("确认预定此餐桌")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(TableActivity.this,MenuActivity.class);
                                    intent.putExtra("tid",list1.get(position).getId());
                                    intent .putExtra("hid",hid);
                                    startActivity(intent);
                                    Toast.makeText(TableActivity.this, "预定成功!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("刷新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(TableActivity.this,TableActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                }
            });
            super.onPostExecute(list);
        }

        public List<Table> getJsonToList(String json){
            ArrayList<Table> list = new ArrayList<Table>();
            //android解析json
            try {
                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                   ;Table table = new Table();
                    table.setId(jsonObject.getInt("id"));
                    table.setTnum(jsonObject.getInt("tnum"));
                    table.setState(jsonObject.getString("state"));
                    list.add(table);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
