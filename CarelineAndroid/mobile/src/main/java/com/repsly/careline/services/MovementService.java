package com.repsly.careline.services;

import android.app.Service;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.repsly.careline.helpers.Constants;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.utils.MotionListener;
import com.repsly.careline.utils.MovementDetector;
import com.tumblr.remember.Remember;

import java.util.Date;

/**
 * Created by Alen on 31.5.2016..
 */
public class MovementService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        MovementDetector.getInstance(this).addListener(new MotionListener() {

            @Override
            public void onMotionDetected(SensorEvent event, float acceleration) {
                String currentDateandTime = DateTimeUtil.toISODate(new Date());
                Remember.putString(Constants.MOTION_TIME, currentDateandTime);
            }
        });

        MovementDetector.getInstance(this).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
