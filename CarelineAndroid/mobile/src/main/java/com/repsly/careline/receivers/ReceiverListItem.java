package com.repsly.careline.receivers;

import com.repsly.utils.lib.list.ListItem;

/**
 * Created by Alen on 31.5.2016..
 */
public class ReceiverListItem extends ListItem {

    private String name;
    private String avatar;
    private String lastMovement;

    public ReceiverListItem(String name, String avatar, String lastMovement) {
        this.name = name;
        this.avatar = avatar;
        this.lastMovement = lastMovement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(String lastMovement) {
        this.lastMovement = lastMovement;
    }
}
