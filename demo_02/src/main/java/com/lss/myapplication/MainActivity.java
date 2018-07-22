package com.lss.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private int data[]={R.drawable.buythg,R.drawable.nicefood,R.drawable.paymoney,R.drawable.service,R.drawable.buythg,R.drawable.buythg,
                         R.drawable.nicefood,R.drawable.paymoney,R.drawable.service,R.drawable.nicefood,R.drawable.paymoney,R.drawable.service,
                         R.drawable.nicefood,R.drawable.paymoney,R.drawable.service,R.drawable.nicefood,R.drawable.paymoney,R.drawable.service, };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView =(GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter());
    }
    public List<String> getList(){
        List<String> list = null ;
        for(int i = 0;i<20;i++){
            list.add("shuju"+i);
        }
        return list;
    }
    //内部类
    public class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            TextView textView;
            LinearLayout linearLayout;

        if(convertView==null){
        imageView = new ImageView(MainActivity.this);
        }else{
        imageView =(ImageView) convertView;
        }
        imageView.setImageResource(data[position]);
        return imageView;
        }



        }


        }
