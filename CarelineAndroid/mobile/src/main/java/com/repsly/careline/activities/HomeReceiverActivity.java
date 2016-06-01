package com.repsly.careline.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.repsly.careline.AlarmReceiver;
import com.repsly.careline.CarelineApplication;
import com.repsly.careline.R;
import com.repsly.careline.database.DbHelper;
import com.repsly.careline.helpers.AlarmHelper;
import com.repsly.careline.helpers.Constants;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.helpers.gps.LocationHelper;
import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.model.Medicine;
import com.repsly.careline.model.Schedule;
import com.repsly.careline.retrofit.ApiCarelineImpl;
import com.tumblr.remember.Remember;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            setTitle("Careline");
        }
    }

    @Override
    public int provideLayout() {
        return R.layout.home_receiver_activity;
    }

    @Override
    public void main() {
        //TODO check if already downloaded this!
        String todayDate = DateTimeUtil.toDateOnlyFormat(new Date());
        String dateOfDownload = Remember.getString("dateOfDownload", "");
        if (!todayDate.equals(dateOfDownload)) {
            new Async().execute();
        }
    }

    private class Async extends AsyncTask<Void, Void, List<Schedule>> {

        @Override
        protected void onPostExecute(List<Schedule> list) {
            super.onPostExecute(list);
            DbHelper dbHelper = CarelineApplication.getDbHandler();
            dbHelper.saveSchedules(list);
            for (int i = 0; i < list.size(); i++) {
                Schedule schedule = list.get(0);
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("title", schedule.note);
                PendingIntent pi = PendingIntent
                        .getBroadcast(getApplicationContext(), i, intent, 0);
                Date d = DateTimeUtil.fromISODate(schedule.dateTime);
                AlarmHelper.setOneTimeAlarmOnDate(getApplication(), pi, d);
            }
            new AsyncMedicine().execute();
        }

        @Override
        protected List<Schedule> doInBackground(Void... params) {
            ApiCarelineImpl service = new ApiCarelineImpl().buildInterceptor()
                                                           .addAuthHeader(Remember.getString(
                                                                   Constants.LOGIN_DATA, ""));
            return service.getSchedules();
        }
    }

    private class AsyncMedicine extends AsyncTask<Void, Void, List<Medicine>> {

        @Override
        protected void onPostExecute(List<Medicine> medicines) {
            super.onPostExecute(medicines);
            DbHelper dbHelper = CarelineApplication.getDbHandler();
            dbHelper.saveMedicines(medicines);
            Remember.putString("dateOfDownload", DateTimeUtil.toDateOnlyFormat(new Date()));
        }

        @Override
        protected List<Medicine> doInBackground(Void... voids) {
            ApiCarelineImpl service = new ApiCarelineImpl().buildInterceptor()
                                                           .addAuthHeader(Remember.getString(
                                                                   Constants.LOGIN_DATA, ""));
            return service.getMedicine();
        }
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
        i.putExtra("v1", true);
        PendingIntent pi = PendingIntent
                .getBroadcast(getApplicationContext(), 1, i, 0); //TODO REQUEST CODE IS KEY HERE!
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MILLISECOND, 5000);
        AlarmHelper.setOneTimeAlarmOnDate(getApplication(), pi, cal.getTime());
    }

    @OnClick(R.id.btnAlarmV2)
    void onBtnAlarmClickV2() {
        Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
        i.putExtra("v2", true);
        PendingIntent pi = PendingIntent
                .getBroadcast(getApplicationContext(), 2, i, 0); //TODO REQUEST CODE IS KEY HERE!
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MILLISECOND, 7000);
        AlarmHelper.setOneTimeAlarmOnDate(getApplication(), pi, cal.getTime());
    }

    @OnClick(R.id.btnLocation)
    void getLocation() {
        Location lastGpsLocation = LocationHelper.getLastFoundLocation();
        if (lastGpsLocation != null) {
            Toast.makeText(getApplicationContext(),
                           "Latitude: " + lastGpsLocation
                                   .getLatitude() + ", longitude: " + lastGpsLocation
                                   .getLongitude(), Toast.LENGTH_SHORT).show();
            Log.d("Repsly debug message",
                  "Latitude: " + lastGpsLocation.getLatitude());
            Log.d("Repsly debug message",
                  "Longitude: " + lastGpsLocation.getLongitude());
        } else {
            Toast.makeText(getApplicationContext(), "Location null!", Toast.LENGTH_SHORT).show();
        }
    }

}
