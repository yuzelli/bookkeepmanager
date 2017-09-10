package com.example.yuzelli.bookkeepmananger.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.pre_chart)
    PieChart mChart;
private Context context;
    @Override
    protected int layoutInit() {
        return R.layout.activity_pie_chart;
    }

    @Override
    protected void binEvent() {
        context = this;
        Intent intent = getIntent();
        ArrayList<TypeBean> types = (ArrayList<TypeBean>) intent.getSerializableExtra("list");
        PieData  pieData = getPieData(types.size(),types);
        showChart(mChart,pieData);
    }

    private void showChart(PieChart pieChart,   PieData  pieData) {
      //  pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("消费饼状图");

        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

        pieChart.setCenterText("消费比例");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(20f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }

    /**
     *
     * @param count 分成几部分
     * @param range
     */
    private PieData getPieData(int count,  ArrayList<TypeBean> types) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < types.size(); i++) {
            xValues.add(types.get(i).getName());  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */

        for (int i = 0; i < types.size(); i++) {
           float q = (float) types.get(i).getAllMoney();
            yValues.add(new Entry(q, i));
        }


//        float quarterly1 = 14;
//        float quarterly2 = 14;
//        float quarterly3 = 34;
//        float quarterly4 = 38;
//
//        yValues.add(new Entry(quarterly1, 0));
//        yValues.add(new Entry(quarterly2, 1));
//        yValues.add(new Entry(quarterly3, 2));
//        yValues.add(new Entry(quarterly4, 3));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "消费指示"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(ContextCompat.getColor(context, R.color.mediumorchid));
        colors.add(ContextCompat.getColor(context, R.color.palevioletred));
        colors.add(ContextCompat.getColor(context, R.color.goldenrod));
        colors.add(ContextCompat.getColor(context, R.color.orchid));
        colors.add(ContextCompat.getColor(context, R.color.chocolate));
        colors.add(ContextCompat.getColor(context, R.color.peru));
        colors.add(ContextCompat.getColor(context, R.color.indianred));
        colors.add(ContextCompat.getColor(context, R.color.mediumvioletred));
        colors.add(ContextCompat.getColor(context, R.color.silver));
        colors.add(ContextCompat.getColor(context, R.color.darkgoldenrod));
        colors.add(ContextCompat.getColor(context, R.color.greenyellow));
        colors.add(ContextCompat.getColor(context, R.color.saddlebrown));


        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }
    @Override
    protected void fillData() {

    }
    public static void startAction(Context context, ArrayList<TypeBean> list){
        Intent intent = new Intent(context,PieChartActivity.class);
        intent.putExtra("list",list);
        context.startActivity(intent);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
