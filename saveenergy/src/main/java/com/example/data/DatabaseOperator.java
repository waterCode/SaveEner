package com.example.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mc on 2016/3/30.
 */
public class DatabaseOperator {

   private SaveEnergyDataBaseHelper dbHelper;
    private SQLiteDatabase dbWriter;
    private  SQLiteDatabase dbReader;


    public DatabaseOperator(Context aContext, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        dbHelper = new SaveEnergyDataBaseHelper(aContext,"mydb",factory,version);
        dbWriter=dbHelper.getWritableDatabase();
        dbReader=dbHelper.getWritableDatabase();
    }



}
