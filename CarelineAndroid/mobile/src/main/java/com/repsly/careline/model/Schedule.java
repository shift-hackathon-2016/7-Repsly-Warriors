package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tosulc on 31.05.2016..
 */
public class Schedule {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("dateTime")
    @Expose
    public Date dateTime;
    @SerializedName("scheduleItems")
    @Expose
    public ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();
    @SerializedName("note")
    @Expose
    public String note;

}
