package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Context context;
    private ChangePassHandler handler;
    private UserBean userInfo;

    @Override
    protected int layoutInit() {
        return R.layout.activity_change_pass;
    }

    @Override
    protected void binEvent() {
        context = this;
        handler = new ChangePassHandler();
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        imgBack.setVisibility(View.VISIBLE);
        tvTitle.setText("重置密码");
        tvPhone.setText(phone);
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
                upUserInfo();
                break;
        }
    }

    private void upUserInfo() {
        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("type", "updata");
        map.put("phone", phone);
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
                    handler.sendEmptyMessage(ConstantsUtils.UPDATA_USER_GET_DATA);
                } else {
                    handler.sendEmptyMessage(ConstantsUtils.UPDATA_USER_GET_DATA_FAILURE);
                }
            }
        });
    }

    class ChangePassHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ConstantsUtils.UPDATA_USER_GET_DATA:
                    SharePreferencesUtil.saveObject(context,ConstantsUtils.SP_LOGIN_USER_INFO,userInfo);
                    finish();
                    break;
                case ConstantsUtils.UPDATA_USER_GET_DATA_FAILURE:
                    showToast("登陆失败！");
                    break;
            }
        }
    }
}
