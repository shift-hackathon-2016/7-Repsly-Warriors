package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tosulc on 31.05.2016..
 */
public class Schedule {
    @SerializedName("ScheduleRowid")
    @Expose
    public String id;
    @SerializedName("ScheduleDateTime")
    @Expose
    public String dateTime;
    @SerializedName("ScheduleItemList")
    @Expose
    public ArrayList<ScheduleItem> scheduleItems = new ArrayList<>();
    @SerializedName("Note")
    @Expose
    public String note;

}
