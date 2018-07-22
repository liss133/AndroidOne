package com.lss.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caidan);
        listView = (ListView) findViewById(R.id.listView);
        List<Map<String,Object>> list = getList();
        Log.i("1",list.get(1).get("photo").toString()+"你好"+list.get(1).get("rating").toString());
        listView.setAdapter(new menuAdapter());

    }
    //得到数据
    public List<Map<String,Object>> getList(){

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 1; i<=10; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("photo",R.drawable.nicefood);
            map.put("rating",i%6);
            list.add(map);
        }
        return list;
    }



    public class menuAdapter extends BaseAdapter{

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
                view = Main2Activity.this.getLayoutInflater().inflate(R.layout.one,null);//null的意思是没有父控件

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
            TextView textView = (TextView)view.findViewById(R.id.neirong);
            textView.setText("abc");
            //星星
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            ratingBar.setRating(Float.parseFloat(getList().get(position).get("rating").toString()));
            //分数
            TextView textView1 = (TextView) view.findViewById(R.id.fenshu);
            textView1.setText("1.0");
            //型号
            TextView textView2 = (TextView) view.findViewById(R.id.xinghao);
            textView2.setText("小米");

            return view;
        }

    }
}
