package com.repsly.careline;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ReminderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_activity);

        //TODO fetch medications for drinking!
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);*/

    }
}
