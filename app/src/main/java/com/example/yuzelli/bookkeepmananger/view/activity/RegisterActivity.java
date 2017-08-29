package com.example.yuzelli.bookkeepmananger.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.UserBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.https.OkHttpClientManager;
import com.example.yuzelli.bookkeepmananger.utils.OtherUtils;
import com.example.yuzelli.bookkeepmananger.widgets.MyPorter;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_refresh)
    ImageView img_refresh;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_contry)
    TextView tvContry;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_ok_password)
    EditText etOkPassword;
    @BindView(R.id.et_version)
    EditText et_version;
    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.myPorter)
    MyPorter myPorter;

    private RegisterHandler handler;
    private UserBean userInfo;
    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {
        handler = new RegisterHandler();
        context = this;
    }

    @Override
    protected void fillData() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.img_back, R.id.btn_register, R.id.rl_contry, R.id.img_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_refresh:
                myPorter.refreshCode();
                break;
            case R.id.btn_register:
                if (version())
                    doRegister();
                break;
            case R.id.rl_contry:
                Intent intent = new Intent(RegisterActivity.this, ContryActivity.class);
                startActivityForResult(intent, 1000);
                break;
        }
    }

    private boolean version() {

        String phone = etPhone.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        String okpass = etOkPassword.getText().toString().trim();
        String version = et_version.getText().toString().trim();
        if (phone.equals("")) {
            showToast("请输入手机号");
            return false;
        }
        if (pass.equals("")) {
            showToast("请输入密码");
            return false;
        }
        if (okpass.equals("")) {
            showToast("请再次输入密码");
            return false;
        }
        if (version.equals("")) {
            showToast("请输入验证码");
            return false;
        }

        if (!OtherUtils.isPhoneEnable(phone)) {
            showToast("请输入合法手机号");
            return false;
        }
        if (!pass.equals(okpass)) {
            showToast("两次密码输入不一致");
            return false;
        }
        if (!MyPorter.verification().equals(version)){
            showToast("验证码错误");
            return false;
        }
        return true;
    }

    private void doRegister() {
        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("type", "register");
        map.put("phone", etPhone.getText().toString().trim());
        map.put("passWord", etPassword.getText().toString().trim());
        map.put("name", "");
        String url = OkHttpClientManager.attachHttpGetParams(ConstantsUtils.LOCTION_ADDRESS + ConstantsUtils.UserService, map);
        manager.getAsync(url, new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Toast.makeText(context, "请求失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                JSONObject object = new JSONObject(result);
                String flag = object.getString("error");
                if (flag.equals("ok")) {
                    userInfo = gson.fromJson(object.getString("object"), UserBean.class);
                    handler.sendEmptyMessage(ConstantsUtils.REGISTER_GET_DATA);
                } else {
                    handler.sendEmptyMessage(ConstantsUtils.REGISTER_GET_DATA_FAILURE);
                }
            }
        });
    }

    public class RegisterHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.REGISTER_GET_DATA:
                    showToast("注册成功！");
                    finish();
                    break;
                case ConstantsUtils.REGISTER_GET_DATA_FAILURE:
                    showToast("用户已注册！注册失败！");
                    break;

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            String contry_code = bundle.getString("contry_code");
            String contry_en_name = bundle.getString("contry_en_name");
            String contry_zh_name = bundle.getString("contry_zh_name");
            tv_phone.setText(contry_code);
            tvContry.setText(contry_zh_name);
        }
    }
}
