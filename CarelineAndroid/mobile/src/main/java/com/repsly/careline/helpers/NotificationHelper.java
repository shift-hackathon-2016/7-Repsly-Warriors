package com.repsly.careline.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by tosulc on 31.05.2016..
 */
public class NotificationHelper {

    public static void buildNotification(Context context, String contentTitle, String contentText,
                                         String tickerText) {
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.stat_notify_sync).setTicker(tickerText)
                .setContentTitle(contentTitle).setContentText(contentText);
        Uri sound = Uri
                .parse("android.resource://" + context.getPackageName() + "/raw/bbc_birds");
        builder.setSound(sound);
        Notification notification = build(builder);
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        //notification.defaults |= Notification.DEFAULT_SOUND;
        //notification.defaults |= Notification.DEFAULT_LIGHTS;
        nm.notify(1, notification);

    }

    public static Notification build(NotificationCompat.Builder builder) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return builder.getNotification();
        } else {
            return builder.build();
        }
    }

}
