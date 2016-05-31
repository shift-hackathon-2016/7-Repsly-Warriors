package com.repsly.careline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.repsly.careline.helpers.NotificationHelper;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by tosulc on 31.05.2016..
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper.buildNotification(context, "Title", "Texts", "tekst2");
        Intent i = new Intent(context, ReminderActivity.class);
        i.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
