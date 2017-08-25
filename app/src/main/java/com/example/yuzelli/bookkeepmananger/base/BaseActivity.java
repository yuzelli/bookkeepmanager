package com.example.yuzelli.bookkeepmananger.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;


import com.example.yuzelli.bookkeepmananger.utils.ActivityCollectorUtil;
import com.example.yuzelli.bookkeepmananger.utils.MyToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends FragmentActivity {
    private Unbinder unbinder;
    protected Bundle savedInstanceState;
    public final static String pageSize = "20/";
    public static String token;

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // 隐藏actionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏通知栏
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.FLAG_FULLSCREEN);


        this.savedInstanceState = savedInstanceState;

        setContentView(layoutInit());
        init(savedInstanceState);
        unbinder= ButterKnife.bind(this);
        viewInit();
        binEvent();
        ActivityCollectorUtil.addActivity(this);
    }
    /**
     * 统一显示toast
     *
     * @param
     * @author Hoyn
     *
     */
    protected void showToast(String msg) {
        MyToast.show(msg);
    }

    public String getToken(){

        return "";
    }
    @Override
    protected void onResume() {
        super.onResume();
        fillData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
        unbinder.unbind();
    }

    /**
     *加载布局
     */
    protected abstract int layoutInit();
    /**
     * 绑定事件
     */
    protected abstract void binEvent();
    /**
     * TODO:需要考虑savedInstanceState 设置布局文件
     *
     * @author yuzelli
     *
     */
    protected void init(Bundle savedInstanceState) {

    }
    /**
     * 加载数据
     * @author yuzelli
     *
     */
    protected void viewInit() {
//        tv_title = (TextView) findViewById(R.id.title);
//        if (!isLoginOut()) {
//        }
    };
    protected abstract void fillData();
}
