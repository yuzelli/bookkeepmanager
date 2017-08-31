package com.example.yuzelli.bookkeepmananger.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/9/6.
 */

public class ParticularsFragment extends BaseFragment {
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.sprinner_month)
    Spinner sprinnerMonth;
    @BindView(R.id.spinner_zhi)
    Spinner spinnerZhi;
    @BindView(R.id.listview)
    ExpandableListView listview;


    @Override
    protected int layoutInit() {
        return R.layout.fragment_particulars;
    }

    @Override
    protected void bindEvent(View v) {

    }

    @Override
    protected void fillData() {

    }

}
