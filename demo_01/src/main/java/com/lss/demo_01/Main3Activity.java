package com.lss.demo_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView nicefood,playfun,service,buythg,paymoney,newcategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caidan);
        nicefood = (ImageView) findViewById(R.id.nicefood);
        playfun = (ImageView) findViewById(R.id.playfun);
        service = (ImageView) findViewById(R.id.service);
        buythg = (ImageView) findViewById(R.id.buythg);
        newcategory = (ImageView) findViewById(R.id.newcategory);
        paymoney = (ImageView) findViewById(R.id.paymoney);

        nicefood.setOnClickListener(this);
        playfun.setOnClickListener(this);
        service.setOnClickListener(this);
        buythg.setOnClickListener(this);
        newcategory.setOnClickListener(this);
        paymoney.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
            int id = v.getId();
        switch(id){
            case R.id.nicefood:
                Log.i("调试信息","美食");
                Toast.makeText(Main3Activity.this, "美食", Toast.LENGTH_SHORT).show();
                break;
            case R.id.playfun:
                Log.i("调试信息","娱乐");
                Toast.makeText(Main3Activity.this, "娱乐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.service:
                Log.i("调试信息","生活");
                Toast.makeText(Main3Activity.this, "生活", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buythg:
                Log.i("调试信息","购物");
                Toast.makeText(Main3Activity.this, "购物", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paymoney:
                Log.i("调试信息","支付");
                Toast.makeText(Main3Activity.this, "支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.newcategory:
                Log.i("调试信息","商家");
                Toast.makeText(Main3Activity.this, "商家", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
