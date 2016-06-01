package com.repsly.careline.activities;

import android.hardware.SensorEvent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.repsly.careline.R;
import com.repsly.careline.database.DbHelper;
import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.helpers.Constants;
import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.receivers.ReceiverListItem;
import com.repsly.careline.retrofit.ApiCarelineImpl;
import com.repsly.careline.utils.MotionListener;
import com.repsly.careline.utils.MovementDetector;
import com.repsly.careline.utils.ViewHelper;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;
import com.tumblr.remember.Remember;

import java.util.List;

/**
 * Created by Alen on 31.5.2016..
 */
public class HomeGiverActivity extends CarelineActivity {

    private RecyclerView recyclerView;
    private CarelineRecyclerAdapter<CareReceiver> adapter;

    @Override
    public void reference() {
        recyclerView = (RecyclerView) findViewById(R.id.rvReceivers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
        return R.layout.home_giver_activity;
    }

    @Override
    public void main() {
        new Async().execute();
    }

    private class Async extends AsyncTask<Void, Void, List<CareReceiver>> {

        @Override
        protected void onPostExecute(List<CareReceiver> list) {
            super.onPostExecute(list);
            DbHelper db=new DbHelper(HomeGiverActivity.this);
            for (CareReceiver cr : list) {
                db.saveCareReceiver(cr);
                Log.d("Repsly debug message", "Item: " + cr.name);
            }
            adapter = new CarelineRecyclerAdapter<>(HomeGiverActivity.this, list);
            recyclerView.setAdapter(adapter);

        }


        @Override
        protected List<CareReceiver> doInBackground(Void... params) {
            ApiCarelineImpl service = new ApiCarelineImpl().buildInterceptor()
                                                           .addAuthHeader(Remember.getString(
                                                                   Constants.LOGIN_DATA, ""));
            return service.getRecevierList();
        }
    }
}
