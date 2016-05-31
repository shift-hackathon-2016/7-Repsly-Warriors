package com.repsly.careline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.repsly.careline.Model.User;
import com.repsly.careline.database.DbHelper;

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
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        User user = dbHelper.getUser("fasdfasd");
        Toast.makeText(getApplicationContext(), "User got: " + user.getName() + " is manager: " + user.isManager(), Toast.LENGTH_SHORT).show();
    }

}
