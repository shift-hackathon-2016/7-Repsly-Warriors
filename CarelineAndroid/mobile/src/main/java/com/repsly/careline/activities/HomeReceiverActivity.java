package com.repsly.careline.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.repsly.careline.R;

/**
 * Created by Alen on 31.5.2016..
 */
public class HomeReceiverActivity extends CarelineActivity implements View.OnClickListener{


    @Override
    public void reference(){
        findViewById(R.id.ibHelpCenter).setOnClickListener(this);
    }

    @Override
    public void setUpToolbar() {
        if (getSupportActionBar()!=null){
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


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ibHelpCenter:
                Intent intent = new Intent(getBaseContext(), HelpCenterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
        }
    }
}
