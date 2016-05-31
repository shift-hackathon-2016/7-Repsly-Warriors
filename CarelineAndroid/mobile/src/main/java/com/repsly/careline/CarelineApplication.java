package com.repsly.careline;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationResult;
import com.repsly.careline.helpers.gps.FusedProvider;
import com.tumblr.remember.Remember;

/**
 * Created by tosulc on 31.05.2016..
 */
public class CarelineApplication extends Application {

    private static FusedProvider fusedProvider;
    private static boolean googleApiAvailable = false;

    public static Location lastGpsLocation = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), "com.repsly.careline");
    }

    public static void checkForGoogleApi(Context context) {
        fusedProvider = new FusedProvider();
        boolean apiAvailable = fusedProvider.checkForGoogleApi(context);
        if (!apiAvailable) {
            googleApiAvailable = false;
            return;
        }
        googleApiAvailable = true;
    }

    public static void connectFusedProvider(Context context) {
        if (googleApiAvailable) {
            if (fusedProvider == null) {
                fusedProvider = new FusedProvider();
            }
            fusedProvider.connect(context);
        }
    }

    //TODO call this in onDestroy of some activity!
    public static void disconnectFusedProvider() {
        if (googleApiAvailable) {
            if (fusedProvider != null) {
                fusedProvider.disconnect();
                fusedProvider = null;
            }
        }
    }

    //TODO cover marginal cases! (when location is not available, but we have to send location to the server)
    public static void findLocation(Context c) {
        if (googleApiAvailable) {
            if (fusedProvider == null) {
                connectFusedProvider(c);
            }
            Location location = fusedProvider.getLocation(c);
            if (location != null) {
                lastGpsLocation = location;
            }
        }
        //TODO implement for no GPlay devices!
    }

    public static Location getLastGpsLocation() {
        return lastGpsLocation;
    }

    public static void setLastGpsLocation(Location lastGpsLocation) {
        CarelineApplication.lastGpsLocation = lastGpsLocation;
    }
}
