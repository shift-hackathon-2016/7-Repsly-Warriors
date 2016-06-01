package com.repsly.careline.helpers;

import android.util.Base64;

/**
 * Created by tosulc on 01.06.2016..
 */
public class AuthHelper {

    public static String encodeBase64String(String data) {
        return "Basic " + Base64.encodeToString(data.getBytes(), Base64.NO_WRAP);
    }

}
