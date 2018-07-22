package com.lss.demo_08;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button queren;
    private EditText caozuo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        caozuo = (EditText) findViewById(R.id.caozuo);
        queren = (Button) findViewById(R.id.queren);


        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用自己的帮助类
                OpenSqlite os = new OpenSqlite(MainActivity.this);
                //触发数据库的状态
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                String xuhao = caozuo.getText().toString();

                if ("1".equals(xuhao)){
                    Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                    startActivity(intent);
                }else if ("2".equals(xuhao)){
                    Intent intent = new Intent(MainActivity.this,DeleteActivity.class);
                    startActivity(intent);
                }else if ("3".equals(xuhao)){
                    Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                    startActivity(intent);
                }else if ("4".equals(xuhao)){
                    Intent intent = new Intent(MainActivity.this,SelectActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "请输入正确的序号！", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
