package com.lss.demo_10;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class DetailActivity extends AppCompatActivity {
    private ImageView call;
    private ImageView message;
    private TextView name2;
    private TextView number2;
    private ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name2 = (TextView) findViewById(R.id.name2);
        number2 = (TextView) findViewById(R.id.number2);
        image2 = (ImageView) findViewById(R.id.image2);
        call= (ImageView) findViewById(R.id.call);
        message= (ImageView) findViewById(R.id.message);
        Intent intent = getIntent();
        String code = intent.getStringExtra("code") ;
        OpenSqlite1 os = new OpenSqlite1(DetailActivity.this);
        SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from contacter where code = '"+Integer.parseInt(code)+"'",null);
        while(cursor.moveToNext()){

            name2.setText(cursor.getString(cursor.getColumnIndex("name")));
            number2.setText(cursor.getString(cursor.getColumnIndex("number")));
            String imageName = cursor.getString(cursor.getColumnIndex("image"));
            File file = new File(imageName);
            if(file.exists()){
                Bitmap bitmap= BitmapFactory.decodeFile(imageName);
                image2.setImageBitmap(bitmap);
            }
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number2.getText().toString()));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(DetailActivity.this, "1123", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+number2.getText().toString()));
                intent.putExtra("sms_body","");
                startActivity(intent);
            }
        });
    }
}
