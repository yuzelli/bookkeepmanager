package com.example.yuzelli.bookkeepmananger.view.activity;


import android.content.Context;
import android.content.Intent;

import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.base.BaseActivity;
import com.example.yuzelli.bookkeepmananger.view.fragment.CommonUseFragment;
import com.example.yuzelli.bookkeepmananger.view.fragment.ParticularsFragment;
import com.example.yuzelli.bookkeepmananger.view.fragment.SettingFragment;
import com.example.yuzelli.bookkeepmananger.view.fragment.StatementFragment;
import com.example.yuzelli.bookkeepmananger.widgets.DragFloatActionButton;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.dfab_add)
    DragFloatActionButton dfab_add;



    //定义FragmentTabHost对象
    private FragmentTabHost tabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放user的Fragment界面
    private Class fragmentArray[] = {ParticularsFragment.class, StatementFragment.class, CommonUseFragment.class, SettingFragment.class};
    //定义数组来存放的按钮图片
    private int tabImageViewArray[] = {R.drawable.tab_part, R.drawable.tab_state,
            R.drawable.tab_com,R.drawable.tab_set};
    //Tab选项卡的文字
    private String tabtTextViewArray[] = {"明细", "报表", "常用","设置"};

    @Override
    protected int layoutInit() {
        return R.layout.activity_main;
    }

    @Override
    protected void binEvent() {
        //oncreate方法中的回调
        initView();
        dfab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddAccountActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void fillData() {

    }


    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_pageContent);


        //得到fragment的个数
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabtTextViewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.main_tab_select_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_tabIcon);
        imageView.setImageResource(tabImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tabText);
        textView.setText(tabtTextViewArray[index]);

        return view;
    }

    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public static void startAction(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }



}
