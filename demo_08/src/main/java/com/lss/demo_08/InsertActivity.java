package com.lss.demo_08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText name,number,price;
    private Button insert;
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
        setContentView(R.layout.activity_insert);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        price = (EditText) findViewById(R.id.price);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNumber(number.getText().toString())&&isNumber(price.getText().toString())) {
                    String nameV = name.getText().toString();
                    int numberV = Integer.parseInt(number.getText().toString());
                    int priceV = Integer.parseInt(price.getText().toString());
                    OpenSqlite os = new OpenSqlite(InsertActivity.this);
                    SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                    sqLiteDatabase.execSQL("insert into goods (name,number,price) values ('"+nameV+"','"+numberV+"','"+priceV+"')");
                    Toast.makeText(InsertActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(InsertActivity.this, "对不起请输入整数!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
