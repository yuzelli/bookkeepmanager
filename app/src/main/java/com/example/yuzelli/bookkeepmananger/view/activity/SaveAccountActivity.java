package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;

public class SaveAccountActivity extends BaseActivity {

    @Override
    protected int layoutInit() {
        return R.layout.activity_save_account;
    }

    @Override
    protected void binEvent() {
        Intent intent =getIntent();

    }

    @Override
    protected void fillData() {

    }
}
