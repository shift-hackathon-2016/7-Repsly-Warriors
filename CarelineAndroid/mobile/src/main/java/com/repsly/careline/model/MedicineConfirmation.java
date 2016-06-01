package com.repsly.careline.model;

/**
 * Created by tosulc on 31.05.2016..
 */
public class MedicineConfirmation {

    public String scheduleItemRowId;
    public String dateTime;

    public MedicineConfirmation(String scheduleItemRowId, String dateTime) {
        this.scheduleItemRowId = scheduleItemRowId;
        this.dateTime = dateTime;
    }
}
