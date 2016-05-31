package com.repsly.careline.helpcenter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.repsly.careline.R;
import com.repsly.careline.utils.list.AdapterItem;
import com.repsly.careline.utils.list.CarelineDataBinder;
import com.repsly.careline.utils.list.CarelineRecyclerAdapter;
import com.repsly.utils.lib.device.CallUtil;

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
            if (model.getType().equals(HelpCenterItemType.CALL)) {
                view.setOnClickListener(callListener);
            }else if(model.getType().equals(HelpCenterItemType.NAVIGATION)){
                view.setOnClickListener(mapListener);
            }
        }

        private View.OnClickListener callListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CallUtil.isCallEnabled(dataBindAdapter.getActivity())) {
                    CallUtil.startCall(dataBindAdapter.getActivity(),
                                       description.getText().toString());
                }
            }
        };

        private View.OnClickListener mapListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=43.5196138,16.4143467&mode=w");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                dataBindAdapter.getActivity().startActivity(mapIntent);
            }
        };


    }

}
