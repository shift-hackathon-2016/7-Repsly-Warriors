package com.repsly.careline.helpers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.repsly.careline.AlarmReceiver;
import com.repsly.careline.MovementReceiver;
import com.repsly.careline.services.MovementService;

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
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, i);

    }

    public static void setAlarmForMovementTracking(Context c) {

        Intent i = new Intent(c, MovementReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(c, 0, i, 0);
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi); //we stop the previous alarms so there won't be any alarms working in parallel
        //TODO do not hardcode this! For now, every minute, send!
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000,
                        60000, pi);
        Intent service = new Intent(c, MovementService.class);
        c.startService(service);
    }

    //TODO implement recurring alarms?

}
