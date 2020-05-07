package com.qibu.sdk.myapplication.aop.life;


import android.util.Log;

import java.util.HashMap;

public class LifeUtil {
    HashMap<String, Long> mStartTimeRecord = new HashMap<>();
    private static LifeUtil instance = new LifeUtil();

    private LifeUtil() {

    }

    public static LifeUtil getInstance() {
        return instance;
    }

    public void markStartTime(String key) {
        mStartTimeRecord.put(key, System.currentTimeMillis());
    }

    public Long removeStartTime(String key) {
        return mStartTimeRecord.remove(key);
    }

    public void getStartTime(String key) {
        mStartTimeRecord.get(key);
    }

    public void uploadTime(String key) {
        Long start = removeStartTime(key);
        if (start != null) {
            Log.e("LifeUtil", "key point :" + key + " cost is: " + (System.currentTimeMillis() - start));
        }
    }
}
