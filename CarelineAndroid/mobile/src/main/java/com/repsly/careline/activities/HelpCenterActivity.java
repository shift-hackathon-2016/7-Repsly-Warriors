package com.repsly.careline.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.repsly.careline.R;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;
import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.utils.ViewHelper;

/**
 * Created by Alen on 31.5.2016..
 */
public class HelpCenterActivity extends CarelineActivity {

    private RecyclerView rvHelpCenter;
    private CarelineRecyclerAdapter<HelpCenterItem> adapter;

    @Override
    public void main() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvHelpCenter.setLayoutManager(layoutManager);
        adapter = new CarelineRecyclerAdapter<>(this, ViewHelper.getItemsForHelpCenter());
        rvHelpCenter.setAdapter(adapter);
    }

    @Override
    public void reference() {
        rvHelpCenter = (RecyclerView) findViewById(R.id.rvHelpCenter);
    }

    @Override
    public void setUpToolbar() {
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setCustomView(null);
        }
        setTitle("Help center");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int provideLayout() {
        return R.layout.help_center;
    }
}
