package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 01.06.2016..
 */
public class Medicine {
    @SerializedName("MedicineID")
    @Expose
    public String id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("MedImg")
    @Expose
    public String medImg;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("MedColor")
    @Expose
    public String medColor;
    @SerializedName("MedType")
    @Expose
    public String medType;
    @SerializedName("Quantity")
    @Expose
    public String quantity;


}
