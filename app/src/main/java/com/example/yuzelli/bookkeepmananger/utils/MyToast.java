package com.example.yuzelli.bookkeepmananger.utils;

/**
 * Created by 51644 on 2017/5/16.
 */


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.app.MyApplication;


public class MyToast extends Toast{

    private static Toast mToast;

    public MyToast(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * 自定义Toast样式
     *
     * @description
     * @param context
     * @param resId
     * @param text
     * @param duration
     *            hrq 2014-7-10下午2:15:36
     */
    public static Toast makeText(Context context, int resId, CharSequence text,
                                 int duration) {
        Toast result = new Toast(context);

        // 获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.toast, null);

        // 实例化ImageView和TextView对象
        ImageView imageView = (ImageView) layout.findViewById(R.id.ImageView);
        TextView textView = (TextView) layout.findViewById(R.id.message);

        //这里我为了给大家展示就使用这个方面既能显示无图也能显示带图的toast
        if (resId == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(resId);
        }

        textView.setText(text);

        result.setView(layout);
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);

        return result;
    }

    public static void show(String content) {

        mToast =  MyToast.makeText(MyApplication.getContextObject(), 0, content, 100);
        mToast.show();
    }
    public static void show(String content,int drawableID) {

        mToast =  MyToast.makeText(MyApplication.getContextObject(),drawableID, content, 100);
        mToast.show();
    }

}
