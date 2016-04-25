package com.example.saveenergy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.MySharedPerferences;
import com.example.data.WechatShare;
import com.example.tool.TimeTool;

import java.text.DecimalFormat;
import java.util.Date;


/**
 * Created by mc on 2016/4/3.
 */
public class EnergyCalculateActivity extends AppCompatActivity {

    private TextView tvHour1,tvHour2,tvHour3,tvHour4,tvAllEnergy;
    private MySharedPerferences dataSettingFile;
    private final String TAG="EnergyCalculateActivity";
    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:tvHour1.setText(TimeTool.turnMiuTimeToString((long)msg.obj));
                        break;
                case 2:tvHour2.setText(TimeTool.turnMiuTimeToString((long)msg.obj));
                    break;
                case 3:tvHour3.setText(TimeTool.turnMiuTimeToString((long)msg.obj));
                    break;
                case 4:tvHour4.setText(TimeTool.turnMiuTimeToString((long)msg.obj));
                    break;
                case 5:
                    DecimalFormat decimalFormat= new DecimalFormat(".00");
                    tvAllEnergy.setText(decimalFormat.format((float) msg.obj)+"KW/h");
                    break;
                default:break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.energy_calculate);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tvHour1=(TextView)findViewById(R.id.hourofswitch_one);
        tvHour2=(TextView)findViewById(R.id.hourofswitch_two);
        tvHour3=(TextView)findViewById(R.id.hourofswitch_three);
        tvHour4=(TextView)findViewById(R.id.hourofswitch_four);
        tvAllEnergy=(TextView)findViewById(R.id.allEnergy);

        setSupportActionBar(toolbar);//??

        dataSettingFile=new MySharedPerferences(this,MySharedPerferences.DATASETTINGFILE);
    }

    @Override
    protected void onResume() {
        updateTime();
        super.onResume();
    }


    public void updateTime(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                float allEnergy=dataSettingFile.getFloat(MySharedPerferences.ALLENERGY,0);
                Date nowTime_Date=new Date(System.currentTimeMillis());//获得当前时间用Date格式和String格式
                String nowTime_String= TimeTool.turnDateToString(nowTime_Date);
                Log.d(TAG,"现在的时间为"+nowTime_String);
                if(dataSettingFile.getBoolean(MySharedPerferences.BUTTON1,false)){//如果开关是打开的就更新时间
                    String onTime_String=dataSettingFile.getString(MySharedPerferences.BUTTON1_ON_TIME, nowTime_String);//获取打开开关的时间两种格式
                    Date onTime_Date=TimeTool.turnStringToDate(onTime_String);
                    Log.d(TAG,"button1上次打开开关的时间"+onTime_String);
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_FIRST_HOUR, 0);//获得之前积累的时间
                    Log.d(TAG,"原先的时间"+Long.toString(beforeTime));
                    float power1=dataSettingFile.getFloat(MySharedPerferences.power1,0);
                    long diffTime=TimeTool.getDiffTime(onTime_Date, nowTime_Date);//获得最终时间累积
                    allEnergy+=(power1*(diffTime/3600000/1000));
                    long allTime=diffTime+beforeTime;
                    setMyText(1,allTime);
                    dataSettingFile.setString(MySharedPerferences.BUTTON1_ON_TIME, nowTime_String);
                    dataSettingFile.setLong(MySharedPerferences.SWITCH_FIRST_HOUR,allTime);
                }else{
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_FIRST_HOUR, 0);//获得之前积累的时间
                    setMyText(1,beforeTime);
                }

                if(dataSettingFile.getBoolean(MySharedPerferences.BUTTON2,false)){//如果开关是打开的就更新时间
                    String onTime_String=dataSettingFile.getString(MySharedPerferences.BUTTON2_ON_TIME, nowTime_String);//获取打开开关的时间两种格式
                    Date onTime_Date=TimeTool.turnStringToDate(onTime_String);
                    Log.d(TAG,"button2上次打开开关的时间"+onTime_String);
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_SECOND_HOUR, 0);//获得之前积累的时间
                    Log.d(TAG, "原先的时间" + Long.toString(beforeTime));
                    float power1=dataSettingFile.getFloat(MySharedPerferences.power2,0);
                    long diffTime=TimeTool.getDiffTime(onTime_Date, nowTime_Date);//获得最终时间累积
                    allEnergy+=(power1*(diffTime/3600000/1000));
                    Log.d(TAG,""+allEnergy);
                    long allTime=diffTime+beforeTime;
                    setMyText(2,allTime);
                    dataSettingFile.setString(MySharedPerferences.BUTTON2_ON_TIME, nowTime_String);
                    dataSettingFile.setLong(MySharedPerferences.SWITCH_SECOND_HOUR,allTime);
                }else {
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_SECOND_HOUR, 0);//获得之前积累的时间
                    setMyText(2,beforeTime);
                }

                if(dataSettingFile.getBoolean(MySharedPerferences.BUTTON3,false)){//如果开关是打开的就更新时间
                    String onTime_String=dataSettingFile.getString(MySharedPerferences.BUTTON3_ON_TIME, nowTime_String);//获取打开开关的时间两种格式
                    Date onTime_Date=TimeTool.turnStringToDate(onTime_String);
                    Log.d(TAG,"button3上次打开开关的时间"+onTime_String);
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_THIRD_HOUR, 0);//获得之前积累的时间
                    Log.d(TAG,"原先的时间"+Long.toString(beforeTime));
                    float power1=dataSettingFile.getFloat(MySharedPerferences.power3,0);
                    long diffTime=TimeTool.getDiffTime(onTime_Date, nowTime_Date);//获得最终时间累积
                    allEnergy+=(power1*(diffTime/3600000/1000));
                    Log.d(TAG,""+allEnergy);
                    long allTime=diffTime+beforeTime;
                    setMyText(3,allTime);
                    dataSettingFile.setString(MySharedPerferences.BUTTON3_ON_TIME, nowTime_String);
                    dataSettingFile.setLong(MySharedPerferences.SWITCH_THIRD_HOUR,allTime);
                }else{
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_THIRD_HOUR, 0);//获得之前积累的时间
                    setMyText(3,beforeTime);
                }


                if(dataSettingFile.getBoolean(MySharedPerferences.BUTTON4,false)){//如果开关是打开的就更新时间
                    String onTime_String=dataSettingFile.getString(MySharedPerferences.BUTTON4_ON_TIME, nowTime_String);//获取打开开关的时间两种格式
                    Date onTime_Date=TimeTool.turnStringToDate(onTime_String);
                    Log.d(TAG, "button4上次打开开关的时间"+onTime_String);
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_FOURTH_HOUR, 0);//获得之前积累的时间
                    Log.d(TAG, "原先的时间" + Long.toString(beforeTime));
                    float power1=dataSettingFile.getFloat(MySharedPerferences.power1,0);
                    long diffTime=TimeTool.getDiffTime(onTime_Date, nowTime_Date);//获得最终时间累积
                    allEnergy+=(power1*(diffTime/3600000/1000));
                    Log.d(TAG,""+allEnergy);
                    long allTime=diffTime+beforeTime;
                    setMyText(4,allTime);
                    dataSettingFile.setString(MySharedPerferences.BUTTON4_ON_TIME, nowTime_String);
                    dataSettingFile.setLong(MySharedPerferences.SWITCH_FOURTH_HOUR,allTime);
                }else {
                    long beforeTime=dataSettingFile.getLong(MySharedPerferences.SWITCH_FOURTH_HOUR, 0);//获得之前积累的时间
                    setMyText(4,beforeTime);
                }

                    /*setAllEnergyText(5,allEnergy);
                    dataSettingFile.setFloat(MySharedPerferences.ALLENERGY,allEnergy);*/
            }
        }).start();
    }

    public void setAllEnergyText(int where,float allEnergy){
        Message message=new Message();
        message.what=where;   message.obj=allEnergy;
        handler.sendMessage(message);
    }
   public void setMyText(int where,long diffTime){
       Message message=new Message();
       message.what=where;   message.obj=diffTime;
       Log.d(TAG,"总时间"+Long.toString(diffTime));
       handler.sendMessage(message);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.energy_show_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.energy_setting:
                Intent intent = new Intent(this, EditPowerActivity.class);
                startActivity(intent);
                break;
            case R.id.share:
                Bitmap bmp=WechatShare.myShot(this);
                WechatShare.shareWithPhoto(this,bmp);
                break;
            case R.id.set_time_zero:setTimeZero();
                break;
            case R.id.set_energy_zero:setEnergyZero();
                break;
            default:break;
        }
        return true;
    }

    public void setTimeZero(){

        dataSettingFile.setLong(MySharedPerferences.SWITCH_FIRST_HOUR,0);
        dataSettingFile.setLong(MySharedPerferences.SWITCH_SECOND_HOUR,0);
        dataSettingFile.setLong(MySharedPerferences.SWITCH_THIRD_HOUR,0);
        dataSettingFile.setLong(MySharedPerferences.SWITCH_FOURTH_HOUR,0);
        setMyText(1,0);
        setMyText(2,0);
        setMyText(3,0);
        setMyText(4,0);
    }

    public void setEnergyZero(){

        setAllEnergyText(5,0);
        dataSettingFile.setFloat(MySharedPerferences.ALLENERGY,0);
    }



}
