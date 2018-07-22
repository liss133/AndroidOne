package com.lss.demo_08;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SelectActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());


    }
    public List<Map<String,Object>> getList (){
        List<Map<String,Object>> list = new ArrayList<>();
        OpenSqlite os = new OpenSqlite(SelectActivity.this);
        final SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        //查询要更新的数据
        Cursor cursor = sqLiteDatabase.rawQuery("select * from goods ",null);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("code","code");
        map1.put("name","name");
        map1.put("number","number");
        map1.put("price","price");
        list.add(map1);
        while(cursor.moveToNext()){
            String code = cursor.getString(cursor.getColumnIndex("code"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",code);
            map.put("name",name);
            map.put("number",number);
            map.put("price",price);
            list.add(map);
        }
        return list;
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
                view = SelectActivity.this.getLayoutInflater().inflate(R.layout.list,null);//null的意思是没有父控件

            }else{
                view = convertView;
            }
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView number = (TextView) view.findViewById(R.id.name);
            TextView code = (TextView) view.findViewById(R.id.code);
            TextView price = (TextView) view.findViewById(R.id.price);
            name.setText(getList().get(position).get("name").toString());
            number.setText(getList().get(position).get("number").toString());
            code.setText(getList().get(position).get("code").toString());
            price.setText(getList().get(position).get("price").toString());
            return view;
        }
    }
}
