package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.Model.Alarm;

/**
 * Created by mc on 2016/3/30.
 */
public class DatabaseOperator {

   private SaveEnergyDataBaseHelper dbHelper;
    private SQLiteDatabase dbWriter;
    private Context mContext;



    public DatabaseOperator(Context aContext){
        dbHelper = SaveEnergyDataBaseHelper.getInstance(aContext);
        dbWriter=dbHelper.getWritableDatabase();
        mContext=aContext;
    }

    /*
    添加闹钟信息
     */
    public boolean insert(ContentValues values){
        dbWriter.insert(SaveEnergyDataBaseHelper.ALARM_TABLE,null,values);
        return true;
    }

/*
通过时间来获得存在数据库的闹钟id
 */
    public int getAlarmId(String time){
        String QUIRY_ALARM_ID="SELECT _id,"+SaveEnergyDataBaseHelper.COL_ALARM_TIME
                +" FROM "+SaveEnergyDataBaseHelper.ALARM_TABLE+" WHERE "
                +SaveEnergyDataBaseHelper.COL_ALARM_TIME+"= '"+time+"'";
        Cursor cursor = dbWriter.rawQuery(QUIRY_ALARM_ID,null);
        int id =-1;
        while(cursor.moveToNext()){
            id=cursor.getInt(0);
        }
        return id;
    }

    public  void delete(int id){
        String[] args = new String[]{String.valueOf(id)};
        dbWriter.delete(SaveEnergyDataBaseHelper.ALARM_TABLE,"_id=?",args);
    }


    public Alarm queryAlarmWithId(int id){
        Alarm alarm=new Alarm();
        String QUERY_ALARMINFO="SELECT * FROM "
                +SaveEnergyDataBaseHelper.ALARM_TABLE+" WHERE "
                +"_id='"+String.valueOf(id)+"'";
        Cursor cursor = dbWriter.rawQuery(QUERY_ALARMINFO,null);
        while (cursor.moveToNext()){
            alarm.setWhitchSwitch(mContext,Integer.parseInt(String.valueOf(cursor.getString(1).charAt(2))));
            alarm.setSwitchStatus(cursor.getString(3));
            return alarm;
        }
        return alarm;
    }
}
