package com.repsly.careline.receivers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.repsly.careline.R;
import com.repsly.careline.helpcenter.HelpCenterItem;
import com.repsly.careline.helpcenter.HelpCenterItemType;
import com.repsly.careline.utils.list.AdapterItem;
import com.repsly.careline.utils.list.CarelineDataBinder;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;
import com.repsly.utils.lib.device.CallUtil;

import org.w3c.dom.Text;

/**
 * Created by Alen on 31.5.2016..
 */
public class ReceiverItemViewBinder  extends CarelineDataBinder<ReceiverItemViewBinder.ViewHolder> {


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
        holder.fillDate((ReceiverListItem) dataBindAdapter.getItem(position));

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
            AdapterItem<ReceiverListItem> {

        private View view;
        private TextView tvName;
        private TextView tvDescription;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @Override
        public void findViews(int position) {
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            avatar = (ImageView) view.findViewById(R.id.ivAvatar);
        }

        @Override
        public void fillDate(ReceiverListItem model) {
            tvName.setText(model.getName());
            tvDescription.setText("last movement "+model.getLastMovement()+"min ago");
        }
    }

}
