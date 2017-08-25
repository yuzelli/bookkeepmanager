package com.example.yuzelli.bookkeepmananger.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by xiaos on 2017/2/23.
 */
public class ListViewForScrollViewUtil extends ListView {
    public ListViewForScrollViewUtil(Context context) {
        super(context);
    }
    public ListViewForScrollViewUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ListViewForScrollViewUtil(Context context, AttributeSet attrs,
                                     int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}

