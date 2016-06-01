package com.repsly.careline;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.repsly.careline.helpers.AlarmHelper;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO get schedules for today and set alarms!

    }

    @OnClick(R.id.dada) void onDadaClick() {
        /*new Async().execute();
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        User user = dbHelper.getUser("");
        Toast.makeText(getApplicationContext(), "User: " + user.isManager(), Toast.LENGTH_SHORT).show();*/
    }

    private class Async extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void list) {
            super.onPostExecute(list);
            Log.d("Repsly debug message", "Dobio sam resposne!");
        }


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

}
