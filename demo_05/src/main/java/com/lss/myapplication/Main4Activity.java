package com.lss.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {
    private Button alphaButton,scaleButton,rotateButton,translateButton;
    private ImageView photo;
    //定义动画实例对象
    private Animation myAnimation_Alpha;
    private Animation myAnimation_Scale;
    private Animation myAnimation_Translate;
    private Animation myAnimation_Rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        alphaButton= (Button) findViewById(R.id.alphaButton);
        scaleButton= (Button) findViewById(R.id.scaleButton);
        rotateButton= (Button) findViewById(R.id.rotateButton);
        translateButton= (Button) findViewById(R.id.translateButton);
        photo = (ImageView) findViewById(R.id.photo);
        //从完全透明0.0到完全不透明1.0
        myAnimation_Alpha=new AlphaAnimation(0.0f, 1.0f);
        //持续时间1秒
        myAnimation_Alpha.setDuration(2000);
        //第一个参数 开始时x的伸缩尺寸 第二个参数为结束时x的伸缩尺寸
        //第三个参数 开始时y的伸缩尺寸  第四个参数为结束时y的伸缩尺寸
        myAnimation_Scale =new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        myAnimation_Scale.setDuration(2000);

        //第一个参数 起始时x坐标的移动位置  第二个参数 结束时x坐标的移动位置
        //第三个参数 起始时y坐标的移动位置  第四个参数 结束时y坐标的移动位置
        myAnimation_Translate=new TranslateAnimation(30.0f, -80.0f, 30.0f, 300.0f);
        myAnimation_Translate.setDuration(2000);
        //第一个参数fromDegrees为动画起始时的旋转角度
        //第二个参数toDegrees为动画旋转到的角度
        //第三个参数pivotXType为动画在X轴相对于物件位置类型
        //第四个参数pivotXValue为动画相对于物件的X坐标的开始位置
        //第五个参数pivotYType为动画在Y轴相对于物件位置类型
        //第六个参数pivotYValue为动画相对于物件的Y坐标的开始位置
        myAnimation_Rotate=new RotateAnimation(0.0f, +350.0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

        alphaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation_Alpha= AnimationUtils.loadAnimation(Main4Activity.this,R.anim.alpha);
                photo.startAnimation(myAnimation_Alpha);
            }
        });
        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation_Scale=AnimationUtils.loadAnimation(Main4Activity.this,R.anim.scale);
                photo.startAnimation(myAnimation_Scale);
            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation_Translate=AnimationUtils.loadAnimation(Main4Activity.this,R.anim.translate);
                photo.startAnimation(myAnimation_Translate);
            }
        });
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation_Rotate=AnimationUtils.loadAnimation(Main4Activity.this,R.anim.rotate);
                photo.startAnimation(myAnimation_Rotate);
            }
        });
    }
}
