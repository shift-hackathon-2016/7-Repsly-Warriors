package com.repsly.careline.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

/**
 * Created by Alen on 1.6.2016..
 */
public class GeocoderHelper {

    public static Address getFromAddress(Context context, String address){
        Geocoder geocoder = new Geocoder(context);
        try {
            return geocoder.getFromLocationName(address,1).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
