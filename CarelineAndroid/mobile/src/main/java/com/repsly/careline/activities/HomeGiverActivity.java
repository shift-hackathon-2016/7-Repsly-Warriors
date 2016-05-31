package com.repsly.careline.activities;

import android.hardware.SensorEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.repsly.careline.R;
import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.receivers.ReceiverListItem;
import com.repsly.careline.utils.MotionListener;
import com.repsly.careline.utils.MovementDetector;
import com.repsly.careline.utils.ViewHelper;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;

/**
 * Created by Alen on 31.5.2016..
 */
public class HomeGiverActivity extends CarelineActivity {

    private RecyclerView recyclerView;
    private CarelineRecyclerAdapter<ReceiverListItem> adapter;


    @Override
    public void reference() {
        recyclerView = (RecyclerView) findViewById(R.id.rvReceivers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CarelineRecyclerAdapter<>(this, ViewHelper.getReceivers());
        recyclerView.setAdapter(adapter);

        /*
        MovementDetector.getInstance(this).addListener(new MotionListener() {

            @Override
            public void onMotionDetected(SensorEvent event, float acceleration) {

            }
        });

        MovementDetector.getInstance(this).start();
        */
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

    }
}
