package com.lss.demo_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 李珊珊 on 2018/8/7.
 */
public class Gouwuche_Adapter extends BaseAdapter {
    private List<Gouwuche> list;
    private Context context;
    public Gouwuche_Adapter(List<Gouwuche> list, Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view; //== image_text.xml
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.gouwuche,null);
        }else{
            view = convertView;
        }
        //取出控件
        TextView cname = (TextView) view.findViewById(R.id.cname);
        TextView cprice = (TextView) view.findViewById(R.id.cprice);
        TextView cnum = (TextView) view.findViewById(R.id.cnum);


        cname.setText(list.get(position).getCname());
        cprice.setText(list.get(position).getCprice()+"");
        cnum.setText("一份");
        return view;
    }
}
