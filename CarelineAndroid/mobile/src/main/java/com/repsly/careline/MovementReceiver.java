package com.repsly.careline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.repsly.careline.helpers.Constants;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.model.TrackingEvent;
import com.repsly.careline.retrofit.ApiCarelineImpl;
import com.tumblr.remember.Remember;

import java.util.Date;

/**
 * Created by tosulc on 31.05.2016..
 */
public class MovementReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (CarelineApplication.lastGpsLocation != null) {
            ApiCarelineImpl service = new ApiCarelineImpl().buildInterceptor()
                                                           .addAuthHeader(
                                                                   Remember.getString(
                                                                           Constants.LOGIN_DATA,
                                                                           ""));
            service.sendUserTracking(
                    new TrackingEvent(4, Remember.getString(Constants.MOTION_TIME, ""),
                                      CarelineApplication.lastGpsLocation.getLatitude(),
                                      CarelineApplication.lastGpsLocation.getLongitude(),
                                      ""));
        }
    }
}
