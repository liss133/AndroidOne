package com.lss.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name,pw;
    private Button toMain2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        pw = (EditText) findViewById(R.id.pw);
        toMain2 = (Button) findViewById(R.id.button);
        //b保护
        SharedPreferences spf = getSharedPreferences("data",0);
        //这里就不再用编辑器了
        //如果name1有值则等于name1 如果没有就等于nothing
        String name1 = spf.getString("name1","nothing");
        if(!"nothing".equals(name1)){
            name.setText(name1);
        }
        toMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先得到要传的数据
                String nameV = name.getText().toString().trim();
                String pwV = pw.getText().toString().trim();
                //第一种方法
                //装进intent中
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("name",nameV);
                intent.putExtra("pw",pwV);
                //或者：装进一个Bundle中
                /*Bundle bundle = new Bundle();
                bundle.putString("name",nameV);
                bundle.putString("pw",pwV);
                intent.putExtras(bundle);*/

                /*startActivity(intent);*///单向跳转

                //如果需要双向传值：
                startActivityForResult(intent,0);//传一个requestCode是0
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==200){
            String backValue = data.getStringExtra("backValue");
            Toast.makeText(MainActivity.this, backValue, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //获取到需要保护的值
        String name1 = name.getText().toString().trim();
        if("".equals(name1)){
            Toast.makeText(MainActivity.this, "未输入值不需要保护", Toast.LENGTH_SHORT).show();
        }else{
            //查询是否有一个文件叫data 有就找data.xml 否则创建一个data；0代表的意思是可读可写
            SharedPreferences spf = getSharedPreferences("data",0);
            //获得可编辑权限
            SharedPreferences.Editor editer = spf.edit();
            editer.putString("name1",name1);
            Boolean bol = editer.commit();
            Toast.makeText(MainActivity.this, "保护状态"+bol, Toast.LENGTH_SHORT).show();


        }


    }
}
