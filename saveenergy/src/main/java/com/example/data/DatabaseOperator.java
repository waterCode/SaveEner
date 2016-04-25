package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mc on 2016/3/30.
 */
public class DatabaseOperator {

   private SaveEnergyDataBaseHelper dbHelper;
    private SQLiteDatabase dbWriter;
    private  SQLiteDatabase dbReader;


    public DatabaseOperator(Context aContext){
        dbHelper = SaveEnergyDataBaseHelper.getInstance(aContext);
        dbWriter=dbHelper.getWritableDatabase();
        dbReader=dbHelper.getWritableDatabase();
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
        String QUIRY_ALARM_ID="SELECT *"
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


}
