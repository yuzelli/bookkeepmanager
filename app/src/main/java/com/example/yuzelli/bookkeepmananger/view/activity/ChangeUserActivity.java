package com.example.yuzelli.bookkeepmananger.view.activity;

import android.os.Bundle;
import android.view.View;
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

public class ChangeUserActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_userName)
    EditText etUserName;


    UserBean user;

    @Override
    protected int layoutInit() {
        return R.layout.activity_change_user;
    }

    @Override
    protected void binEvent() {
        imgBack.setVisibility(View.VISIBLE);
        user = (UserBean) SharePreferencesUtil.readObject(this, ConstantsUtils.SP_LOGIN_USER_INFO);
        tvTitle.setText("修改资料");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE
        );
        etUserName.setText(user.getName());
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upUserInfo();
            }
        });
    }

    @Override
    protected void fillData() {

    }
    private void upUserInfo() {
        OkHttpClientManager manager = OkHttpClientManager.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("type", "updata");
        map.put("phone", user.getPhone());
        map.put("passWord",user.getPassWord());
        map.put("name", etUserName.getText().toString());
        String url = OkHttpClientManager.attachHttpGetParams(ConstantsUtils.LOCTION_ADDRESS + ConstantsUtils.UserService, map);
        manager.getAsync(url, new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                showToast("修改失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                JSONObject object = new JSONObject(result);
                String flag = object.getString("error");
                if (flag.equals("ok")) {
                   UserBean userInfo = gson.fromJson(object.getString("object"), UserBean.class);
                    SharePreferencesUtil.saveObject(ChangeUserActivity.this,ConstantsUtils.SP_LOGIN_USER_INFO,userInfo);
                    showToast("修改成功！");
                } else {
                    showToast("修改失败！");
                }
            }
        });
    }

    @OnClick({R.id.img_back, R.id.tv_right, R.id.rl_changpass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right:
                doChangeUserInfo();
                break;
            case R.id.rl_changpass:
                ChangePassActivity.startAction(ChangeUserActivity.this,user.getPhone() );
                break;
        }
    }

    private void doChangeUserInfo() {
    }
}
