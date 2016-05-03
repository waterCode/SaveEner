package com.example.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.data.DatabaseOperator;
import com.example.data.MySharedPerferences;
import com.example.http.HttpConnection;
import com.example.saveenergy.R;

/**
 * Created by mc on 16-4-24.
 */
public class AlarmReciever extends BroadcastReceiver {
    private String TAG="AlarmReciever";
    private Context mContext;
    DatabaseOperator databaseOperator;
    MySharedPerferences dataSettingFile;
    Alarm alarm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"alarmReciever get");
        mContext=context;
        int id =intent.getIntExtra("_id",-1);
        databaseOperator = new DatabaseOperator(context);
        alarm=databaseOperator.queryAlarmWithId(id);
        setMessToSwitch();
        sentNotification();//发送通知


        if (id!=-1){
            databaseOperator.delete(id);
        }else {

        }
    }


    private  void setMessToSwitch(){
        if (alarm.getSwitchStatus().equals("on")){
            setData(alarm.getWhitchSwitch(),true);
        }else {
            setData(alarm.getWhitchSwitch(),false);
        }
    }
    public void sentNotification(){
        NotificationManager notificationManager=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        String status;
        if ((alarm.getSwitchStatus().equals("on"))){
            status="已经打开";
        }else {
            status="已经关闭";
        }
        String content ="开关"+alarm.getWhitchSwitch()+status;
        Notification notification=new Notification.Builder(mContext)
                .setContentTitle("定时开关通知")
                .setContentText(content)
                .setSmallIcon(R.mipmap.saveicon)
                .build();
        notificationManager.notify(0,notification);

    }

    private void setData(int whichOne, boolean isChecked) {
        if (dataSettingFile == null) {
            dataSettingFile = new MySharedPerferences(mContext, MySharedPerferences.DATASETTINGFILE);
        }
        if (isChecked) {
            HttpConnection.controlSwitch(whichOne, "ON");
        } else {
            HttpConnection.controlSwitch(whichOne, "OFF");
        }
    }



}

