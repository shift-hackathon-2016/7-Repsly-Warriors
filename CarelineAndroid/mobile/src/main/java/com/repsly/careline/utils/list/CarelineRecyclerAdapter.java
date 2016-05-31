package com.repsly.careline.utils.list;

import android.content.Context;

import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.helpcenter.HelpItemViewBinder;
import com.repsly.utils.lib.list.DataBinder;
import com.repsly.utils.lib.list.ListItem;

import java.util.List;

/**
 * Created by Alen on 31.5.2016..
 */
public class CarelineRecyclerAdapter<T extends ListItem> extends CarelineDataBinderAdapter {

    private List<T> items;
    private Context activity;

    public CarelineRecyclerAdapter(Context activity, List<T> items) {
        super();
        this.items = items;
        this.activity = activity;
    }

    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    /*
    We override method from RecyclerView.Adapter to use only one adapter for every list.
    Now we can custom setup ids for ViewType in Repsly app.
    Considering this method result class will know which binder to use.
     */
    @Override
    public int getItemViewType(int position) {
       if (items.get(position) instanceof HelpCenterItem) {
            return HELP_ITEM;
        }
        return 0;
    }


    @Override
    public <T extends CarelineDataBinder> T getDataBinder(int viewType) {
       switch (viewType) {
            case HELP_ITEM:
                return (T) new HelpItemViewBinder(this);
            default:
                return null;
        }
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        return binderPosition;
    }

    @Override
    public int getBinderPosition(int position) {
        return position;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

}
