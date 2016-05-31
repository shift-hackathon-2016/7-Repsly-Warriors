package com.repsly.careline.helpcenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.repsly.careline.R;
import com.repsly.careline.utils.list.AdapterItem;
import com.repsly.careline.utils.list.CarelineDataBinder;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;

/**
 * Created by Alen on 31.5.2016..
 */
public class HelpItemViewBinder extends CarelineDataBinder<HelpItemViewBinder.ViewHolder> {


    public HelpItemViewBinder(CarelineRecyclerAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }


    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.findViews(position);
        holder.fillDate((HelpCenterItem) dataBindAdapter.getItem(position));

    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }


    @Override
    public int provideLayout() {
        return R.layout.help_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements
            AdapterItem<HelpCenterItem> {

        private View view;
        private TextView title;
        private TextView description;
        private ImageButton image;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @Override
        public void findViews(int position) {
            title = (TextView) view.findViewById(R.id.tvTitle);
            description = (TextView) view.findViewById(R.id.tvDescription);
            image = (ImageButton) view.findViewById(R.id.ibIcone);
        }

        @Override
        public void fillDate(HelpCenterItem model) {
            title.setText(model.getTitle());
            description.setText(model.getDescription());
        }
    }

    }
