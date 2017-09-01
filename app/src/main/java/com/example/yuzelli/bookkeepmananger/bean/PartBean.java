package com.example.yuzelli.bookkeepmananger.bean;

import java.util.ArrayList;

/**
 * Created by 51644 on 2017/9/7.
 */

public class PartBean {
    private String time;
    private String week;
    private String money;
    public ArrayList<BookKeepBean> bookKeepArrs = new ArrayList<>();



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
