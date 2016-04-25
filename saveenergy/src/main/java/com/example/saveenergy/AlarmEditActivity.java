package com.example.saveenergy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;

import com.example.Model.Alarm;
import com.example.Model.AlarmReciever;
import com.example.data.DatabaseOperator;
import com.example.data.SaveEnergyDataBaseHelper;
import com.example.tool.TimeTool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mc on 16-4-23.
 */

public class AlarmEditActivity extends Activity implements AdapterView.OnItemClickListener,View.OnClickListener{

    private String TAG="AlarmEditActivity";
    private ListView listView;
    private List<Map<String,String>> datalist;
    private Map<String,String> map;
    private SimpleAdapter simpleAdapter;

    Alarm alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_edit);
        alarm=new Alarm();
        datalist=new ArrayList<Map<String, String>>();

        ImageButton saveAlarm=(ImageButton)findViewById(R.id.save_alarm);
        saveAlarm.setOnClickListener(this);

        ImageButton cacelEditAlarm =(ImageButton)findViewById(R.id.cancel_edit_alarm);
        cacelEditAlarm.setOnClickListener(this);

        TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minuite) {
                Calendar time =alarm.getAlarmTime();

                time.set(Calendar.HOUR,hourOfDay);
                time.set(Calendar.MINUTE,minuite);
                time.set(Calendar.SECOND,0);
               /*if(time.before(Calendar.getInstance())){
                    time.add(Calendar.DAY_OF_MONTH,1);
                }*/
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        listView=(ListView)findViewById(R.id.alarm_edit_list_view);
        setDataList();
        simpleAdapter= new SimpleAdapter(this,datalist,R.layout.alarm_edit_item,new String[]{"name","value"},new int[]{R.id.switch_name,R.id.switch_value});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);

    }

    public  void setDataList(){
        datalist.clear();
        map = new HashMap<String, String>();
        map.put("name","目标开关");
        map.put("value","开关"+alarm.getWhitchSwitch());
        datalist.add(map);
        map = new HashMap<String, String>();
        map.put("name","开关需求");
        map.put("value",alarm.getSwitchStatus());
        datalist.add(map);

    }
    private void selectSwitchDialog(){
        String[] items=new String[]{"开关1","开关2","开关3","开关4"};
        AlertDialog.Builder dialog= new AlertDialog.Builder(this)
                .setTitle("开关选择")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarm.setWhitchSwitch(i);
                        setDataList();
                        simpleAdapter.notifyDataSetChanged();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog.create().show();
    }

    private  void selectstatusDialog(){
        String[] items=new String[]{"on","off"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this)
                .setTitle("定时开断")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:alarm.setSwitchStatus("on");break;
                            case 1:alarm.setSwitchStatus("off");break;
                        }
                        setDataList();
                        simpleAdapter.notifyDataSetChanged();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i){
            case 0:selectSwitchDialog();
                   break;
            case 1:selectstatusDialog();

        }
    }

    private void saveAlarm(){
        String time=TimeTool.turnDateToStringonlyTime(alarm.getAlarmTime().getTime());
        String logTime=TimeTool.turnDateToString(alarm.getAlarmTime().getTime());
        ContentValues values =new ContentValues(3);
        values.put(SaveEnergyDataBaseHelper.COL_SWTICH_NAME,"开关"+alarm.getWhitchSwitch());
        values.put(SaveEnergyDataBaseHelper.COL_SWITCH_STATUS,alarm.getSwitchStatus());
        values.put(SaveEnergyDataBaseHelper.COL_ALARM_TIME,time);
        DatabaseOperator dbOperator=new DatabaseOperator(this);
        dbOperator.insert(values);

        int id=dbOperator.getAlarmId(time);//获得新添加时钟在数据库的id
        Intent intent=new Intent(this, AlarmReciever.class);
        intent.putExtra("_id",id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,id,intent,0);

        Log.d(TAG,time);
        Log.d(TAG,logTime);
        Log.d(TAG,Long.toString(alarm.getAlarmTime().getTimeInMillis()));
        Log.d(TAG,Long.toString(Calendar.getInstance().getTimeInMillis()));


        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,alarm.getAlarmTime().getTimeInMillis(),pendingIntent);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_alarm:saveAlarm();finish();break;
            case R.id.cancel_edit_alarm:finish();break;
            default:break;
        }
    }


}
