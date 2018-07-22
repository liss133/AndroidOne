package com.lss.demo_06;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button alertdialog,timePikerDialog,datePikerDialog;
    private String data[]={"长春市","四平市","吉林市"};
    private Boolean bol [] = {true,false,false};
    private DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){

                case -1:
                    Toast.makeText(MainActivity.this, "喜欢", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(MainActivity.this, "不喜欢", Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(MainActivity.this, "一般", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertdialog = (Button) findViewById(R.id.alertdialog);
        timePikerDialog = (Button) findViewById(R.id.timePikerDialog);
        datePikerDialog = (Button) findViewById(R.id.datePikerDialog);
        alertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("消息提示")
                        /*.setMessage("您喜欢听音乐么?")
                        .setPositiveButton("喜欢",clickListener)
                        .setNegativeButton("不喜欢",clickListener)
                        .setNeutralButton("一般",clickListener)*/
                       /* .setView(R.layout.login)*/
                        //单选
                        /*.setSingleChoiceItems(data, 0, new DialogInterface.OnClickListener() {//0的意思是第0个被选中
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, data[which]+"", Toast.LENGTH_SHORT).show();
                            }
                        })*/
                        //多选
                        .setMultiChoiceItems(data, new boolean[]{true, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(MainActivity.this, data[which] + "", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //show之前一定要把扩展都写好
                        .show();

            }
        });

        datePikerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用java日历类获取时间
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String sj = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        Toast.makeText(MainActivity.this, sj, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                dpd.show();//不写show是不会显示的


            }
        });
        timePikerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog time = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String sj = hourOfDay + ":" + minute;
                        Toast.makeText(MainActivity.this, sj, Toast.LENGTH_SHORT).show();
                    }
                }, hour+8, minute, true);
                time.show();
            }
        });

    }
}
