package com.repsly.careline.receivers;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.repsly.careline.R;
import com.repsly.careline.helpers.DateTimeUtil;
import com.repsly.careline.helpers.GeocoderHelper;
import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.utils.list.AdapterItem;
import com.repsly.careline.utils.list.CarelineDataBinder;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;

/**
 * Created by Alen on 31.5.2016..
 */
public class ReceiverItemViewBinder extends CarelineDataBinder<ReceiverItemViewBinder.ViewHolder> {


    public ReceiverItemViewBinder(
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
        holder.fillDate((CareReceiver) dataBindAdapter.getItem(position));

    }

    @Override
    public int getItemCount() {
        return dataBindAdapter.getItemCount();
    }


    @Override
    public int provideLayout() {
        return R.layout.receiver_list_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements
            AdapterItem<CareReceiver> {

        private View view;
        private TextView tvName;
        private TextView tvDescription;
        private ImageView avatar;
        private String address;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @Override
        public void findViews(int position) {
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            avatar = (ImageView) view.findViewById(R.id.ivAvatar);
            view.setOnClickListener(listener);
        }

        @Override
        public void fillDate(CareReceiver model) {
            tvName.setText(model.getName());
            tvDescription.setText("Last movement: " + DateTimeUtil
                    .getTimeAgo(DateTimeUtil.fromISODate(model.getLastMovement()),
                                dataBindAdapter.getActivity()));
            address = model.getAddress();
            avatar.setImageResource(R.mipmap.avatar_grandpa);
        }

        private View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Address adr = GeocoderHelper.getFromAddress(dataBindAdapter.getActivity(), address);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri
                        .parse("http://maps.google.com/maps?q=loc:" + adr.getLatitude() + "," + adr
                                .getLongitude()));
                dataBindAdapter.getActivity().startActivity(i);

            }
        };
    }

}
