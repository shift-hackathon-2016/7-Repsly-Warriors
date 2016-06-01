package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 31.05.2016..
 */
public class ScheduleItem {
    @SerializedName("ScheduleItemRowId")
    @Expose
    public String id;
    @SerializedName("MedicineID")
    @Expose
    public String medicineId;
    @SerializedName("MedicineRowId")
    @Expose
    public String medicineRowId;
    @SerializedName("ItemName")
    @Expose
    public String name;
    @SerializedName("Quantity")
    @Expose
    public int quantity;

    public String scheduleId;
}
