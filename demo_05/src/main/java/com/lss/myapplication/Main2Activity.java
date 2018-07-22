package com.lss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter <String> arrayAdapter;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView= (ListView) findViewById(R.id.listView);
        button= (Button) findViewById(R.id.to3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
                //动态跳出第三个界面
                overridePendingTransition(R.anim.tip_right,R.anim.tip_left);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getList());
        listView.setAdapter(arrayAdapter);
        //讲菜单注册到长点击事件上
        registerForContextMenu(listView);

    }
    public List<String> getList(){
        List<String> list = new ArrayList<String>();
        list.add("数据1");
        list.add("数据2");
        list.add("数据3");
        list.add("数据4");
        list.add("数据5");
        return list;
    }

    //注册和激活

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //菜单填充器
        MenuInflater menuInflater = getMenuInflater();
        //把one填充进去
        menuInflater.inflate(R.menu.one,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    //菜单选择器
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.save:
                Toast.makeText(Main2Activity.this, "保存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(Main2Activity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sao:
                Toast.makeText(Main2Activity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
