package com.repsly.careline.utils.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alen on 31.5.2016..
 */

public abstract class CarelineDataBinder<T extends RecyclerView.ViewHolder> {

        public CarelineRecyclerAdapter dataBindAdapter;

        public CarelineDataBinder(CarelineRecyclerAdapter dataBindAdapter) {
            this.dataBindAdapter = dataBindAdapter;
        }

        /*
        This method is used to inflate specific layout that is used for binder
         */
        public View getView(ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(
                    provideLayout(), parent, false);
        }

        abstract public T newViewHolder(ViewGroup parent);

        abstract public void bindViewHolder(T holder, int position);

        abstract public int getItemCount();

        public abstract int provideLayout();

}
