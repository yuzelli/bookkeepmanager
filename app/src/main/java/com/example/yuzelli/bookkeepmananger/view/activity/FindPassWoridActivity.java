package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.utils.OtherUtils;
import com.example.yuzelli.bookkeepmananger.widgets.MyPorter;

import java.util.IllegalFormatFlagsException;

import butterknife.BindView;
import butterknife.OnClick;

public class FindPassWoridActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_ver_password)
    EditText etVerPassword;
    @BindView(R.id.img_ver)
    MyPorter imgVer;
    @BindView(R.id.img_refresh)
    ImageView img_refresh;
    @BindView(R.id.btn_ver)
    Button btn_ver;
    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void binEvent() {
        context = this;
        imgBack.setVisibility(View.VISIBLE);
        tvTitle.setText("找回密码");
    }

    @Override
    protected void fillData() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, FindPassWoridActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.img_back, R.id.btn_ver, R.id.img_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_refresh:
                imgVer.refreshCode();
                break;
            case R.id.btn_ver:
                doFindAction();
                break;
        }
    }

    private void doFindAction() {
        String phone = etPhone.getText().toString().trim();
        String ver = etVerPassword.getText().toString().trim();
        if (phone.equals("") || ver.equals("")) {
            showToast("请输入手机号、验证码");
            return;
        }
        if (!OtherUtils.isPhoneEnable(phone)) {
            showToast("手机号输入不合法");
            return;
        }

        if (MyPorter.verification().equals(ver)) {
            ChangePassActivity.startAction(context, phone);
            finish();
        } else {
            showToast("验证码错误");
        }
    }
}
