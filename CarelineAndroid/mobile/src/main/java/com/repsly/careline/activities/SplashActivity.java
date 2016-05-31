package com.repsly.careline.activities;

import com.repsly.careline.MainActivity;
import com.repsly.careline.R;
import com.repsly.utils.lib.activities.abstracts.SplashAbstract;

/**
 * Created by Alen on 31.5.2016..
 */
public class SplashActivity extends SplashAbstract {

    @Override
    public int provideLayoutRes() {
        return R.layout.splash_activity;
    }

    @Override
    public int getSplashTime() {
        return 700;
    }

    @Override
    public Class getNextClassActivity() {
        return HomeReceiverActivity.class;
    }
}
