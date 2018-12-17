package com.lss.demo_order;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MaidanActivity extends AppCompatActivity {
    private TextView osum,discountsum,ljjf;
    private Button querenpay;
    private int oid;
    private double sumV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maidan);
        osum = (TextView) findViewById(R.id.osum);
        ljjf = (TextView) findViewById(R.id.ljjf);
        discountsum = (TextView) findViewById(R.id.discountsum);
        querenpay = (Button) findViewById(R.id.querenpay);
        Intent intent = getIntent();
        oid = intent.getIntExtra("oid",0);
        sumV = Double.parseDouble(intent.getStringExtra("sum"));
        //Toast.makeText(MaidanActivity.this, sumV+"", Toast.LENGTH_SHORT).show();
        new MyTask().execute(IpUtils.MAIDANPATH);

    }

    class MyTask extends AsyncTask<String ,Void,String>{
        @Override
        protected String doInBackground(String... params) {

            try {
                //建立和params[0]指定的地址的连接
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");
                hc.setReadTimeout(5000);
                OutputStream out = hc.getOutputStream();
                String values = "oid="+oid+"&sum="+sumV;
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
            //Toast.makeText(MaidanActivity.this, s+"", Toast.LENGTH_SHORT).show();
            String strs[] = s.split("@");
            osum.setText(""+sumV);
            discountsum.setText(strs[0]);
            ljjf.setText(strs[1]);
            super.onPostExecute(s);
        }
    }
}
