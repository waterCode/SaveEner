package com.example.Model;

import android.content.Context;

import com.example.data.MySharedPerferences;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mc on 16-4-17.
 */
public class Alarm {

    private Calendar time;
    private String switchStatus;
    private int whichSwitch;
    private String swicthName;

    public Alarm(){
        time= Calendar.getInstance();
        switchStatus="on";
        whichSwitch=1;//代表开关
        swicthName="开关1";
    }

    public void setSwitchStatus(String switchStatus) {
        this.switchStatus = switchStatus;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setWhitchSwitch(Context context,int whichSwitch) {
        MySharedPerferences dataFile = new MySharedPerferences(context,MySharedPerferences.DATASETTINGFILE);
        this.whichSwitch = whichSwitch;
        switch (whichSwitch){
            case 1:swicthName = dataFile.getString(MySharedPerferences.SWITCH_NAME1,"开关1");break;
            case 2:swicthName = dataFile.getString(MySharedPerferences.SWITCH_NAME2,"开关2");break;
            case 3:swicthName = dataFile.getString(MySharedPerferences.SWITCH_NAME3,"开关3");break;
            case 4:swicthName = dataFile.getString(MySharedPerferences.SWITCH_NAME4,"开关4");break;

        }
    }


    public String getSwicthName() {
        return swicthName;
    }

    public Calendar getAlarmTime() {
        return time;
    }

    public int getWhitchSwitch() {
        return whichSwitch;
    }

    public String getSwitchStatus(){
        return switchStatus;
    }
}
