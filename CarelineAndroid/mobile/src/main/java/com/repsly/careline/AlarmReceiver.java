package com.repsly.careline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.repsly.careline.helpers.NotificationHelper;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by tosulc on 31.05.2016..
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        if (b != null) {
            boolean v1 = b.getBoolean("v1");
            boolean v2 = b.getBoolean("v2");
            if (v2) {
                //TODO get title and other data from intent!
                NotificationHelper.buildNotification(context, "Title", "Texts", "tekst2");
                Intent i = new Intent(context, ReminderActivity.class);
                i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } else if (v1) {
                Log.d("Repsly debug message", "0 bodova");
            }
        }
    }
}
