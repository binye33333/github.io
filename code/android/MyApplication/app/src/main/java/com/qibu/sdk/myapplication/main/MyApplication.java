package com.qibu.sdk.myapplication.main;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.LogPrinter;

public class MyApplication extends Application {

    long lastCommendStartTime;

    @Override
    public void onCreate() {
        super.onCreate();
        hook();
    }

    private void hook() {
        try {
            Looper.getMainLooper().setMessageLogging(new LogPrinter(Log.ERROR, "main thread") {
                public void println(String x) {
                    if (x.startsWith(">>>>> Dispatching to ")) {
                        lastCommendStartTime = System.currentTimeMillis();
                    } else if (x.startsWith("<<<<< Finished to ")) {
                        long during = System.currentTimeMillis() - lastCommendStartTime;
                        Log.e("main thread ", "main thread log :" + x);
                        Log.e("main thread ", "main thread time cost :" + during);
                    }
                }
            });
        } catch (Exception e) {

        }

    }
}
