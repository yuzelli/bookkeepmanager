package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.BellReminderBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.DateUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveBellReminderActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tp_time)
    TimePicker tpTime;
    @BindView(R.id.tv_chongfu)
    TextView tvChongfu;
    @BindView(R.id.ll_chongfu)
    RelativeLayout llChongfu;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.spinner)
    Spinner spinner;

    private int hour;
    private int minute;

    @Override
    protected int layoutInit() {
        return R.layout.activity_save_bell_reminder;
    }

    @Override
    protected void binEvent() {
        tvTitle.setText("事件提醒");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
        DateUtils dateUtils = new DateUtils();
        hour = dateUtils.hour;
        minute = dateUtils.minute;

        tpTime.setCurrentHour(hour);
        tpTime.setCurrentMinute(minute);
        tpTime.setIs24HourView(true);
        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                 hour = timePicker.getCurrentHour();
                 minute = timePicker.getCurrentMinute();
            }
        });
        llChongfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveBellReminderActivity.this,
                        ChongfuActivity.class);
                startActivityForResult(intent, 1000);// requestCode

            }
        });

        spinner.setSelection(0);

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type==null||type.equals("")){
                    showToast("设置重复时间");
                    return;
                }

                ArrayList<BellReminderBean> bellArr = (ArrayList<BellReminderBean>) SharePreferencesUtil.readObject(SaveBellReminderActivity.this, ConstantsUtils.BELL_REMINDER);
                if (bellArr.equals("")){
                    bellArr = new ArrayList<BellReminderBean>();
                }
                BellReminderBean br = new BellReminderBean();
                br.setHour(hour);
                br.setMinute(minute);
                br.setChonagfuType(type);
                br.setContent(content);
                if (content2==null||content2.equals("")){
                    br.setContent2("");
                }else {
                    br.setContent2(content2);
                }
                br.setBeizhu(etInput.getText().toString().trim());
                br.setType(spinner.getSelectedItemPosition());
            }
        });

    }

    @Override
    protected void fillData() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    private String type;
    private String content;
    private String content2;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000&&resultCode==1001){
             type = data.getStringExtra("type");
            if (type.equals("0")){
                 content = data.getStringExtra("content");
                tvChongfu.setText("每"+"周"+(Integer.valueOf(content)+1));
            }else if (type.equals("1")){
                 content = data.getStringExtra("content");
                tvChongfu.setText("每月"+(Integer.valueOf(content)+1)+ "号");
            }else if (type.equals("2")){
                 content = data.getStringExtra("content");
                 content2 = data.getStringExtra("content2");
                tvChongfu.setText("每年"+(Integer.valueOf(content2)+1)+ "月"+(Integer.valueOf(content)+1)+ "号");
            }

        }

    }
}
