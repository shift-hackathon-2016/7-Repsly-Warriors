package com.repsly.careline;

import android.app.Application;

import com.tumblr.remember.Remember;

/**
 * Created by tosulc on 31.05.2016..
 */
public class CarelineApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), "com.repsly.careline");
    }
}
