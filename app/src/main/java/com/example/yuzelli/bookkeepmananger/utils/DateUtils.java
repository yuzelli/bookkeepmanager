package com.example.yuzelli.bookkeepmananger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 51644 on 2017/7/5.
 */

public class DateUtils {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    /**
     * 获取当前时间的年月日时分秒
     * @return
     */

    private static DateUtils initDateUtils = null;

    public DateUtils() {
       initDate();
    }

    private  void initDate() {
        Calendar c = Calendar.getInstance();
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH) + 1;
         day = c.get(Calendar.DAY_OF_MONTH);
         hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE);
         second = c.get(Calendar.SECOND);
    }

    public  String  getSummaryBeiginDate(){

        return  year+"-"+String.format("%02d", month)+"-"+String.format("%02d", day)+" "+"00:00";
    }
      public  String  getSummaryEndDate(){
        return  year+"-"+String.format("%02d", month)+"-"+String.format("%02d", day)+" "+String.format("%02d", hour)+":"+String.format("%02d", minute);
    }

    /**
     * 日期格式字符串转换成时间戳
     * @return
     */
    public static long date2TimeStamp(String date_str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(date_str).getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断开始结束时间
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean judgeDateLegitimate(String beginTime, String endTime) {
        if (beginTime.equals("")||endTime.equals("")){
            return true;
        }

        if (beginTime.equals("") || beginTime == null) {
            return false;
        }
        if (endTime.equals("") || endTime == null) {
            return false;
        }
        if (Integer.valueOf(beginTime.substring(0, 4)) > Integer.valueOf(endTime.substring(0, 4))) {
            return false;
        }
        if (Integer.valueOf(beginTime.substring(5, 7).replaceFirst("^(0+)", "")) > Integer.valueOf(endTime.substring(5, 7).replaceFirst("^0*", ""))) {
            return false;
        }
        if (Integer.valueOf(beginTime.substring(8, 10).replaceFirst("^(0+)", "")) > Integer.valueOf(endTime.substring(8, 10).replaceFirst("^0*", ""))) {
            return false;
        }
        if (Integer.valueOf(beginTime.substring(11, 13).replaceFirst("^(0+)", "")) > Integer.valueOf(endTime.substring(11, 13).replaceFirst("^0*", ""))) {
            return false;
        }
        if (Integer.valueOf(beginTime.substring(14, 16).replaceFirst("^(0+)", "")) > Integer.valueOf(endTime.substring(14, 16).replaceFirst("^0*", ""))) {
            return false;
        }
        return true;
    }


}
