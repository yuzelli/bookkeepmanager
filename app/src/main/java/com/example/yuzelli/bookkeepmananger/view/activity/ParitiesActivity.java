package com.example.yuzelli.bookkeepmananger.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParitiesActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.spinner_input)
    Spinner spinnerInput;
    @BindView(R.id.et_out)
    EditText etOut;
    @BindView(R.id.spinner_out)
    Spinner spinnerOut;
    @BindView(R.id.textView)
    TextView textView;
    float prices[] = {1f,6.69f,0.85f,0.063f,7.35f,8.7901f};

    @Override
    protected int layoutInit() {
        return R.layout.activity_parities;
    }

    @Override
    protected void binEvent() {
        tvTitle.setText("汇率换算");
        imgBack.setVisibility(View.VISIBLE);
        spinnerOut.setSelection(1);
        spinnerInput.setSelection(0);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMoney();
            }
        });
    }


    private void getMoney() {
        if (etInput.getText().toString().trim().equals("")){
            showToast("请输入值！");
            return;
        }
        int p1 = spinnerInput.getSelectedItemPosition();
        int p2 = spinnerOut.getSelectedItemPosition();
        float price = Float.valueOf(etInput.getText().toString().trim());
        DecimalFormat df = new DecimalFormat("######0.00");
        double a = price/prices[p1]*prices[p2];
        etOut.setText(df.format(a)+"");

    }

    @Override
    protected void fillData() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
