package com.repsly.careline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.repsly.utils.lib.list.ListItem;

/**
 * Created by tosulc on 01.06.2016..
 */
public class CareReceiver extends ListItem {

    @SerializedName("UserId")
    @Expose
    public int userId;

    @SerializedName("LastMovementDateTime")
    @Expose
    public String lastMovement;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(String lastMovement) {
        this.lastMovement = lastMovement;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
