package com.lss.demo_09;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShouyeActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());


    }
    public List<Map<String,Object>> getList (){
        List<Map<String,Object>> list = new ArrayList<>();
        OpenSqlite1 os = new OpenSqlite1(ShouyeActivity.this);
        final SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        //查询要更新的数据
        Cursor cursor = sqLiteDatabase.rawQuery("select * from contacter ",null);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("code","code");
        map1.put("name","name");
        map1.put("number","number");
        map1.put("image","image");
        list.add(map1);
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
            case R.id.search:
               //搜索
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
            if(position==0){
                code.setText(getList().get(position).get("name").toString());
            }else{
                code.setText(""+getList().get(position).get("name").toString().charAt(0));
            }

            name.setText(getList().get(position).get("name").toString());
            number.setText(getList().get(position).get("number").toString());
            return view;
        }
    }
}
