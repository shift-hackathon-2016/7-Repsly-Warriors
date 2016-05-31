package com.repsly.careline.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.repsly.careline.R;

/**
 * Created by Alen on 31.5.2016..
 */
public abstract class CarelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayout());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar()!=null){
            setSupportActionBar(toolbar);
        }
        setUpToolbar();
        reference();
        main();
    }

    public abstract void main();
    public abstract void reference();
    public abstract void setUpToolbar();
    public abstract int provideLayout();
}
