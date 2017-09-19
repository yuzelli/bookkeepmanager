package com.example.yuzelli.bookkeepmananger.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.BellReminderBean;
import com.example.yuzelli.bookkeepmananger.bean.UserBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.CommonAdapter;
import com.example.yuzelli.bookkeepmananger.utils.DialogUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;
import com.example.yuzelli.bookkeepmananger.utils.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BellReminderActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_back)
    ImageView imgBlack;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected int layoutInit() {
        return R.layout.activity_bell_reminder;
    }

    @Override
    protected void binEvent() {
        tvTitle.setText("事件提醒");
        imgBlack.setVisibility(View.VISIBLE);
    }

    String types[] = {"信用卡还款", "发工资", "理财到期", "其他"};
   private ArrayList<BellReminderBean> bellArr;
    @Override
    protected void fillData() {
        initView();

    }

    private void initView() {
        UserBean u = (UserBean) SharePreferencesUtil.readObject(BellReminderActivity.this,ConstantsUtils.SP_LOGIN_USER_INFO);
        bellArr = (ArrayList<BellReminderBean>) SharePreferencesUtil.readObject(BellReminderActivity.this,u.getPhone()+ ConstantsUtils.BELL_REMINDER);
        if (bellArr==null) {
            bellArr = new ArrayList<BellReminderBean>();
        }


        lvList.setAdapter(new CommonAdapter<BellReminderBean>(BellReminderActivity.this, bellArr, R.layout.cell_bell) {
            @Override
            public void convert(ViewHolder helper, BellReminderBean item, final int position) {
                helper.setText(R.id.tv_time, item.getHour() + ":" + item.getMinute() + "");
                helper.setText(R.id.tv_type, types[item.getType()]);
                TextView tvChongfu = helper.getView(R.id.tv_chonfu);
                String type = item.getChonagfuType();
                if (type.equals("0")) {
                    tvChongfu.setText("每" + "周" + (Integer.valueOf(item.getContent()) + 1));
                } else if (type.equals("1")) {
                    tvChongfu.setText("每月" + (Integer.valueOf(item.getContent()) + 1) + "号");
                } else if (type.equals("2")) {
                    tvChongfu.setText("每年" + (Integer.valueOf(item.getContent2()) + 1) + "月" + (Integer.valueOf(item.getContent()) + 1) + "号");
                }
                ToggleButton toggleButton = helper.getView(R.id.toggleButton);
                toggleButton.setChecked(item.isOpean());
                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        bellArr.get(position).setOpean(b);

                        SharePreferencesUtil.saveObject(BellReminderActivity.this,ConstantsUtils.BELL_REMINDER,bellArr);
                    }
                });
            }
        });

        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int i, long l) {

                new DialogUtils(BellReminderActivity.this, R.layout.view_order_close_dialog) {
                    @Override
                    public void initLayout(ViewHelper helper, final Dialog dialog) {
                        helper.setViewClick(R.id.tv_cancel, new ViewHelper.ViewClickCallBack() {
                            @Override
                            public void doClickAction(View v) {
                                dialog.dismiss();
                            }
                        });
                        helper.setViewClick(R.id.img_cancel, new ViewHelper.ViewClickCallBack() {
                            @Override
                            public void doClickAction(View v) {
                                dialog.dismiss();
                            }
                        });
                        helper.setViewClick(R.id.tv_ok, new ViewHelper.ViewClickCallBack() {
                            @Override
                            public void doClickAction(View v) {
                                bellArr.remove(i);

                                SharePreferencesUtil.saveObject(BellReminderActivity.this, ConstantsUtils.BELL_REMINDER,bellArr);
                                initView();
                                dialog.dismiss();
                            }
                        });


                    }
                };


                return true;
            }
        });
    }


    @OnClick({R.id.img_back, R.id.add_bell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.add_bell:
                Intent intent = new Intent(BellReminderActivity.this, SaveBellReminderActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showDailog() {
        DialogUtils d = new DialogUtils(BellReminderActivity.this, R.layout.dailog_view) {
            @Override
            public void initLayout(ViewHelper helper, Dialog dialog) {

            }
        };
    }
}
