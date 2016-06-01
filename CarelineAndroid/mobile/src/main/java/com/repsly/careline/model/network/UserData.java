package com.repsly.careline.model.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 01.06.2016..
 */
public class UserData {
    @SerializedName("AccountRowId")
    @Expose
    public String accountRowId;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("Avatar")
    @Expose
    public String avatar;
    @SerializedName("Manager")
    @Expose
    public boolean manager;
    /*
    {"UserId":0,"UserRowId":"48097ba9-be9c-4b3b-b5dc-6dbef6362fd3","AccountId":0,
    "AccountRowId":"bcf72d62-4cc3-4c4b-95e6-08a61f165a11","Name":"Boris Masnec",
    "Address":"S. Radića 96","Email":null,"Manager":false,"Avatar":""}
     */

}
