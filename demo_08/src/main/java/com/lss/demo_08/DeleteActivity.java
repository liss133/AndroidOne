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

public class DeleteActivity extends AppCompatActivity {
    private Button delete;
    private EditText deletecode;

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
        setContentView(R.layout.activity_delete);
        deletecode = (EditText) findViewById(R.id.deletecode);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deletecodeV = deletecode.getText().toString();
                if(isNumber(deletecodeV)){
                    OpenSqlite os = new OpenSqlite(DeleteActivity.this);
                    SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                        //先查询输入的序号存不存在 不存在则重新输入
                    Cursor cursor = sqLiteDatabase.rawQuery("select * from goods where code ='"+Integer.parseInt(deletecodeV)+"' ",null);

                    if(cursor.moveToNext()){
                        sqLiteDatabase.execSQL("delete from goods where code = '"+Integer.parseInt(deletecodeV)+"'");
                        Toast.makeText(DeleteActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(DeleteActivity.this, "对不起序号不存在！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DeleteActivity.this, "序号请填入数字！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
