package com.repsly.careline.activities;

import com.repsly.careline.R;
import com.repsly.careline.database.DbHelper;
import com.repsly.careline.model.User;
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
        DbHelper dbHelper = new DbHelper(this);
        User user= dbHelper.getUser();
        if(user!=null) {
            if(user.isManager()){
                return HomeGiverActivity.class;
            }
            return HomeReceiverActivity.class;
        }
        return LogInActivity.class;
    }
}
