package com.repsly.careline.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.repsly.careline.MainActivity;
import com.repsly.careline.R;
import com.repsly.utils.lib.activities.abstracts.LogInAbstract;

/**
 * Created by Alen on 31.5.2016..
 */
public class LogInActivity extends LogInAbstract{

    @Override
    public void referenceItems() {
        submit = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void submitAction() {
        if(validateData()) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public int provideLayout() {
        return R.layout.log_in_activity;
    }
}
