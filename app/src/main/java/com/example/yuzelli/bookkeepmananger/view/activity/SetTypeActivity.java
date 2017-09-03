package com.example.yuzelli.bookkeepmananger.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.adapter.GridImgAdapter2;
import com.example.yuzelli.bookkeepmananger.adapter.GridImgAdapter3;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetTypeActivity extends BaseActivity {


    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.tv_shouru)
    TextView tvShouru;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.grid_type)
    GridView gridType;
    private boolean iszhichu = true;
    private ArrayList<TypeBean> gridDatas;
    private ArrayList<TypeBean> gridDatas1;

    @Override
    protected int layoutInit() {
        return R.layout.activity_set_type;
    }

    @Override
    protected void binEvent() {

        gridDatas = (ArrayList<TypeBean>) SharePreferencesUtil.readObject(SetTypeActivity.this, ConstantsUtils.TYPE_ZHICHU);
        if (gridDatas == null) {
            gridDatas = new ArrayList<>();
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
            SharePreferencesUtil.saveObject(SetTypeActivity.this,ConstantsUtils.TYPE_ZHICHU,gridDatas);
        }
        gridDatas1 = (ArrayList<TypeBean>) SharePreferencesUtil.readObject(SetTypeActivity.this, ConstantsUtils.TYPE_SHOURU);
        if (gridDatas1 == null) {
            gridDatas1 = new ArrayList<>();
            gridDatas1.add(new TypeBean(0, "餐饮", R.drawable.ic_canying));
            gridDatas1.add(new TypeBean(1, "购物", R.drawable.ic_gouwu));
            gridDatas1.add(new TypeBean(2, "日用", R.drawable.ic_riyong));
            gridDatas1.add(new TypeBean(3, "交通", R.drawable.ic_jiaotong));
            gridDatas1.add(new TypeBean(4, "蔬菜", R.drawable.ic_shucai));
            gridDatas1.add(new TypeBean(5, "水果", R.drawable.ic_shuiguo));
            gridDatas1.add(new TypeBean(6, "零食", R.drawable.ic_linshi));
            gridDatas1.add(new TypeBean(7, "运动", R.drawable.ic_yundong));
            gridDatas1.add(new TypeBean(8, "娱乐", R.drawable.ic_yule));
            gridDatas1.add(new TypeBean(9, "通讯", R.drawable.ic_tongxing));
            gridDatas1.add(new TypeBean(10, "服装", R.drawable.if_fuzhuang));
            gridDatas1.add(new TypeBean(11, "美容", R.drawable.ic_meirong));
            SharePreferencesUtil.saveObject(SetTypeActivity.this,ConstantsUtils.TYPE_SHOURU,gridDatas1);
        }
        setGridView(0);
    }

    @Override
    protected void fillData() {

    }


    @OnClick({R.id.tv_zhichu, R.id.tv_shouru, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhichu:
                setGridView(0);
                iszhichu = true;
                tvShouru.setTextColor(ContextCompat.getColor(SetTypeActivity.this, R.color.text_blue_color));
                tvShouru.setBackgroundColor(ContextCompat.getColor(SetTypeActivity.this, R.color.white));
                tvZhichu.setTextColor(ContextCompat.getColor(SetTypeActivity.this, R.color.white));
                tvZhichu.setBackgroundColor(ContextCompat.getColor(SetTypeActivity.this, R.color.text_blue_color));
                break;
            case R.id.tv_shouru:
                setGridView(1);
                iszhichu = false;
                tvZhichu.setTextColor(ContextCompat.getColor(SetTypeActivity.this, R.color.text_blue_color));
                tvZhichu.setBackgroundColor(ContextCompat.getColor(SetTypeActivity.this, R.color.white));
                tvShouru.setTextColor(ContextCompat.getColor(SetTypeActivity.this, R.color.white));
                tvShouru.setBackgroundColor(ContextCompat.getColor(SetTypeActivity.this, R.color.text_blue_color));
                break;
            case R.id.tv_ok:
                if (iszhichu){
                    SharePreferencesUtil.saveObject(SetTypeActivity.this,ConstantsUtils.TYPE_ZHICHU,gridDatas);
                }else {
                    SharePreferencesUtil.saveObject(SetTypeActivity.this,ConstantsUtils.TYPE_SHOURU,gridDatas1);
                }
                showToast("已保存");
                finish();
                break;
        }
    }

    private void setGridView(int p) {
        if (p==0){
            gridType.setAdapter(new GridImgAdapter2(SetTypeActivity.this, gridDatas));
            gridType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    gridDatas.get(i).setZhiChu(!gridDatas.get(i).isZhiChu());
                    gridType.setAdapter(new GridImgAdapter2(SetTypeActivity.this, gridDatas));
                }
            });
        }else {
            gridType.setAdapter(new GridImgAdapter3(SetTypeActivity.this, gridDatas1));
            gridType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    gridDatas1.get(i).setSHouRu(!gridDatas1.get(i).isSHouRu());
                    gridType.setAdapter(new GridImgAdapter3(SetTypeActivity.this, gridDatas1));
                }



            });
        }
    }



}
