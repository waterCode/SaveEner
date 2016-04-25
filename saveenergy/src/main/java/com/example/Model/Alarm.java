package com.example.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mc on 16-4-17.
 */
public class Alarm {

    private Calendar time;
    private String switchStatus;
    private int whitchSwitch;


    public Alarm(){
        time= Calendar.getInstance();
        switchStatus="on";
        whitchSwitch=1;//代表开关
    }

    public void setSwitchStatus(String switchStatus) {
        this.switchStatus = switchStatus;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setWhitchSwitch(int whitchSwitch) {
        this.whitchSwitch = whitchSwitch;
    }

    public Calendar getAlarmTime() {
        return time;
    }

    public int getWhitchSwitch() {
        return whitchSwitch;
    }

    public String getSwitchStatus(){
        return switchStatus;
    }
}
