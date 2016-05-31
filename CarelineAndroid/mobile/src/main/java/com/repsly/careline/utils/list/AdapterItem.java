package com.repsly.careline.utils.list;

import com.repsly.utils.lib.list.ListItem;

/**
 * Created by Alen on 31.5.2016..
 */
public interface AdapterItem<T extends ListItem> {

    void findViews(int position);

    void fillDate(T model);
}
