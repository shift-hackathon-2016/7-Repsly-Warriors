package com.repsly.careline.utils;

import com.repsly.careline.R;
import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.helpcenter.HelpCenterItemType;
import com.repsly.careline.receivers.ReceiverListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alen on 31.5.2016..
 */
public class ViewHelper {

    public static List<HelpCenterItem> getItemsForHelpCenter(){
        List<HelpCenterItem> items = new ArrayList<>();
        items.add(new HelpCenterItem("Call doctor", "+385998877891", R.mipmap.icon_call,
                                     HelpCenterItemType.CALL));
        items.add(new HelpCenterItem("Call caregiver", "+385998877891", R.mipmap.icon_call,HelpCenterItemType.CALL));
        items.add(new HelpCenterItem("Navigate home", "Velebitska 78, Split", R.mipmap.icon_pin, HelpCenterItemType.NAVIGATION));
        items.add(new HelpCenterItem("Shout for help", "+instant chat", R.mipmap.icon_shout, HelpCenterItemType.INTERCOM));
        return  items;
    }

    public static List<ReceiverListItem> getReceivers(){
        List<ReceiverListItem> items = new ArrayList<>();
        items.add(new ReceiverListItem("Marko Varat", "slika","10"));
        items.add(new ReceiverListItem("Marko Varat", "slika","120"));
        items.add(new ReceiverListItem("Marko Varat", "slika","30"));
        return  items;
    }
}
