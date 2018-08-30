package com.yinglan.scg.entity;


/**
 * Created by Admin on 2017/9/16.
 */

public class CalendarControlDateBean {

    private boolean flag;

    private String dateStr;

    private long dateTime;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
