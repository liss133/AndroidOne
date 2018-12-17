package com.lss.demo_order;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2018-07-25.
 */
public class Caipin_Adapter extends BaseAdapter{


    private List<Caipin> list;
    private Context context;
    public Caipin_Adapter(List<Caipin> list, Context context){
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
        //在适配器中获取image_text.xml获取这个布局文件
        //在从这个文件中获取需要的控件
        View view; //== image_text.xml
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.caipin,null);
        }else{
            view = convertView;
        }
        //取出控件
        ImageView imageView = (ImageView) view.findViewById(R.id.photo);
        TextView textView1 = (TextView) view.findViewById(R.id.name);
        TextView textView2 = (TextView) view.findViewById(R.id.price);
        TextView textView3 = (TextView) view.findViewById(R.id.bz);
        TextView textView4 = (TextView) view.findViewById(R.id.type);


        //imageView.setImageResource(Integer.parseInt(list.get(position).get("photo").toString()));
        imageView.setImageResource(R.mipmap.ic_launcher);
        textView1.setText(list.get(position).getName());
        textView2.setText(""+list.get(position).getPrice());
        textView3.setText(list.get(position).getBz());
        textView4.setText(list.get(position).getType());

        //为了防止出现的第一个一直来回换 可以设置一个tag防止缓存
        imageView.setTag(list.get(position).getImage());
        new getImage(imageView,list.get(position).getImage()).execute(list.get(position).getImage());

        return view;
    }

    //由于拿到的是一个图片的地址，所以需要开启一个异步任务去进行图片存储

    class getImage extends AsyncTask<String,Void,Bitmap> {
        private ImageView imageView;
        private String tag;
        public getImage(ImageView imageView,String tag){
            this.imageView = imageView;
            this.tag = tag;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                HttpURLConnection hc = (HttpURLConnection) new URL(params[0]).openConnection();
                hc.setRequestMethod("POST");
                hc.setReadTimeout(5000);
                //使用得到的数据创建一个bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(hc.getInputStream());
                return  bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPreExecute();
            if(tag.equals(imageView.getTag())){
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
