package com.repsly.careline.helpers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tosulc on 31.05.2016..
 */
public class AlarmHelper {

    /**
     * Start alarm in 5 seconds.
     *
     * @param c
     * @param i
     */
    public static void setOneTimeAlarm(Context c, PendingIntent i) {
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, i);
    }

    /**
     * Set alarm for given date.
     */
    public static void setOneTimeAlarmOnDate(Context c, PendingIntent i, Date d) {
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), i);

    }

    //TODO implement recurring alarms?

}
