package com.lss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    private Button caidan,to4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        caidan = (Button) findViewById(R.id.caidan);
        to4 = (Button) findViewById(R.id.to4);
        to4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main5Activity.this,Main4Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tip_right,R.anim.tip_left);
            }
        });
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Main5Activity.this,v);
                //将菜单填充进去
                popupMenu.getMenuInflater().inflate(R.menu.one,popupMenu.getMenu());
                //将操蛋显示出来
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch(id){
                            case R.id.save:
                                Toast.makeText(Main5Activity.this, "保存", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(Main5Activity.this, "删除", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.sao:
                                Toast.makeText(Main5Activity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });

            }
        });
    }
}
