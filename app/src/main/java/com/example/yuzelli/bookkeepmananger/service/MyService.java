package com.example.yuzelli.bookkeepmananger.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.bean.BellReminderBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.DateUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;
import com.example.yuzelli.bookkeepmananger.view.activity.BellReminderActivity;

import java.util.ArrayList;

/**
 * Created by 51644 on 2017/9/12.
 */

public class MyService extends Service {

private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 101010:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60*1000);
                            doNotif();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
};
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler.sendEmptyMessage(101010);

    }

    private void doNotif() {
        handler.sendEmptyMessage(101010);
        ArrayList<BellReminderBean> array = (ArrayList<BellReminderBean>) SharePreferencesUtil.readObject(getApplicationContext(), ConstantsUtils.BELL_REMINDER);
        if(array==null){
            array = new ArrayList<>();
        }
        DateUtils d = new DateUtils();

        for (BellReminderBean b :array){
            if (b.getChonagfuType().equals("0")){
                if (d.week==Integer.valueOf(b.getContent())+1){
                    if (d.hour==b.getHour()&&d.minute==b.getMinute()){
                        showCont(b);
                    }
                }
            }else if (b.getChonagfuType().equals("1")){
                if (d.day==Integer.valueOf(b.getContent())){
                    if (d.hour==b.getHour()&&d.minute==b.getMinute()){
                        showCont(b);
                    }
                }

            }else if (b.getChonagfuType().equals("2")){
                if (d.month==Integer.valueOf(b.getContent2())&&d.day==Integer.valueOf(b.getContent())){
                    if (d.hour==b.getHour()&&d.minute==b.getMinute()){
                        showCont(b);
                    }
                }
            }
        }

    }
    String types[] = {"信用卡还款", "发工资", "理财到期", "其他"};
    private void showCont(BellReminderBean b) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,BellReminderActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setTicker(types[b.getType()])  //设置顶部出现文字
                .setContentTitle(types[b.getType()]) //设置下拉后通知标题
                .setContentText(b.getBeizhu()) //设置下拉后出现的内容
                .setSmallIcon(R.mipmap.ic_app)
                .setContentIntent(pi) //设置点击跳转
                .build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        manager.notify(0,notification);

    }
}
