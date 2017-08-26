package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_ok_password)
    EditText etOkPassword;
    @BindView(R.id.btn_ver)
    Button btnVer;
    private String phone;

    @Override
    protected int layoutInit() {
        return R.layout.activity_change_pass;
    }

    @Override
    protected void binEvent() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

    }

    @Override
    protected void fillData() {

    }

    public static void startAction(Context context, String phone) {
        Intent intent = new Intent(context, ChangePassActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @OnClick({R.id.img_back, R.id.btn_ver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_ver:
                break;
        }
    }
}
