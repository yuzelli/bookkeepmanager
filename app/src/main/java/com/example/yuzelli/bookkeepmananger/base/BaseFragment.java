package com.example.yuzelli.bookkeepmananger.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.yuzelli.bookkeepmananger.utils.MyToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 51644 on 2017/5/16.
 */

public abstract class BaseFragment extends Fragment {
    private View rootView;
    private Unbinder unbinder;
    public final static String pageSize = "20/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(layoutInit(), container, false);
            unbinder= ButterKnife.bind(this,rootView);
            bindEvent(rootView);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }
    protected abstract int layoutInit();
    public String getToken(){

        return "";

    }
    /**
     * 绑定事件
     *
     * @author liu_haifang
     * @date 2015-4-9 下午5:05:22
     */
    protected abstract void bindEvent(View v);

    /**
     * 加载数据
     *
     * @author liu_haifang
     * @date 2015-4-9 下午5:05:30
     */
    protected abstract void fillData();
    /**
     * 统一显示toast
     *
     * @param
     * @author Hoyn
     *
     */
    protected void showToast(String msg) {
        MyToast.show(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        fillData();
//		 MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
