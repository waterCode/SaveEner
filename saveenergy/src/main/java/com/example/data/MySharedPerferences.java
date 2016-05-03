package com.example.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by mc on 2016/3/30.
 */
public class MySharedPerferences {

    private  Context mContext;
    private  SharedPreferences spre;
    private  SharedPreferences.Editor editor;

    public static final String LAST_COUNT="lastCounts";

    public static final String power1="power1";
    public static final String power2="power2";
    public static final String power3="power3";
    public static final String power4="power4";

    public static final String ALLENERGY="all_energy";
    public static final String SWITCH_FIRST_HOUR="switch_first_hour";//纪律打开时间
    public static final String SWITCH_SECOND_HOUR="switch_second_hour";
    public static final String SWITCH_THIRD_HOUR="switch_third_hour";
    public static final String SWITCH_FOURTH_HOUR="switch_four_hour";

    public static final String BUTTON1="button1";
    public static final String BUTTON2="button2";
    public static final String BUTTON3="button3";
    public static final String BUTTON4="button4";

    public static final String LASTLOGIN ="LAST_LOGIN_FILE";//两个file
    public static final String DATASETTINGFILE="DATA_SETTING_FILE";

    public static final String BUTTON1_ON_TIME="BUTTON1_ON_TIME";
    public static final String BUTTON2_ON_TIME="BUTTON2_ON_TIME";
    public static final String BUTTON3_ON_TIME="BUTTON3_ON_TIME";
    public static final String BUTTON4_ON_TIME="BUTTON4_ON_TIME";


    public static final String SWITCH_NAME1="SWITCH_NAME1";
    public static final String SWITCH_NAME2="SWITCH_NAME2";
    public static final String SWITCH_NAME3="SWITCH_NAME3";
    public static final String SWITCH_NAME4="SWITCH_NAME4";

    public MySharedPerferences(Context aContext,String name){
        spre =aContext.getSharedPreferences(name,Context.MODE_PRIVATE);
        editor=spre.edit();

    }


    public Map<String, ?> getALl(){
        return spre.getAll();
    }


    public int getAllSize(){
        return spre.getAll().size();
    }



    public String getString(String key, String defValue) {
        return spre.getString(key, defValue);
    }

    public void setString(String key,String defValue){
        editor.putString(key,defValue);
        editor.commit();
    }

    public void setLong(String key,long defValue){
        editor.putLong(key, defValue);
        editor.commit();
    }
    public void setBoolean(String key,boolean defValue){
        editor.putBoolean(key, defValue);
        editor.commit();
    }
    public void setFloat(String key,float defValue){
        editor.putFloat(key, defValue);
        editor.commit();
    }
    public  void setInt(String key,int defValue){
        editor.putInt(key, defValue);
        editor.commit();
    }
    public  boolean getBoolean(String key,boolean defValue){
        return spre.getBoolean(key,defValue);
    }

    public float getFloat(String key,float defValue){
        return spre.getFloat(key,defValue);
    }

    public int getInt(String key, int defValue) {
        return  spre.getInt(key, -1);
    }


    public long getLong(String key, long defValue) {
        return spre.getLong(key, defValue);
    }




    void putString(String key,String defValue){
        editor.putString(key,defValue);
        editor.commit();
    }



    public boolean contains(String key) {
        return false;
    }




}
