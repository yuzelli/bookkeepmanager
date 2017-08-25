package com.example.yuzelli.bookkeepmananger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 51644 on 2017/7/6.
 */

public class OtherUtils {


    /**
     * 验证电话号码是否符合格式
     * @return true （符合）or false（不符合）
     */
    public static boolean isPhoneEnable(String strPhone) {
        boolean b = false;
        if (strPhone.length() == 11) {
            Pattern pattern = null;
            Matcher matcher = null;
            pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
            matcher = pattern.matcher(strPhone);
            b = matcher.matches();
        }
        return b;
    }

    /**
     * 日期格式字符串转换成时间戳
     * @return
     */
    public static long date2TimeStamp(String date_str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String a = sdf.parse(date_str).getTime()/1000+"";
            return sdf.parse(date_str).getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /*
       * 将时间戳转换为时间
       */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String setShowCountDownText(int time) {
        StringBuffer buffer = new StringBuffer();
        int hour = 0;
        int feng = time / 60;
        int min = time % 60;
        if (feng>=60){
            hour = feng/60;
            feng = feng-hour*60;
            return buffer.append(hour + "小时").append(feng + "分").append(min + "秒").toString();
        }
        return buffer.append(feng + "分").append(min + "秒").toString();
    }
    public static String setShowCountDownText2(int time) {
        StringBuffer buffer = new StringBuffer();
        int hour = 0;
        int feng = time / 60;
        int min = time % 60;
        if (feng>=60){
            hour = feng/60;
            feng = feng-hour*60;
            return buffer.append(hour + "小时").append(feng + "分").toString();
        }
        return buffer.append(feng + "分").toString();
    }

}
