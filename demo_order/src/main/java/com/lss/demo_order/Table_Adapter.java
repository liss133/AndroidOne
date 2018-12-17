package com.lss.demo_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 李珊珊 on 2018/8/6.
 */
public class Table_Adapter extends BaseAdapter {
    private List<Table> list;
    private Context context;
    public Table_Adapter(List<Table> list, Context context){
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
            view = LayoutInflater.from(context).inflate(R.layout.table,null);
        }else{
            view = convertView;
        }
        //取出控件
        TextView id = (TextView) view.findViewById(R.id.tid);
        TextView tnum = (TextView) view.findViewById(R.id.tnum);
        TextView state = (TextView) view.findViewById(R.id.tstate);

        //imageView.setImageResource(Integer.parseInt(list.get(position).get("photo").toString()));

        id.setText(list.get(position).getId()+"");
        tnum.setText(""+list.get(position).getTnum()+"");
        state.setText(list.get(position).getState());
        return view;
    }
}
