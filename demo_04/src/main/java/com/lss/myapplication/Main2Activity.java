package com.lss.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private EditText name,pw;
    private Button toMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = (EditText) findViewById(R.id.name2);
        pw = (EditText) findViewById(R.id.pw2);
        toMain = (Button) findViewById(R.id.button2);
        Intent intent =getIntent();
        final String nameV = intent.getStringExtra("name");
        final String pwV = intent.getStringExtra("pw");
        name.setText(nameV);
        pw.setText(pwV);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String backValue = name.getText().toString().trim()+"@"+pw.getText().toString().trim();
                Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
                intent.putExtra("backValue",backValue);
                setResult(200,intent);
                finish();
            }
        });
    }
}
