package com.repsly.careline.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.repsly.careline.AlarmReceiver;
import com.repsly.careline.R;
import com.repsly.careline.helpers.AlarmHelper;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alen on 31.5.2016..
 */
public class HomeReceiverActivity extends CarelineActivity {

    @Override
    public void reference() {
        ButterKnife.bind(this);
    }

    @Override
    public void setUpToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            getSupportActionBar().setCustomView(null);
        }
    }

    @Override
    public int provideLayout() {
        return R.layout.home_receiver_activity;
    }

    @Override
    public void main() {

    }

    @OnClick(R.id.ibHelpCenter)
    void onHelp() {
        Intent intent = new Intent(getBaseContext(), HelpCenterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @OnClick(R.id.btnAlarm)
    void onBtnAlarmClick() {
        Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MILLISECOND, 3000);
        AlarmHelper.setOneTimeAlarmOnDate(getApplication(), pi, cal.getTime());
    }

}
