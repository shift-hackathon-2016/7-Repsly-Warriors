package com.repsly.careline.utils.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.repsly.utils.lib.list.DataBinder;

/**
 * Created by Alen on 31.5.2016..
 */
public abstract class CarelineDataBinderAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final static int HELP_ITEM = 1;
    final static int RECEIVER_ITEM = 2;
    final static int SCHEDULE_ITEM = 3;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).newViewHolder(parent);
    }

    /*
    This method will take specific binder with getDataBinder() (considering viewType) that we need
    for item in recyclerview and then will call bindViewHolder method in specific binder that will
    call findViews and fillData.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int binderPosition = getBinderPosition(position);
        if (getDataBinder(viewHolder.getItemViewType()) != null) {
            getDataBinder(viewHolder.getItemViewType()).bindViewHolder(viewHolder, binderPosition);
        }
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    protected abstract <T extends CarelineDataBinder> T getDataBinder(int viewType);

    public abstract int getPosition(DataBinder binder, int binderPosition);

    protected abstract int getBinderPosition(int position);
}

