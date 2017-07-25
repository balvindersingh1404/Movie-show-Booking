package com.example.balvinder.bookYourShow.adapter;

/**
 * Created by balvinder on 7/12/16.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.Search_data;
import com.example.balvinder.bookYourShow.util.MyTextView;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<ArrayList<Search_data>> {

    Context context;
    ArrayList<Search_data> arrayList;
    public SearchAdapter(Context context, ArrayList<Search_data> arrayList) {
        super(context, R.layout.listitem);

        this.arrayList=arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Search_data data = arrayList.get(position);
        ViewHolder holder=null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listitem, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvEventName.setText(data.getN());

        return convertView;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public MyTextView tvEventName;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);


        }
    }

}