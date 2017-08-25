package com.example.yuzelli.bookkeepmananger.app;

import android.app.Application;
import android.content.Context;

//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;



/**
 * Created by 51644 on 2017/5/16.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


        initImageLoader(getApplicationContext());
    }
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(context);
//
////Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);




    }


    public static Context getContextObject() {
        return context;
    }
}
