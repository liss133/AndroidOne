package com.lss.demo_08;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update2Activity extends AppCompatActivity {
    private EditText name;
    private EditText number;
    private EditText price;
    private Button update;
    public boolean isNumber(String str){
        for(int i = 0;i<str.length();i++){
            if(!(Character.isDigit(str.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        name= (EditText) findViewById(R.id.name);
        number= (EditText) findViewById(R.id.number);
        price= (EditText) findViewById(R.id.price);
        update = (Button) findViewById(R.id.update);
        Intent intent = getIntent();
        final String code = intent.getStringExtra("code");
        Log.i("code",code);
        OpenSqlite os = new OpenSqlite(Update2Activity.this);
        final SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        //查询要更新的数据
        Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where code ='"+Integer.parseInt(code)+"' ",null);

        if(cursor.moveToNext()){
            final String nameV = cursor.getString(cursor.getColumnIndex("name"));
            final String numberV = cursor.getString(cursor.getColumnIndex("number"));
            final String priceV = cursor.getString(cursor.getColumnIndex("price"));
            Log.i("name",nameV);
            Log.i("number",numberV);
            Log.i("price",priceV);

            name.setText(nameV);
            number.setText(numberV);
            price.setText(priceV);
             update.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(isNumber(number.getText().toString())&&isNumber(price.getText().toString())) {
                         sqLiteDatabase.execSQL("update goods set name ='"+name.getText().toString()+"',number='"+Integer.parseInt(number.getText().toString())+"'" +
                                 ",price='"+Integer.parseInt(price.getText().toString())+"' where code = '"+Integer.parseInt(code)+"'");
                         Toast.makeText(Update2Activity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                         Intent intent1 = new Intent(Update2Activity.this,MainActivity.class);
                         startActivity(intent1);
                     }else{
                         Toast.makeText(Update2Activity.this, "对不起数量和价格请输入整数!", Toast.LENGTH_SHORT).show();
                     }
                 }
             });


        }else{
            Toast.makeText(Update2Activity.this, "对不起序号不存在！", Toast.LENGTH_SHORT).show();
        }

    }
}
