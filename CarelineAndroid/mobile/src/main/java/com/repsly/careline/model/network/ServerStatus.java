package com.repsly.careline.model.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 01.06.2016..
 */
public class ServerStatus {
    @SerializedName("status")
    @Expose
    public int status;

    public int getStatus() {
        return status;
    }
}
