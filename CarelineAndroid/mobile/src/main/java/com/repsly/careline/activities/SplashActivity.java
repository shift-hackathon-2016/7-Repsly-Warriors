package com.repsly.careline.activities;

import com.repsly.careline.R;
import com.repsly.careline.database.DbHelper;
import com.repsly.careline.helpers.AlarmHelper;
import com.repsly.careline.helpers.gps.LocationHelper;
import com.repsly.careline.model.User;
import com.repsly.utils.lib.activities.abstracts.SplashAbstract;
import com.tumblr.remember.Remember;

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
        LocationHelper.startLocationGettingProcess(getApplicationContext());
        AlarmHelper.setAlarmForMovementTracking(getApplicationContext());

        boolean loggedIn = Remember.getBoolean("loggedIn", false);
        boolean isManager = Remember.getBoolean("isManager", false);
        if (loggedIn) {
            if (isManager) {
                return HomeGiverActivity.class;
            }
            return HomeReceiverActivity.class;
        }
        return LogInActivity.class;
    }
}
