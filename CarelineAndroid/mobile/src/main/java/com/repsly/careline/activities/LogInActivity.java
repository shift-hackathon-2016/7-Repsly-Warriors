package com.repsly.careline.activities;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.repsly.careline.CarelineApplication;
import com.repsly.careline.MainActivity;
import com.repsly.careline.R;
import com.repsly.careline.database.DbHelper;
import com.repsly.careline.helpers.AuthHelper;
import com.repsly.careline.helpers.Constants;
import com.repsly.careline.interfaces.ILogin;
import com.repsly.careline.model.network.UserData;
import com.repsly.careline.retrofit.ApiCarelineImpl;
import com.repsly.utils.lib.activities.abstracts.LogInAbstract;
import com.tumblr.remember.Remember;

/**
 * Created by Alen on 31.5.2016..
 */
public class LogInActivity extends CereLogInAbstract implements ILogin {

    @Override
    public void referenceItems() {
        submit = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void submitAction() {
        //TODO uncomment this!
        /*String credentials = username + ":" + password;
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);*/
        if (validateData()) {
            String data = AuthHelper.encodeBase64String(
                    username.getText().toString() + ":" + password.getText().toString());
            ApiCarelineImpl service = new ApiCarelineImpl()
                    .buildInterceptor().addAuthHeader(data);
            service.sendLoginData(this);
        } else {
            Toast.makeText(getApplicationContext(), "You need to input data to log in.",
                           Toast.LENGTH_SHORT).show();
        }
        /*if(validateData()) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }*/

    }

    @Override
    public int provideLayout() {
        return R.layout.log_in_activity;
    }

    @Override
    public void LoginResult(UserData userData) {
        if (userData != null) {
            DbHelper dbHelper = CarelineApplication.getDbHandler();
            dbHelper.saveUser(userData);
            Remember.putBoolean(Constants.LOGGED_IN, true);
            Remember.putBoolean(Constants.IS_MANAGER, userData.manager);
            Remember.putString(Constants.LOGIN_DATA,
                               AuthHelper
                                       .encodeBase64String(
                                               username.getText().toString() + ":" + password
                                                       .getText().toString()));
            Intent i;
            if (userData.manager) {
                i = new Intent(getApplicationContext(), HomeGiverActivity.class);
            } else {
                i = new Intent(getApplicationContext(), HomeReceiverActivity.class);
            }
            startActivity(i);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
