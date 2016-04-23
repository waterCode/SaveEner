package com.example.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mc on 16-4-17.
 */
public class Alarm {

    private Date time;
    private String switchStatus;
    private int whitchSwitch;


    public Alarm(){
        time= Calendar.getInstance().getTime();
        switchStatus="on";
        whitchSwitch=1;
    }

    public void setSwitchStatus(String switchStatus) {
        this.switchStatus = switchStatus;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setWhitchSwitch(int whitchSwitch) {
        this.whitchSwitch = whitchSwitch;
    }

    public Date getTime() {
        return time;
    }

    public int getWhitchSwitch() {
        return whitchSwitch;
    }

    public String getSwitchStatus(){
        return switchStatus;
    }
}
