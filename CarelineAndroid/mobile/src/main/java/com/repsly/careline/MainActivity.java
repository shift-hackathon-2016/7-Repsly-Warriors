package com.repsly.careline;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dada) void onDadaClick() {
        new Async().execute();
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
