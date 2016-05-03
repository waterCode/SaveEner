package com.example.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mc on 2016/3/30.
 */
public class SaveEnergyDataBaseHelper extends SQLiteOpenHelper{

    public static SaveEnergyDataBaseHelper helper;

    public final static String ALARM_TABLE="alarmTabble";
    public final static String DbName="save.db";
    public final static String COL_ALARM_TIME="alarm_time";
    public final static String COL_SWTICH_NAME="swtich_name";
    public final static String COL_SWITCH_STATUS="switch_staus";
    public final static String COL_ANOTHER_NAME="another_name";

    public final static String CREATE_ALARM_TABLE="CREATE TABLE "+ALARM_TABLE
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_SWTICH_NAME+" TEXT NOT NULL, "
            +COL_ALARM_TIME+" TEXT NOT NULL,"
            +COL_SWITCH_STATUS+" INTEGER);"
            ;



    private SaveEnergyDataBaseHelper(Context aContext, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(aContext,dbName,factory,version);
    }


    public static synchronized SaveEnergyDataBaseHelper getInstance(Context context) {
        if (helper == null) {
            helper = new SaveEnergyDataBaseHelper(context, DbName, null, 2);
        }
        return helper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createLoginTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<=1){
            db.execSQL("ALTER TABLE "+ALARM_TABLE+" ADD COLUMN "+COL_ANOTHER_NAME+" TEXT ");
        }
    }

    private void createLoginTable(SQLiteDatabase db){

        db.execSQL(CREATE_ALARM_TABLE);
    }

}
