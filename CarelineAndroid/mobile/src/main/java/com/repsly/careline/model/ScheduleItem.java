package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ScheduleItem {
    @SerializedName("rowId")
    @Expose
    public String rowId;
    @SerializedName("medicineId")
    @Expose
    public String medicineId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("quantity")
    @Expose
    public int quantity;
}
