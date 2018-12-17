package com.lss.demo_order;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

public class MenuActivity extends AppCompatActivity {
    private ImageView shucai,junlei,roulei,haixian,qita,guodi;
    private ListView listView;
    private Button gwc;
    private String type = "";
    private int tid;
    private int oid = 1;
    //接收过来的会员的编号
    private int hid;
    private int cid;
    private TextView hid1,oid1,tid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        shucai = (ImageView) findViewById(R.id.qingcai);
        junlei = (ImageView) findViewById(R.id.junlei);
        roulei = (ImageView) findViewById(R.id.roulei);
        haixian = (ImageView) findViewById(R.id.haixian);
        qita = (ImageView) findViewById(R.id.qita);
        guodi = (ImageView) findViewById(R.id.guodi);
        listView = (ListView) findViewById(R.id.listView);
        hid1 = (TextView) findViewById(R.id.hid);
        oid1 = (TextView) findViewById(R.id.oid);
        tid1 = (TextView) findViewById(R.id.tid);
        gwc= (Button) findViewById(R.id.gwc);
        Intent intent = getIntent();
        hid = intent.getIntExtra("hid",0);
        tid = intent.getIntExtra("tid",0);

        new MyTask().execute(IpUtils.SEARCHPATH);

        tid1.setText(tid+"");
        hid1.setText(hid+"");

        gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GouwucheActivity.class);
                //Toast.makeText(MenuActivity.this, ""+oid, Toast.LENGTH_SHORT).show();
                intent.putExtra("oid",oid);
                startActivity(intent);
            }
        });
        shucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "青菜";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
        junlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "菌类";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
        roulei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "肉类";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
        haixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "海鲜";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
        qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "其他";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
        guodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "锅底";
                new MyTask().execute(IpUtils.SEARCHPATH);
            }
        });
    }
    class MyTask extends AsyncTask<String,Void,List<Caipin>> {

        @Override
        protected List<Caipin> doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");

                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "type="+type+"&tid="+tid+"&hid="+hid+"&oid="+oid;
                out.write(values.getBytes());
                out.close();

                //接受web端的数据
                //buffrerReader 是用来升级InputStreamReader 的  InputStreamReader 是用来读取InputStream的
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
        protected void onPostExecute(List<Caipin> list) {
            //s为返回来的数据

            final List<Caipin> list1 = list;
            listView.setAdapter(new Caipin_Adapter(list,MenuActivity.this));
            oid1.setText(oid+"");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    cid = list1.get(position).getId();
                    //Toast.makeText(MenuActivity.this, ""+cid, Toast.LENGTH_SHORT).show();
                    new MyTaskOrder().execute(IpUtils.ORDERCAIPATH);
                }
            });
            super.onPostExecute(list);
        }

        public List<Caipin> getJsonToList(String json){
            String [] strs = json.split("@");
            oid = Integer.parseInt(strs[0]);
            ArrayList<Caipin> list = new ArrayList<Caipin>();
            //android解析json
            try {
                JSONArray jsonArray = new JSONArray(strs[1]);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Caipin caipin = new Caipin();
                    caipin.setId(jsonObject.getInt("id"));
                    caipin.setName(jsonObject.getString("name"));
                    caipin.setBz(jsonObject.getString("bz"));
                    caipin.setImage(jsonObject.getString("image"));
                    caipin.setPrice(jsonObject.getDouble("price"));
                    caipin.setType(jsonObject.getString("type"));
                    list.add(caipin);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class  MyTaskOrder extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");
                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "oid="+oid+"&cid="+cid;
                out.write(values.getBytes());
                out.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
                String str = "";
                StringBuffer sf = new StringBuffer();
                while((str = br.readLine())!=null){
                    sf.append(str);
                }

                return sf.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MenuActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
    }


}
