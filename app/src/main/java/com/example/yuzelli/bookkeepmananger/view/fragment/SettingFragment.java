package com.example.yuzelli.bookkeepmananger.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseFragment;
import com.example.yuzelli.bookkeepmananger.view.activity.SetTypeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/9/6.
 */

public class SettingFragment extends BaseFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    @Override
    protected int layoutInit() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void bindEvent(View v) {

    }

    @Override
    protected void fillData() {

    }



    @OnClick({R.id.img_back, R.id.tv_changeUserInfo, R.id.tv_set_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.tv_changeUserInfo:
                break;
            case R.id.tv_set_type:
                Intent intent = new Intent(getActivity(), SetTypeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
