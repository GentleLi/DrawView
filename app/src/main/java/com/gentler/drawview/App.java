package com.gentler.drawview;


import android.app.Application;


/**
 * Created by admin on 2017/11/8.
 */

public class App extends Application {

    private static App mInstance;

    public static App getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}


