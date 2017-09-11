package com.example.yuzelli.bookkeepmananger.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BellReminderActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected int layoutInit() {
        return R.layout.activity_bell_reminder;
    }

    @Override
    protected void binEvent() {
       tvTitle.setText("事件提醒");
    }

    @Override
    protected void fillData() {

    }


    @OnClick({R.id.img_back, R.id.add_bell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.add_bell:
                Intent intent = new Intent(BellReminderActivity.this,SaveBellReminderActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showDailog() {
        DialogUtils d = new DialogUtils(BellReminderActivity.this,R.layout.dailog_view) {
            @Override
            public void initLayout(ViewHelper helper, Dialog dialog) {

            }
        };
    }
}
