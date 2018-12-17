package com.lss.demo_order;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OrderActivity extends AppCompatActivity {
    private EditText number;
    private Button enter,register;
    private String numberV = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        number = (EditText) findViewById(R.id.number);
        enter= (Button) findViewById(R.id.enter);
        register= (Button) findViewById(R.id.register);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberV = number.getText().toString().trim();
                new MyTask().execute(IpUtils.LOGINPATH);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");

                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "number="+numberV;
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
            //Toast.makeText(OrderActivity.this, s, Toast.LENGTH_SHORT).show();
            if("对不起此号码非会员！".equals(s)){
                Toast.makeText(OrderActivity.this, "对不起会员号码不存在！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderActivity.this,OrderActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(OrderActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                int hid = Integer.parseInt(s);
                Intent intent = new Intent(OrderActivity.this,TableActivity.class);
                intent.putExtra("hid",hid);
                startActivity(intent);
            }
            super.onPostExecute(s);
        }
    }
}
