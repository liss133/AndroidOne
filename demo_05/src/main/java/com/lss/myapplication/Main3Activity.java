package com.lss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Button to5;
    //Action Mode模式
    private ActionMode actionMode;
    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.one,menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch(id){
                case R.id.save:
                    Toast.makeText(Main3Activity.this, "保存", Toast.LENGTH_SHORT).show();
                    //点击之后要将其关闭
                    mode.finish();
                    break;
                case R.id.delete:
                    Toast.makeText(Main3Activity.this, "删除", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
                case R.id.sao:
                    Toast.makeText(Main3Activity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
        }
    };
    public List<String> getList(){
        List<String> list = new ArrayList<String>();
        list.add("数据1");
        list.add("数据2");
        list.add("数据3");
        list.add("数据4");
        list.add("数据5");
        return list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView = (ListView) findViewById(R.id.listView2);
        to5 = (Button) findViewById(R.id.to5);
        to5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this,Main5Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tip_right,R.anim.tip_left);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(Main3Activity.this,R.layout.support_simple_spinner_dropdown_item,getList());
        listView.setAdapter(arrayAdapter);
        //注册一个长点击时间到listView上
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(actionMode!=null){
                    return false;
                }
                actionMode=Main3Activity.this.startSupportActionMode(callback);
                return true;
            }
        });
    }

}
