package com.example.yuzelli.bookkeepmananger.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.adapter.MyExpandableListViewAdapter;
import com.example.yuzelli.bookkeepmananger.base.BaseFragment;
import com.example.yuzelli.bookkeepmananger.bean.BookKeepBean;
import com.example.yuzelli.bookkeepmananger.bean.PartBean;
import com.example.yuzelli.bookkeepmananger.bean.UserBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.DateUtils;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/9/6.
 */

public class ParticularsFragment extends BaseFragment {
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_all_money)
    TextView tv_all_money;
    @BindView(R.id.sprinner_month)
    Spinner sprinnerMonth;
    @BindView(R.id.spinner_zhi)
    Spinner spinnerZhi;
    @BindView(R.id.listview)
    ExpandableListView listview;
    private ArrayList<BookKeepBean> bookKeepBeen;
    private ArrayList<PartBean> partBeanArrayList;
    DateUtils dateUtils = new DateUtils();
    @Override
    protected int layoutInit() {
        return R.layout.fragment_particulars;
    }

    @Override
    protected void bindEvent(View v) {


        tvYear.setText(dateUtils.year+"年");
        int m = dateUtils.month;
        sprinnerMonth.setSelection(m-1);
        spinnerZhi.setSelection(1);
        sprinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerZhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    protected void fillData() {
        initListView();
    }

    private void initListView() {
        String spinner_month = getMonthBySpinner();
        int iszhicu = spinnerZhi.getSelectedItemPosition();
        partBeanArrayList = new ArrayList<>();

        UserBean u = (UserBean) SharePreferencesUtil.readObject(getActivity(),ConstantsUtils.SP_LOGIN_USER_INFO);
        bookKeepBeen = (ArrayList<BookKeepBean>) SharePreferencesUtil.readObject(getActivity(), u.getPhone()+ConstantsUtils.Bookkeep_INFO);
        if (bookKeepBeen == null) {
            bookKeepBeen = new ArrayList<>();
        }
        ArrayList<String> dayLists = new ArrayList<>();
        for (BookKeepBean book : bookKeepBeen) {
            if (!book.getYear().equals(dateUtils.year+"")){
                continue;
            }
            if (!spinner_month.equals(book.getMonth()) || iszhicu != book.getIsZhiCu()) {
                continue;
            }
            boolean have = false;
            int postion = -1;
            for (int i = 0; i < dayLists.size(); i++) {
                if (dayLists.get(i).equals(book.getDay())) {
                   postion = i;
                    have = true;
                   break;
                }
            }
            if (have){
                partBeanArrayList.get(postion).bookKeepArrs.add(book);
            }else {
                dayLists.add(book.getDay());
                PartBean part = new PartBean();
                part.setTime(book.getDay());
                part.bookKeepArrs.add(book);
                partBeanArrayList.add(part);
            }

        }

        for (PartBean p : partBeanArrayList) {
            Double money = 0d;
            for (BookKeepBean b : p.bookKeepArrs) {
                money = money + Double.valueOf(b.getMoney());
            }
            p.setMoney(money + "");
            if (p.bookKeepArrs.get(0).getMonth().substring(0, 1).equals("0")) {
                p.setTime(p.bookKeepArrs.get(0).getMonth().substring(1, 2) + "月" + p.bookKeepArrs.get(0).getDay());
            } else {
                p.setTime(p.bookKeepArrs.get(0).getMonth() + "月" + p.bookKeepArrs.get(0).getDay());
            }
            p.setWeek(GetWeek(p.bookKeepArrs.get(0).getTime()));
        }
        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(getActivity(),partBeanArrayList);
              listview.setAdapter(adapter);
        double all = 0.0d;
        for (PartBean p :partBeanArrayList){
            all = all + Double.valueOf(p.getMoney());
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        tv_all_money.setText(df.format(all));

          for(int i = 0; i < adapter.getGroupCount(); i++){
              listview.expandGroup(i);
        }
    }

    private String GetWeek(long time) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        int week_index = cl.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public String getMonthBySpinner() {
        int postion = sprinnerMonth.getSelectedItemPosition();
        String[] mArr = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        return mArr[postion];
    }
}
