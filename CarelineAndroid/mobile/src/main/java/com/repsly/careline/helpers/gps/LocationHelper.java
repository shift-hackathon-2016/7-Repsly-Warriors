package com.repsly.careline.helpers.gps;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.repsly.careline.CarelineApplication;

/**
 * Created by tosulc on 31.05.2016..
 */
public class LocationHelper {

    public static void startLocationGettingProcess(Context c) {
        CarelineApplication.checkForGoogleApi(c);
        CarelineApplication.connectFusedProvider(c);
        CarelineApplication.findLocation(c);
    }

    public static Location getLastFoundLocation() {
        return CarelineApplication.lastGpsLocation;
    }

    /**
     * Method for checking if user have locations turned on. We check this when
     * location is needed and to warn the user that he has no locations services turned on!
     *
     * @return true if location is turned on
     */
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure
                        .getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure
                    .getString(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            //crashed the app on long wait, then lock and then unlock. Krešo noticed this and it was logged by splunk ->https://mint.splunk.com/dashboard/project/13e27042/errors/3950758157
            //Log.d("Repsly debug message", "Providers: " + locationProviders.toString());
            return !TextUtils.isEmpty(locationProviders);
        }
    }

}
