package com.lss.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView ;
    private Button readContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readContact= (Button) findViewById(R.id.readContact);
        readContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},null,null,null);
                while (cursor.moveToNext()){
                    String code = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.i("data","code:"+code+"——"+"name"+name);

                }
            }
        });
        textView = (TextView) findViewById(R.id.textViewOne);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                //动态跳出第二个界面
                overridePendingTransition(R.anim.tip_right,R.anim.tip_left);
            }
        });
    }

    //首先要激活菜单选项  由于OptionMenu是默认存在的 所以激活就可以不需要注册
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //建一个菜单填充器
        MenuInflater menuInflater = getMenuInflater();
        //在 menu中填充一个one进去
        menuInflater.inflate(R.menu.one,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //实现菜单选择
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击了之后传过来一个id
        int id = item.getItemId();
        switch(id){
            case R.id.save:
                Toast.makeText(MainActivity.this, "保存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sao:
                Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
