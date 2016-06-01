package com.repsly.careline.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tosulc on 31.05.2016..
 */
public class DateTimeUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public final static String DATE_FORMAT_SHOW = "EEE, MMM d, HH:mm";


    public static String toShowDate(Date d) {
        if (d == null) {
            d = new Date(System.currentTimeMillis());
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHOW, Locale.US);
        return sdf.format(d);
    }

    public static String toISODate(Date d) {
        if (d == null) {
            d = new Date(System.currentTimeMillis());
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return sdf.format(d);
    }

    public static Date fromISODate(String d) {
        if (d == null) {
            return new Date(System.currentTimeMillis());
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            return sdf.parse(d);
        } catch (ParseException e) {
            return new Date(System.currentTimeMillis());
        }
    }

}
