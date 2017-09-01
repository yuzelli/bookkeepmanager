package com.example.yuzelli.bookkeepmananger.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/9/6.
 */

public class BookKeepBean implements Serializable {
    private int id ;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String min;
    private String week;
    private int type_id;
    private int isZhiCu;
    private String comment;
    private String money;
    private long time ;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getIsZhiCu() {
        return isZhiCu;
    }

    public void setIsZhiCu(int isZhiCu) {
        this.isZhiCu = isZhiCu;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
