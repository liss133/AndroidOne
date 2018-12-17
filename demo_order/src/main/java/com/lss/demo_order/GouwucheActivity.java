package com.lss.demo_order;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class GouwucheActivity extends AppCompatActivity {
    private int oid;
    private ListView listView;
    private TextView sum;
    private String sumV="0.0";
    private int odid;
    private List<Gouwuche> list1;
    private Button maidan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwuche);
        Intent intent = getIntent();
        oid = intent.getIntExtra("oid",0);
        //Toast.makeText(GouwucheActivity.this, ""+oid, Toast.LENGTH_SHORT).show();
        listView= (ListView) findViewById(R.id.listView2);
        sum = (TextView) findViewById(R.id.sum);
        maidan = (Button) findViewById(R.id.maidan);
        new MyTaskToGou().execute(IpUtils.GOUCAIPATH);
        maidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GouwucheActivity.this,MaidanActivity.class);
                intent.putExtra("oid",oid);
                Toast.makeText(GouwucheActivity.this, sumV+"", Toast.LENGTH_SHORT).show();
                intent.putExtra("sum",sumV);
                startActivity(intent);
            }
        });

    }

    class MyTaskToGou extends AsyncTask<String,Void,List<Gouwuche>> {


        @Override
        protected List<Gouwuche> doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");
                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "oid="+oid;
                out.write(values.getBytes());
                out.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
                String str = "";
                StringBuffer sf = new StringBuffer();
                while((str = br.readLine())!=null){
                    sf.append(str);
                }

                return getJsonToListG(sf.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Gouwuche> list) {
            sum.setText(sumV);
            list1 = list;
            listView.setAdapter(new Gouwuche_Adapter(list,GouwucheActivity.this));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    odid = list1.get(position).getNum();
                    //Toast.makeText(GouwucheActivity.this, odid+"", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(GouwucheActivity.this);
                    builder.setTitle("删除确认")
                            .setMessage("确认从购物车中移除菜品？")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new MyTaskDelGou().execute(IpUtils.DELGOUPATH);
                                    Toast.makeText(GouwucheActivity.this, "移除成功!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            });
            super.onPostExecute(list);
        }

        public List<Gouwuche> getJsonToListG(String json){
            String [] strs = json.split("@");
            sumV = strs[0];
            ArrayList<Gouwuche> list = new ArrayList<Gouwuche>();
            //android解析json
            try {
                JSONArray jsonArray = new JSONArray(strs[1]);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gouwuche gwc = new Gouwuche();
                    gwc.setCname(jsonObject.getString("cname"));
                    gwc.setCprice(jsonObject.getDouble("cprice"));
                    gwc.setNum(jsonObject.getInt("num"));
                    list.add(gwc);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    class MyTaskDelGou extends AsyncTask<String,Void,List<Gouwuche>>{

        @Override
        protected List<Gouwuche> doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");
                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "odid="+odid+"&oid="+oid;
                out.write(values.getBytes());
                out.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(hc.getInputStream()));
                String str = "";
                StringBuffer sf = new StringBuffer();
                while((str = br.readLine())!=null){
                    sf.append(str);
                }

                return getJsonToListG(sf.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Gouwuche> list) {
            sum.setText(sumV);

            listView.setAdapter(new Gouwuche_Adapter(list,GouwucheActivity.this));
            super.onPostExecute(list);
        }

        public List<Gouwuche> getJsonToListG(String json){
            String [] strs = json.split("@");
            sumV = strs[0];
            ArrayList<Gouwuche> list = new ArrayList<Gouwuche>();
            //android解析json
            try {
                JSONArray jsonArray = new JSONArray(strs[1]);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gouwuche gwc = new Gouwuche();
                    gwc.setCname(jsonObject.getString("cname"));
                    gwc.setCprice(jsonObject.getDouble("cprice"));
                    gwc.setNum(jsonObject.getInt("num"));
                    list.add(gwc);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
