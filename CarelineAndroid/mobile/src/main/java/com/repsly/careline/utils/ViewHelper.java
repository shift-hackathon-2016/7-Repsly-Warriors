package com.repsly.careline.utils;

import com.repsly.careline.R;
import com.repsly.careline.helpcenter.HelpCenterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alen on 31.5.2016..
 */
public class ViewHelper {

    public static List<HelpCenterItem> getItemsForHelpCenter(){
        List<HelpCenterItem> items = new ArrayList<>();
        items.add(new HelpCenterItem("Call doctor", "+38566272", R.mipmap.ic_launcher));
        items.add(new HelpCenterItem("Call caregiver", "+38566272", R.mipmap.ic_launcher));
        items.add(new HelpCenterItem("Navigate home", "Velebitska 78, Split", R.mipmap.ic_launcher));
        items.add(new HelpCenterItem("Shout for help", "+instant chat", R.mipmap.ic_launcher));
        return  items;
    }
}
