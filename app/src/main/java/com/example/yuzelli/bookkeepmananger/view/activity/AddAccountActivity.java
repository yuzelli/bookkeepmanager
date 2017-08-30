package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.adapter.GridImgAdapter;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAccountActivity extends BaseActivity {
    @BindView(R.id.grid_type)
    GridView gridType;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.tv_shouru)
    TextView tvShouru;

    private GridImgAdapter adapter;
    // 图片封装为一个数组
    private ArrayList<TypeBean> gridDatas = new ArrayList<>();
    private boolean iszhichu = true;

    @Override
    protected int layoutInit() {
        return R.layout.activity_add_account;
    }

    @Override
    protected void binEvent() {
        setGridViewData();
        adapter = new GridImgAdapter(this, gridDatas);
        gridType.setAdapter(adapter);
        gridType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AddAccountActivity.this,SaveAccountActivity.class);
                intent.putExtra("type",gridDatas.get(i));
                intent.putExtra("iszhichu",iszhichu);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setGridViewData() {
        gridDatas.add(new TypeBean(0, "餐饮", R.drawable.ic_canying));
        gridDatas.add(new TypeBean(1, "购物", R.drawable.ic_gouwu));
        gridDatas.add(new TypeBean(2, "日用", R.drawable.ic_riyong));
        gridDatas.add(new TypeBean(3, "交通", R.drawable.ic_jiaotong));
        gridDatas.add(new TypeBean(4, "蔬菜", R.drawable.ic_shucai));
        gridDatas.add(new TypeBean(5, "水果", R.drawable.ic_shuiguo));
        gridDatas.add(new TypeBean(6, "零食", R.drawable.ic_linshi));
        gridDatas.add(new TypeBean(7, "运动", R.drawable.ic_yundong));
        gridDatas.add(new TypeBean(8, "娱乐", R.drawable.ic_yule));
        gridDatas.add(new TypeBean(9, "通讯", R.drawable.ic_tongxing));
        gridDatas.add(new TypeBean(10, "服装", R.drawable.if_fuzhuang));
        gridDatas.add(new TypeBean(11, "美容", R.drawable.ic_meirong));

    }

    @Override
    protected void fillData() {

    }


    @OnClick({R.id.tv_zhichu, R.id.tv_shouru, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhichu:
                iszhichu = true;
                tvZhichu.setTextColor(ContextCompat.getColor(AddAccountActivity.this,R.color.text_blue_color));
                tvZhichu.setBackgroundColor(ContextCompat.getColor(AddAccountActivity.this,R.color.white));
                tvShouru.setTextColor(ContextCompat.getColor(AddAccountActivity.this,R.color.white));
                tvShouru.setBackgroundColor(ContextCompat.getColor(AddAccountActivity.this,R.color.text_blue_color));

                break;
            case R.id.tv_shouru:
                iszhichu = false;
                tvShouru.setTextColor(ContextCompat.getColor(AddAccountActivity.this,R.color.text_blue_color));
                tvShouru.setBackgroundColor(ContextCompat.getColor(AddAccountActivity.this,R.color.white));
                tvZhichu.setTextColor(ContextCompat.getColor(AddAccountActivity.this,R.color.white));
                tvZhichu.setBackgroundColor(ContextCompat.getColor(AddAccountActivity.this,R.color.text_blue_color));
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }


}
