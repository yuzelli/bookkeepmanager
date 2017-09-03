package com.example.yuzelli.bookkeepmananger.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseFragment;
import com.example.yuzelli.bookkeepmananger.bean.BookKeepBean;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;
import com.example.yuzelli.bookkeepmananger.bean.UserBean;
import com.example.yuzelli.bookkeepmananger.constants.ConstantsUtils;
import com.example.yuzelli.bookkeepmananger.utils.CommonAdapter;
import com.example.yuzelli.bookkeepmananger.utils.SharePreferencesUtil;
import com.example.yuzelli.bookkeepmananger.utils.ViewHolder;
import com.example.yuzelli.bookkeepmananger.view.activity.PieChartActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okio.ForwardingTimeout;

/**
 * Created by 51644 on 2017/9/6.
 */

public class StatementFragment extends BaseFragment {
    @BindView(R.id.spinner_year)
    Spinner spinnerYear;
    @BindView(R.id.spinner_month)
    Spinner spinnerMonth;
    @BindView(R.id.spinner_week)
    Spinner spinnerWeek;
    @BindView(R.id.spinner_zhi)
    Spinner spinnerZhi;
    @BindView(R.id.btn_look)
    Button btnLook;
    @BindView(R.id.listview)
    ListView listview;
    private ArrayList<TypeBean> gridDatas = new ArrayList<>();
    private ArrayList<TypeBean> list = new ArrayList<>();

    @Override
    protected int layoutInit() {
        return R.layout.fragment_statement;
    }

    @Override
    protected void bindEvent(View v) {
        spinnerYear.setSelection(1);
        spinnerZhi.setSelection(1);
        spinnerMonth.setSelection(0);
        spinnerWeek.setSelection(0);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setGridViewData();

        btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PieChartActivity.startAction(getActivity(), list);
            }
        });
    }

    @Override
    protected void fillData() {
        initListView();
    }

    private void initListView() {
        list.clear();
        listview.setAdapter(new CommonAdapter<TypeBean>(getActivity(), list, R.layout.cell_paihang) {
            @Override
            public void convert(ViewHolder helper, TypeBean item, int position) {
                helper.setText(R.id.tv_number, position + 1 + "");
                helper.setText(R.id.tv_name, item.getName());
                DecimalFormat df = new DecimalFormat("######0.00");
                double a = item.getAllMoney() * 100;
                helper.setText(R.id.tv_money, df.format(a) + "%");
                helper.setImageResource(R.id.img_type, item.getTypeRESID());
            }
        });
        for (int i = 0; i < gridDatas.size(); i++) {
            gridDatas.get(i).setAllMoney(0d);
        }
        UserBean u = (UserBean) SharePreferencesUtil.readObject(getActivity(),ConstantsUtils.SP_LOGIN_USER_INFO);
        ArrayList<BookKeepBean> bookArr = (ArrayList<BookKeepBean>) SharePreferencesUtil.readObject(getActivity(),u.getPhone()+ ConstantsUtils.Bookkeep_INFO);
        ArrayList<BookKeepBean> bookArr2 = new ArrayList<>();
        if (bookArr == null) {
            bookArr = new ArrayList<>();
        }
        for (BookKeepBean b : bookArr) {
            if (spinnerMonth.getSelectedItemPosition()==0&&spinnerWeek.getSelectedItemPosition()==0){
                if (!b.getYear().equals(2017 - spinnerYear.getSelectedItemPosition() + "")) {
                    continue;
                }
            }else if (spinnerMonth.getSelectedItemPosition()!=0&&spinnerWeek.getSelectedItemPosition()==0){
                if (!b.getYear().equals(2017 - spinnerYear.getSelectedItemPosition() + "")) {
                    continue;
                }
                if ( !b.getMonth().equals(getM(spinnerMonth.getSelectedItemPosition()))) {
                    continue;
                }
            }else {
                if (!b.getYear().equals(2017 - spinnerYear.getSelectedItemPosition() + "")) {
                    continue;
                }
                if ( !b.getMonth().equals(getM(spinnerMonth.getSelectedItemPosition()))) {
                    continue;
                }
                if (!b.getWeek().equals(getW(spinnerWeek.getSelectedItemPosition()))) {
                    continue;
                }
            }
            if (b.getIsZhiCu() != spinnerZhi.getSelectedItemPosition()) {
                continue;
            }
            bookArr2.add(b);
        }
        double allmoney = 0d;
        for (BookKeepBean b : bookArr2) {
            allmoney += Double.valueOf(b.getMoney());
            double a = Double.valueOf(b.getMoney());
            double bb = gridDatas.get(b.getType_id()).getAllMoney();
            double c = a + bb;
            gridDatas.get(b.getType_id()).setAllMoney(c);
        }
        if (allmoney > 0) {
            for (int i = 0; i < gridDatas.size(); i++) {
                gridDatas.get(i).setAllMoney(gridDatas.get(i).getAllMoney() / allmoney);
            }
        } else {
            return;
        }

        ArrayList<TypeBean> types = new ArrayList<>();
        types.addAll(gridDatas);
        Collections.sort(types, new Comparator<TypeBean>() {
            @Override
            public int compare(TypeBean typeBean, TypeBean t1) {
                if (typeBean.getAllMoney() > t1.getAllMoney()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int length = types.size();

        for (int i = 0; i < length; i++) {
            if (types.get(i).getAllMoney() >= 0.01) {
                list.add(types.get(i));
            }
        }


        listview.setAdapter(new CommonAdapter<TypeBean>(getActivity(), list, R.layout.cell_paihang) {
            @Override
            public void convert(ViewHolder helper, TypeBean item, int position) {
                helper.setText(R.id.tv_number, position + 1 + "");
                helper.setText(R.id.tv_name, item.getName());
                DecimalFormat df = new DecimalFormat("######0.00");
                double a = item.getAllMoney() * 100;
                helper.setText(R.id.tv_money, df.format(a) + "%");
                helper.setImageResource(R.id.img_type, item.getTypeRESID());
            }
        });
    }


    private String getW(int selectedItemPosition) {
        String str = "";
        switch (selectedItemPosition-1) {
            case 0:
                str = "第一周";
                break;
            case 1:
                str = "第二周";
                break;
            case 2:
                str = "第三周";
                break;
            case 3:
                str = "第四周";
                break;
            case 4:
                str = "第五周";
                break;
        }
        return str;
    }

    private String getM(int selectedItemPosition) {
        String str = String.format("%2d", selectedItemPosition ).replace(" ", "0");
        return str;
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
}
