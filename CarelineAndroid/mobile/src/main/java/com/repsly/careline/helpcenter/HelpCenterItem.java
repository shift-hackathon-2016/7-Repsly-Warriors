package com.repsly.careline.helpcenter;

import com.repsly.utils.lib.list.ListItem;

/**
 * Created by Alen on 31.5.2016..
 */
public class HelpCenterItem extends ListItem {

    private String title;
    private String description;
    private int image;

    public HelpCenterItem(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
