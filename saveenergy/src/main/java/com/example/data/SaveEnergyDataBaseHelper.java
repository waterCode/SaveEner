package com.example.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mc on 2016/3/30.
 */
public class SaveEnergyDataBaseHelper extends SQLiteOpenHelper{

    public final static String loginTabName="loginTab";

    public final static String CREATELOGINTABLE="Create table "+loginTabName+"("
            +"key "+"text not null"
            +"did"+"text not null"
            ;



    public SaveEnergyDataBaseHelper(Context aContext, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(aContext,dbName,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createLoginTable(SQLiteDatabase db){
        db.execSQL(CREATELOGINTABLE);
    }

}
