package com.repsly.careline.helpers.gps;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.repsly.careline.CarelineApplication;

/**
 * Created by tosulc on 31.05.2016..
 */
public class FusedProvider implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleApiClient googleApiClient;
    private Location lastKnownLocation;
    private final LocationRequest locationRequest;
    private static final long POLLING_FREQ = 1000; //1 second
    private static final long FASTEST_UPDATE_FREQ = 1000; //1 second
    private static int locationUpdateTry = 1; //when requesting location, every second comes the result and we're incrementing this counter (see in onLocationChanged())
    private static final int LOCATION_UPDATE_TRY_MAX = 15; //after 15 tries, remove requesting of location

    public FusedProvider() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(POLLING_FREQ);
        locationRequest.setFastestInterval(FASTEST_UPDATE_FREQ);
    }

    public boolean checkForGoogleApi(Context context) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultGoogleApiAvailability = apiAvailability.isGooglePlayServicesAvailable(context);
        /*boolean result = (resultGoogleApiAvailability == ConnectionResult.SUCCESS);
        if(!result){
            Mint.logException(new Exception("Google api not available"));
        }*/
        if (resultGoogleApiAvailability == ConnectionResult.SUCCESS) {
            return true;
        } else {
            return false;
        }
    }

    public void connect(Context context) {
        if (googleApiClient == null) {
            buildGoogleApiClient(context);
        }
        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    public void disconnect() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                locationUpdateTry = 1;
                googleApiClient.disconnect();
            }
        }
    }

    public Location getLocation(Context context) {
        if (googleApiClient.isConnected()) {
            lastKnownLocation = CarelineApplication.getLastGpsLocation();

            if (lastKnownLocation != null) {
                int MAX_AGE_OF_READOUT = 300 * 1000;
                if ((System.currentTimeMillis() - lastKnownLocation
                        .getTime()) > MAX_AGE_OF_READOUT) {
                    //start getting of location
                    startGettingOfLocation(context);
                } else {
                    //do nothing
                }
            } else {
                startGettingOfLocation(context);
            }
        }
        return lastKnownLocation;
    }

    @SuppressWarnings("ResourceType") //it's check in outer classes
    private void startGettingOfLocation(Context context) {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                if (LocationHelper.isLocationEnabled(context)) {
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest,
                                                    FusedProvider.this);
                    //} else { Toast.makeText(getContext(), "Turn on location services!", Toast.LENGTH_SHORT).show(); }
                }
            }
        }

    }

    private synchronized void buildGoogleApiClient(Context context) {
        GoogleApiClient.Builder builderApiClient = new GoogleApiClient.Builder(context);
        builderApiClient.addConnectionCallbacks(this);
        builderApiClient.addOnConnectionFailedListener(this);
        builderApiClient.addApi(LocationServices.API);
        googleApiClient = builderApiClient.build();
    }

    @SuppressWarnings("ResourceType") //permission check in outer class
    @Override
    public void onConnected(Bundle connectionHint) {
        //old way of fetching current location
        //location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        //start constant updating of gps location until you found the "satisfying" one
        //doing this on starting of the app, to find as accurate as possible the location of rep
        LocationServices.FusedLocationApi
                .requestLocationUpdates(googleApiClient, locationRequest, this);
        locationUpdateTry = 1;
        Log.d("Repsly debug message", "OnConnected started. Requesting location updates active!");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            if (locationUpdateTry <= LOCATION_UPDATE_TRY_MAX) {
                if (location.getAccuracy() > 200) {
                    //do nothing, carry on, tra la la
                    locationUpdateTry++;
                } else if (location.getAccuracy() > 50 && location.getAccuracy() < 200) {
                    CarelineApplication.setLastGpsLocation(location);
                    locationUpdateTry++;
                } else if (location.getAccuracy() < 50) {
                    //stop tracking and save that location to last known
                    locationUpdateTry = 1;
                    LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                    CarelineApplication.setLastGpsLocation(location);
                }
            } else {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                locationUpdateTry = 1;
            }
        } else {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }
}
