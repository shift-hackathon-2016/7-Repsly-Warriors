package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tosulc on 31.05.2016..
 */
public class User {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("isManager")
    @Expose
    public boolean isManager;

    public User() {
    }

    public User(String id, String name, String address, boolean isManager) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isManager = isManager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(String manager) {
        isManager = (manager.equals("1"));
    }
}
