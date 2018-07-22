package com.lss.demo_08;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private Button update;
    private EditText updatecode;

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
        setContentView(R.layout.activity_update);
        updatecode = (EditText) findViewById(R.id.updateCode);
        update = (Button) findViewById(R.id.update1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateCodeV = updatecode.getText().toString();
                if(isNumber(updateCodeV)){
                    OpenSqlite os = new OpenSqlite(UpdateActivity.this);
                    SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                    //先查询输入的序号存不存在 不存在则重新输入
                    Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where code ='"+Integer.parseInt(updateCodeV)+"' ",null);

                    if(cursor.moveToNext()){
                        Intent intent = new Intent(UpdateActivity.this,Update2Activity.class);
                        intent.putExtra("code",updateCodeV);
                        startActivity(intent);
                    }else{
                        Toast.makeText(UpdateActivity.this, "对不起序号不存在！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UpdateActivity.this, "序号请填入数字！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
