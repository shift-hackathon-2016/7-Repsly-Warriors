package com.repsly.careline.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.repsly.careline.R;
import com.repsly.careline.ReminderActivity;

/**
 * Created by tosulc on 31.05.2016..
 */
public class NotificationHelper {

    public static void buildNotification(Context context, String contentTitle, String contentText,
                                         String tickerText) {
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.icone).setTicker(tickerText)
                .setContentTitle(contentTitle).setContentText(contentText).setAutoCancel(true);
        Uri sound = Uri
                .parse("android.resource://" + context.getPackageName() + "/raw/bluepill");
        builder.setSound(sound);
        Intent i = new Intent(context, ReminderActivity.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.addAction(R.mipmap.icone, "Drink pills", pi);
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
