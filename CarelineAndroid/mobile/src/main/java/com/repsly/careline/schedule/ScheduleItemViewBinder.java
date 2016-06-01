package com.repsly.careline.schedule;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repsly.careline.R;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.model.ReminderScheduleItem;
import com.repsly.careline.utils.list.AdapterItem;
import com.repsly.careline.utils.list.CarelineDataBinder;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;

/**
 * Created by Alen on 1.6.2016..
 */
public class ScheduleItemViewBinder extends CarelineDataBinder<ScheduleItemViewBinder.ViewHolder> {

    public ScheduleItemViewBinder(
            CarelineRecyclerAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        return new ViewHolder(getView(parent));
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.findViews(position);
        holder.fillDate((ReminderScheduleItem) dataBindAdapter.getItem(position));

    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }


    @Override
    public int provideLayout() {
        return R.layout.schedule_list_item;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements
            AdapterItem<ReminderScheduleItem> {

        private View view;
        private TextView name;
        private TextView quantity;
        private TextView time;
        private View crta;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @Override
        public void findViews(int position) {
            name = (TextView) view.findViewById(R.id.tvMedName);
            quantity = (TextView) view.findViewById(R.id.tvQuantity);
            time = (TextView) view.findViewById(R.id.tvTime);
            crta = (View) view.findViewById(R.id.vCrta);
        }

        @Override
        public void fillDate(ReminderScheduleItem model) {
            name.setText(model.name);
            if(model.quantity.equals("1")){
                quantity.setText(model.quantity+" pill");
            }else {
                quantity.setText(model.quantity + " pills");
            }
            time.setText(DateTimeUtil.toShowTIme(DateTimeUtil.fromISODate(model.dateTime)));
            if(model.medColor!=null) {
                crta.setBackgroundColor(Color.parseColor(model.medColor));
            }
        }
    }
}


