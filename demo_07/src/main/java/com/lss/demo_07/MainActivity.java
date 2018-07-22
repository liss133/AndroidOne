package com.lss.demo_07;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText name,pw;
    private Button read,save;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        pw = (EditText) findViewById(R.id.pw);
        read = (Button) findViewById(R.id.read);
        save = (Button) findViewById(R.id.save);
        //XML 存储方式
        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameV = name.getText().toString();
                String pwV = pw.getText().toString();
                //data存在就去找data不存在就创建一个data
                SharedPreferences sharedPreferences = getSharedPreferences("data",0);
                //获取编辑权限
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",nameV);
                editor.putString("pw",pwV);
                Boolean bol = editor.commit();
                Toast.makeText(MainActivity.this, "保存状态"+bol, Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data",0);
                //后边的nothing的意思是如果不存在就给一个nothing给它
                String name1 = sharedPreferences.getString("name","nothing");
                String pw1 = sharedPreferences.getString("pw","nothing");
                Toast.makeText(MainActivity.this, name1+"@"+pw1, Toast.LENGTH_SHORT).show();


            }
        });*/
        //IO流方式
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameV = name.getText().toString();
                String pwV = pw.getText().toString();
                //写入流
                FileOutputStream out = null;
                Boolean bol = false;
                try {
                    //创建一个文件
                    out = openFileOutput("data.txt",0);
                    String str = nameV+"@"+pwV;
                    //将str以字节方式写入
                    out.write(str.getBytes());
                    bol = true;
                    Toast.makeText(MainActivity.this, "保护状态是"+bol, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream input = openFileInput("data.txt");
                    int length = 0;
                    String str = "";
                    String [] str1 = null;
                    //方法一
                    /*while((length=input.read())!=-1){
                        str = str + (char)length;
                    }*/
                    //方法二
                    byte [] bytes = new byte[2048];
                    while((length=input.read(bytes))!=-1){
                        str = new String(bytes,0,length);
                        str1 = str.split("@");
                    }
                    input.close();
                    Toast.makeText(MainActivity.this, "姓名:"+str1[0]+"密码:"+str1[1], Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
