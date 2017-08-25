package com.example.yuzelli.bookkeepmananger.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


/**
 * Created by 51644 on 2017/7/20.
 */

public abstract class DialogUtils {
    private Activity activity;
    private int xmlLayout;
    private Dialog dialog = null;

    public DialogUtils(Activity activity, int xmlLayout) {
        this.activity = activity;
        this.xmlLayout = xmlLayout;
        createDialogView();
    }

    private void createDialogView() {
        dialog = new Dialog(activity, R.style.PhotoDialog);
        final ViewHelper viewHelper = getViewHelper();
        initLayout(viewHelper, dialog);

        dialog.setContentView(viewHelper.getContentView());
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        //将对话框的大小按屏幕大小的百分比设置
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        p.height = WindowManager.LayoutParams.WRAP_CONTENT;  // 高度设置为屏幕的0.6
        p.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);

//        android Activity改成dialog样式后 怎设置点击空白处关闭窗体，如图点击窗体意外的地方关闭窗体
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }


    public abstract void initLayout(ViewHelper helper, Dialog dialog);

    private ViewHelper getViewHelper()
    {
        return ViewHelper.get(activity,xmlLayout);
    }


    public  static class ViewHelper {
        private final SparseArray<View> mViews;
        private View mConvertView;
        private static ViewHelper viewHelper = null;

        private ViewHelper(Context context, int layoutId) {
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        }
        public static ViewHelper get(Context context, int layoutId)
        {

            viewHelper = new ViewHelper(context, layoutId);

            return viewHelper;
        }

        public View getContentView(){
            return mConvertView;
        }


        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public ViewHelper setText(int viewId, String text)
        {
            if(text==null){
                text = "";
            }
            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }
        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param bm
         * @return
         */
        public ViewHelper setImageBitmap(int viewId, Bitmap bm)
        {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param url
         * @return
         */
        public ViewHelper setImageByUrl(int viewId, String url)
        {
            final ImageView imageView = getView(viewId);
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                    .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型
                    //  .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                    .build();

            ImageLoader.getInstance().loadImage(url,options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
            return this;
        }

        private View.OnClickListener mListener;


        public  void setViewClick(int viewId,final ViewClickCallBack callBack){
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.doClickAction(v);
                }
            });
        }
        public static interface ViewClickCallBack {
            public void doClickAction(View v);
        }

    }

}
