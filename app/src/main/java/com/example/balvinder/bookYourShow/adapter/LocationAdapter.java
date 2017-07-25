package com.example.balvinder.bookYourShow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.activity.SelectLocationActivity;
import com.example.balvinder.bookYourShow.model.Location_data;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.MovieApplication;

import java.util.ArrayList;

/**
 * Created by balvinder on 9/12/16.
 */

public class LocationAdapter extends ArrayAdapter<ArrayList<Location_data>> {

    Context context;
    ArrayList<Location_data> arrayList;
    int TYPE_HEADER = 1;

    public LocationAdapter(Context context, ArrayList<Location_data> arrayList) {
        super(context, R.layout.row_menu_layout);
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (arrayList.get(position).getId() == "") {
            return TYPE_HEADER;
        } else return 0;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Location_data data = arrayList.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            if (getItemViewType(position) == TYPE_HEADER) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.section_header, null);
            } else {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem, null);
            }


            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (getItemViewType(position) == TYPE_HEADER) {
            holder.headerItemPcTextView.setText(data.getName());
        } else {
            holder.listItemPcTextView.setText(data.getName());

        }

        final ViewHolder finalHolder = holder;
        if (holder.listItemPcTextView != null) {
            holder.listItemPcTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MovieApplication.getPref().setString(AppPreferences.location, data.getName());
                    MovieApplication.getPref().setString(AppPreferences.id, data.getId());


                    ((SelectLocationActivity) context).finish();
                }
            });
        }
        return convertView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public MyTextView listItemPcTextView, headerItemPcTextView;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            listItemPcTextView = (MyTextView) itemLayoutView.findViewById(R.id.listItem);
            headerItemPcTextView = (MyTextView) itemLayoutView.findViewById(R.id.headerItem);

        }

    }

}
