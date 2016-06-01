package com.repsly.careline;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.repsly.careline.database.DbHelper;
import com.repsly.careline.helpers.AnimationHelper;
import com.repsly.careline.helpers.Constants;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.model.MedicineConfirmation;
import com.repsly.careline.model.ReminderScheduleItem;
import com.repsly.careline.retrofit.ApiCarelineImpl;
import com.tumblr.remember.Remember;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ReminderActivity extends Activity {

    @BindView(R.id.ll_reminder_acitivity)
    LinearLayout llReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_activity);
        ButterKnife.bind(this);

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        ArrayList<ReminderScheduleItem> list = dbHelper.getScheduleForToday();

        llReminder.removeAllViews();
        final int[] numberOfItems = {list.size()};
        for (final ReminderScheduleItem rsi : list) {
            LinearLayout llReminderItem = (LinearLayout) getLayoutInflater()
                    .inflate(R.layout.reminder_item, llReminder, false);
            //TODO implement some random color chooser!
            if (rsi.name.equals("Lupocet")) {
                llReminderItem.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            } else {
                llReminderItem
                        .setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            TextView tvNameOfMedicine = (TextView) llReminderItem
                    .findViewById(R.id.tv_name_of_medicine);
            Button btnSwallow = (Button) llReminderItem.findViewById(R.id.btn_swallow);
            tvNameOfMedicine.setText(rsi.name);
            btnSwallow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnimationHelper.fadeOutView((LinearLayout) v.getParent(), 1.0f, 0);
                    --numberOfItems[0];
                    if (numberOfItems[0] == 0) {
                        ApiCarelineImpl service = new ApiCarelineImpl().buildInterceptor()
                                                                       .addAuthHeader(
                                                                               Remember.getString(
                                                                                       Constants.LOGIN_DATA,
                                                                                       ""));
                        service.sendMedicineConfirmation(new MedicineConfirmation(rsi.medicineRowId,
                                                                                  DateTimeUtil
                                                                                          .toISODate(
                                                                                                  new Date())));

                        Toast.makeText(getApplicationContext(),
                                       "You did it, now we will leave from this page!",
                                       Toast.LENGTH_LONG).show();
                        NotificationManager nm = (NotificationManager) getApplicationContext()
                                .getSystemService(Context.NOTIFICATION_SERVICE);
                        nm.cancel(1);
                        showSuccessMessageAndExit();
                    }
                }
            });
            llReminder.addView(llReminderItem);
        }
    }


    public void showSuccessMessageAndExit() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
