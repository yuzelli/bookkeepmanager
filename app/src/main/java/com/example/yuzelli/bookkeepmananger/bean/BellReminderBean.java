package com.example.yuzelli.bookkeepmananger.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/9/12.
 */

public class BellReminderBean implements Serializable {
    private int hour;
    private int minute;
    private String chonagfuType;
    private String content;
    private String content2;
    private String beizhu;
    private int type;
    private boolean opean = true;

    public boolean isOpean() {
        return opean;
    }

    public void setOpean(boolean opean) {
        this.opean = opean;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getChonagfuType() {
        return chonagfuType;
    }

    public void setChonagfuType(String chonagfuType) {
        this.chonagfuType = chonagfuType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
