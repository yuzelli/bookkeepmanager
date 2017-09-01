package com.example.yuzelli.bookkeepmananger.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.bean.PartBean;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;

import java.util.ArrayList;

/**
 * Created by 51644 on 2017/9/7.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<TypeBean> gridDatas = new ArrayList<>();
    private Activity activity ;
    private ArrayList<PartBean> firstArrays;

    public MyExpandableListViewAdapter(Activity activity, ArrayList<PartBean> firstArrays) {
        this.activity = activity;
        this.firstArrays = firstArrays;

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

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int parentPos, int childPos) {
        return firstArrays.get(parentPos).bookKeepArrs.get(childPos);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return firstArrays.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int parentPos) {
        return firstArrays.get(parentPos).bookKeepArrs.size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int parentPos) {
        return firstArrays.get(parentPos);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int parentPos) {
        return parentPos;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
        view = activity.getLayoutInflater().inflate(R.layout.item_group,null);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_week = view.findViewById(R.id.tv_week);
        TextView tv_money = view.findViewById(R.id.tv_money);

        tv_time.setText(firstArrays.get(parentPos).getTime());
        tv_week.setText(firstArrays.get(parentPos).getWeek());
        if (firstArrays.get(parentPos).bookKeepArrs.get(0).getIsZhiCu()==1){
            tv_money.setText("支出:"+firstArrays.get(parentPos).getMoney());
        }else {
            tv_money.setText("收入:"+firstArrays.get(parentPos).getMoney());
        }


        return view;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
        view = activity.getLayoutInflater().inflate(R.layout.item_child,null);
        ImageView img_type = view.findViewById(R.id.img_type);
        TextView tv_comment =view.findViewById(R.id.tv_comment);
        TextView tv_money =view.findViewById(R.id.tv_money);

        img_type.setImageResource(gridDatas.get(firstArrays.get(parentPos).bookKeepArrs.get(childPos).getType_id()).getTypeRESID());
        tv_comment.setText(firstArrays.get(parentPos).bookKeepArrs.get(childPos).getComment());
        if (firstArrays.get(parentPos).bookKeepArrs.get(0).getIsZhiCu()==0){
            tv_money.setText("-"+firstArrays.get(parentPos).bookKeepArrs.get(childPos).getMoney());
        }else {
            tv_money.setText("+"+firstArrays.get(parentPos).bookKeepArrs.get(childPos).getMoney());
        }

        return view;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
