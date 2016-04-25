package com.example.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.data.DatabaseOperator;
import com.example.saveenergy.R;

/**
 * Created by mc on 16-4-24.
 */
public class AlarmReciever extends BroadcastReceiver {
    private String TAG="AlarmReciever";
    private Context mContext;
    private String content="开关1打开";
    Alarm alarm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"alarmReciever get");
        mContext=context;
        getAlarmInfo();
        sentNotification();


        int id =intent.getIntExtra("_id",-1);
        if (id!=-1){
            DatabaseOperator databaseOperator = new DatabaseOperator(context);
            databaseOperator.delete(id);
        }else {

        }
    }

    public void sentNotification(){
        NotificationManager notificationManager=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(mContext)
                .setContentTitle("定时开关通知")
                .setContentText(content)
                .setSmallIcon(R.mipmap.saveicon)
                .build();
        notificationManager.notify(0,notification);

    }

    private void getAlarmInfo(){
        alarm=new Alarm();

    }



}

