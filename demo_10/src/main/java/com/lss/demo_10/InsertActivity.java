package com.lss.demo_10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private ImageView image;
    private EditText name;
    private EditText number;
    private Button choose;
    private Button insert;
    private String imageName;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;//和URL类似 统一资源标识符
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        image = (ImageView) findViewById(R.id.image);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        choose = (Button) findViewById(R.id.choose);
        insert = (Button) findViewById(R.id.insert);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nameV = name.getText().toString();
                 String numberV = number.getText().toString();
                if("".equals(nameV)||"".equals(numberV)){
                    Toast.makeText(InsertActivity.this, "请输入姓名或电话号码！", Toast.LENGTH_SHORT).show();
                }else if(numberV.length()!=11||!numberV.matches("[1][3578]\\d{9}")){
                    Toast.makeText(InsertActivity.this, "请正确输入11位电话号码！", Toast.LENGTH_SHORT).show();
                }else{
                    OpenSqlite1 os = new OpenSqlite1(InsertActivity.this);
                    SQLiteDatabase sqLiteDatabase = os.getReadableDatabase();
                    Log.i("imagename",imageName);
                    sqLiteDatabase.execSQL("insert into contacter (name,number,image,xing) values ('"+nameV+"','"+numberV+"','"+imageName+"','否')");
                    Toast.makeText(InsertActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InsertActivity.this,ShouyeActivity.class);
                    startActivity(intent);
                }


            }
        });


    }
    //实现弹出窗口选择照片来源
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;

                }
            }
        });
        builder.create().show();
    }
    //接收来源得到并显示图片
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    //此时代表所得到的图片为拍照得来的
                    startPhotoZoom(data.getData()); // 用得到的图片的uri进行裁剪
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    //十字框裁剪图片
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //裁剪完成保存图片到ImageView中圆形显示
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            image.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        Log.i("文件路径1","@@@@@@"+Environment.getExternalStorageDirectory().getAbsolutePath());
        String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("文件路径2", "@@@@@@@"+imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了


            imageName=imagePath;
            Log.i("imageName1",imagePath);

        }
    }
}
