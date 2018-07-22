package com.lss.demo_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button denglu,zc;
    private TextView title;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = (EditText)findViewById(R.id.username) ;
        password = (EditText)findViewById(R.id.password);
        final CharSequence usernameV = username.getText().toString();
        final CharSequence passwordV = password.getText().toString();
        password = (EditText)findViewById(R.id.password) ;
        denglu = (Button) findViewById(R.id.denglu);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                  // Toast.makeText(Main2Activity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                   Toast.makeText(Main2Activity.this, usernameV, Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(Main2Activity.this, "登陆失败", Toast.LENGTH_SHORT).show();
               }
            }
        });









    }
}
