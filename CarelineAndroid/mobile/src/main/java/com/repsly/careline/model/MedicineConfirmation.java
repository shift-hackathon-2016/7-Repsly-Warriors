package com.repsly.careline.model;

/**
 * Created by tosulc on 31.05.2016..
 */
public class MedicineConfirmation {

    public String ScheduleItemRowid;
    public String ConfirmationDateTime;

    public MedicineConfirmation(String scheduleItemRowId, String dateTime) {
        this.ScheduleItemRowid = scheduleItemRowId;
        this.ConfirmationDateTime = dateTime;
    }
}
