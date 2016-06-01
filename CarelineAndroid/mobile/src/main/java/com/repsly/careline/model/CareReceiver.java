package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 01.06.2016..
 */
public class CareReceiver {
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("Avatar")
    @Expose
    public String avatar;
    @SerializedName("Note")
    @Expose
    public String note;

}
