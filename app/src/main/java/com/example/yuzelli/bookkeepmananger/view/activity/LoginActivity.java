package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    private LoginHandler handler;
    private UserBean userInfo;
    private Context context;


    @Override
    protected int layoutInit() {
        return R.layout.activity_login;
    }

    @Override
    protected void binEvent() {
        handler = new LoginHandler();
        context = this;
        tvTitle.setText("登陆");
    }

    @Override
    protected void fillData() {

    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLoginAction();
                break;
            case R.id.tv_forget_password:
                FindPassWoridActivity.startAction(LoginActivity.this);
                break;
            case R.id.tv_register:
                RegisterActivity.startAction(LoginActivity.this);
                break;
        }
    }

    private void doLoginAction() {
        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("type", "login");
        map.put("phone", etPhone.getText().toString().trim());
        map.put("passWord", etPassword.getText().toString().trim());
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
                    handler.sendEmptyMessage(ConstantsUtils.LOGIN_GET_DATA);
                } else {
                    handler.sendEmptyMessage(ConstantsUtils.LOGIN_GET_DATA_FAILURE);
                }
            }
        });
    }

    class LoginHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ConstantsUtils.LOGIN_GET_DATA:
                    SharePreferencesUtil.saveObject(context,ConstantsUtils.SP_LOGIN_USER_INFO,userInfo);
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case ConstantsUtils.LOGIN_GET_DATA_FAILURE:
                    showToast("登陆失败！");
                    break;
            }
        }
    }
}
