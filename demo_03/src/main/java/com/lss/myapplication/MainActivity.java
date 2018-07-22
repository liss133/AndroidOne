package com.lss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView= (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new xihuanAdapter());
        imageView= (ImageView) findViewById(R.id.wodetaobaot );

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent是意图
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                //跳转时要发送意图
                startActivity(intent);
            }
        });


    }
    public List<Map<String,Object>> getList(){
        List<Map<String,Object>>list = new ArrayList<Map<String,Object>>();

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("photo",R.drawable.fdayi);
        map1.put("name","2016秋装大码条绒上衣韩版宽松灯芯风衣长款");
        map1.put("price","99.99");
        map1.put("person","1333");
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("photo",R.drawable.guocha);
        map2.put("name","纯手工水果片茶花果果干茶果养生7日套餐");
        map2.put("price","29.1");
        map2.put("person","1333");
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("photo",R.drawable.zdayi);
        map3.put("name","青蔷微 大码条绒上衣韩版宽松灯芯风衣长款");
        map3.put("price","128.45");
        map3.put("person","1333");
        Map<String,Object> map4 = new HashMap<String,Object>();
        map4.put("photo",R.drawable.doudouxie);
        map4.put("name","天天特价 豆豆鞋女夹棉加绒冬季皮毛一体 雪地鞋");
        map4.put("price","138");
        map4.put("person","123");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return list;
    }

    public class xihuanAdapter extends BaseAdapter {

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
            View view ;
            if(convertView==null){
                //获取到要用的那个xml布局
                view = MainActivity.this.getLayoutInflater().inflate(R.layout.cainixihuan,null);//null的意思是没有父控件

            }else{
                view = convertView;
            }
            //依次取出控件
            //图片控件
            Log.i("1",getList().get(position).get("photo").toString());
            ImageView imageView = (ImageView) view.findViewById( R.id.photo);
            imageView.setImageResource(Integer.parseInt(getList().get(position).get("photo").toString()));
            //第一个文字控件
            //注意这里取出控件的时候要用view.find来取出不然绘出现空指针错误
            TextView textView = (TextView)view.findViewById(R.id.price);
            textView.setText("￥"+getList().get(position).get("price").toString());
            TextView textView1 = (TextView)view.findViewById(R.id.name);
            textView1.setText(getList().get(position).get("name").toString());

            TextView textView2 = (TextView) view.findViewById(R.id.person);
            textView2.setText(getList().get(position).get("person").toString()+"人付款");

            return view;
        }

    }
}
