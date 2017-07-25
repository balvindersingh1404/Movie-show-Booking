package com.example.balvinder.bookYourShow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.Offer_data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OfferAdapter extends ArrayAdapter<ArrayList<Offer_data>> {
    Context context;
   ArrayList<Offer_data> arrayList;


    public OfferAdapter(Context context, ArrayList<Offer_data> arrayList) {
        super(context, R.layout.offers_layout);
        this.arrayList=arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Offer_data data = arrayList.get(position);
        ViewHolder holder=null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.offers_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(data!=null){
            if(data.getI()!=null &&!TextUtils.isEmpty(data.getI()))
            Picasso.with(context).load(data.getI()).into(holder.thumbnail);
            holder.name.setText(data.getT());
            holder.offer.setText(data.getC());
            holder.offerdetail.setText(data.getD());

        }
        return convertView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView thumbnail;
        private TextView name,offer,offerdetail;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            thumbnail = (ImageView) itemLayoutView.findViewById(R.id.image);
            name=(TextView) itemLayoutView.findViewById(R.id.name);
            offer=(TextView) itemLayoutView.findViewById(R.id.offer);
            offerdetail=(TextView) itemLayoutView.findViewById(R.id.offerdetail);

        }
    }

}