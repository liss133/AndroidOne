package com.lss.demo_10;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShouyeActivity extends AppCompatActivity {
    private TextView all;
    private TextView xing;
    private ListView listView;
    private String str = "%%";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);
        all = (TextView) findViewById(R.id.all);
        xing = (TextView) findViewById(R.id.xingbiao);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = "%%";
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(new MyAdapter());
            }
        });
        xing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = "%是%";
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(new MyAdapter());
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
        registerForContextMenu(listView);


    }
    public List<Map<String,Object>> getList (){
        List<Map<String,Object>> list = new ArrayList<>();
        OpenSqlite1 os = new OpenSqlite1(ShouyeActivity.this);
        final SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        //查询要更新的数据

        Cursor cursor = sqLiteDatabase.rawQuery("select * from contacter where xing like '"+str+"' order by name",null);
        while(cursor.moveToNext()){
            String code = cursor.getString(cursor.getColumnIndex("code"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String number = cursor.getString(cursor.getColumnIndex("number"));

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",code);
            map.put("name",name);
            map.put("number",number);

            list.add(map);
        }
        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.one,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                Intent intent = new Intent(ShouyeActivity.this,InsertActivity.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return getList().size();
        }

        @Override
        public Object getItem(int position) {
            return getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            if(convertView==null){
                //获取到要用的那个xml布局
                view = ShouyeActivity.this.getLayoutInflater().inflate(R.layout.list,null);//null的意思是没有父控件

            }else{
                view = convertView;
            }
            TextView code = (TextView) view.findViewById(R.id.code);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView number = (TextView) view.findViewById(R.id.number);
            code.setText(""+getList().get(position).get("name").toString().charAt(0));

            name.setText(getList().get(position).get("name").toString());
            number.setText(getList().get(position).get("number").toString());
            return view;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //菜单填充器
        MenuInflater menuInflater = getMenuInflater();
        //把one填充进去
        menuInflater.inflate(R.menu.two,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int code = (int)adapterContextMenuInfo.id;
        Log.i("code",""+code);

        Map<String,Object> map = getList().get(code);
        String codeV = map.get("code").toString();
        Log.i("code",""+codeV);
        int id = item.getItemId();
        switch(id){
            case R.id.delete:
                OpenSqlite1 os = new OpenSqlite1(ShouyeActivity.this);
                SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                sqLiteDatabase.execSQL("delete from contacter where code = '"+codeV+"'");
                Toast.makeText(ShouyeActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ShouyeActivity.this,ShouyeActivity.class);
                startActivity(intent1);
                break;
            case R.id.update:
                Intent intent = new Intent(ShouyeActivity.this,UpdateActivity.class);
                intent.putExtra("code",codeV);
                startActivity(intent);
                break;
            case R.id.detail:
                Intent intent2 = new Intent(ShouyeActivity.this,DetailActivity.class);
                intent2.putExtra("code",codeV);
                startActivity(intent2);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
