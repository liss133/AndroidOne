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

public class RegisterActivity extends AppCompatActivity {
    private EditText newnumber;
    private Button registerHy;
    private String numberV = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newnumber = (EditText) findViewById(R.id.newnumber);
        registerHy = (Button) findViewById(R.id.registerHy);
        registerHy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberV = newnumber.getText().toString().trim();
                if(numberV.length()!=11||!numberV.matches("[1][3578]\\d{9}")){
                    Toast.makeText(RegisterActivity.this, "请正确输入11位电话号码！", Toast.LENGTH_SHORT).show();
                }else{
                    new MyTask().execute(IpUtils.REGISTERPATH);
                    //送到Java工程中检验如果数据库中没有 则添加成为新会员（累计消费为0） 如果有则告知请重新输入或直接登陆


                }
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
       //回归主线程打印一句话


        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            if("对不起此号码已注册会员！".equals(s)||"对不起请重新输入号码注册！".equals(s)){
                Intent intent = new Intent(RegisterActivity.this,RegisterActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(RegisterActivity.this,OrderActivity.class);
                startActivity(intent);
            }
            super.onPostExecute(s);
        }
    }
}
