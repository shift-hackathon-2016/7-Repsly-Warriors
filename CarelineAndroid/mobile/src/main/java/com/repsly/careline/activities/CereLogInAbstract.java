package com.repsly.careline.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alen on 1.6.2016..
 */
public abstract class CereLogInAbstract extends Activity {

    protected EditText username;
    protected EditText password;
    protected Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayout());
        referenceItems();
        submit.setOnClickListener(listener);
    }

    protected boolean validateData(){
        return username.getText()!=null
                && !username.getText().toString().equals("")
                && password.getText()!=null
                && !password.getText().toString().equals("");
    }



    public abstract void referenceItems();

    public abstract void submitAction();

    public abstract int provideLayout();

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitAction();
        }
    };
}

