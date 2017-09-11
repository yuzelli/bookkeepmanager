package com.example.yuzelli.bookkeepmananger.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseFragment;
import com.example.yuzelli.bookkeepmananger.view.activity.BeiFengActivity;
import com.example.yuzelli.bookkeepmananger.view.activity.BellReminderActivity;
import com.example.yuzelli.bookkeepmananger.view.activity.ParitiesActivity;
import com.example.yuzelli.bookkeepmananger.view.activity.PieChartActivity;
import com.example.yuzelli.bookkeepmananger.view.activity.SaveBellReminderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/9/6.
 */

public class CommonUseFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int layoutInit() {
        return R.layout.fragment_common_use;
    }

    @Override
    protected void bindEvent(View v) {

    }

    @Override
    protected void fillData() {

    }


    @OnClick({ R.id.tv_tixing, R.id.tv_huansuan, R.id.tv_beifeng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tixing:
                Intent intent2  = new Intent(getActivity(), BellReminderActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_huansuan:
                Intent intent = new Intent(getActivity(), ParitiesActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_beifeng:
                Intent intent1 = new Intent(getActivity(), BeiFengActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
