package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChongfuActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.spinner_week)
    Spinner spinnerWeek;
    @BindView(R.id.spinner_year)
    Spinner spinnerYear;
    @BindView(R.id.spinner_month)
    Spinner spinnerMonth;
    private RadioButton radioButton0;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    @Override
    protected int layoutInit() {
        return R.layout.activity_chongfu;
    }

    @Override
    protected void binEvent() {
        radioButton0 = (RadioButton) this.findViewById(R.id.radio0);
        radioButton1 = (RadioButton) this.findViewById(R.id.radio1);
        radioButton2 = (RadioButton) this.findViewById(R.id.radio2);
        radioButton0.setChecked(true);
        tvTitle.setText("设置重复时间");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("确定");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                //设置回传的意图p


                if (radioButton0.isChecked()){
                    intent.putExtra("type", 0+"");
                    intent.putExtra("content",spinnerWeek.getSelectedItemPosition()+"");
                }else if (radioButton1.isChecked()){
                    intent.putExtra("type", 1+"");
                    intent.putExtra("content",spinnerMonth.getSelectedItemPosition()+"");
                }else if (radioButton2.isChecked()){
                    intent.putExtra("type", 2+"");
                    intent.putExtra("content",spinnerMonth.getSelectedItemPosition()+"");
                    intent.putExtra("content2",spinnerYear.getSelectedItemPosition()+"");
                }
                setResult(1001, intent);
                finish();
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        RadioButton button = (RadioButton) view;
        boolean isChecked = button.isChecked();
        switch (view.getId()) {
            case R.id.radio0:
                if (isChecked) {
                    spinnerWeek.setVisibility(View.VISIBLE);
                    spinnerYear.setVisibility(View.GONE);
                    spinnerMonth.setVisibility(View.GONE);
                    spinnerWeek.setSelection(0);
                    spinnerMonth.setSelection(0);
                    spinnerYear.setSelection(0);
                }
                break;
            case R.id.radio1:
                if (isChecked) {
                    spinnerWeek.setVisibility(View.GONE);
                    spinnerYear.setVisibility(View.GONE);
                    spinnerMonth.setVisibility(View.VISIBLE);
                    spinnerWeek.setSelection(0);
                    spinnerMonth.setSelection(0);
                    spinnerYear.setSelection(0);
                }
                break;
            case R.id.radio2:
                if (isChecked) {
                    spinnerWeek.setVisibility(View.GONE);
                    spinnerYear.setVisibility(View.VISIBLE);
                    spinnerMonth.setVisibility(View.VISIBLE);
                    spinnerWeek.setSelection(0);
                    spinnerMonth.setSelection(0);
                    spinnerYear.setSelection(0);
                }
                break;

        }
    }

    @Override
    protected void fillData() {

    }


}
