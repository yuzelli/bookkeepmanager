package com.example.yuzelli.bookkeepmananger.view.activity;


import android.content.Context;
import android.content.Intent;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutInit() {
        return R.layout.activity_main;
    }

    @Override
    protected void binEvent() {

    }

    @Override
    protected void fillData() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }



}
