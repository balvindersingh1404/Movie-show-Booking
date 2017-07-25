package com.example.balvinder.bookYourShow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.Location_data;

import java.util.List;

public class SimpleAdapter extends
        RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {

    private List<Location_data> list_item ;
    public Context mcontext;



    public SimpleAdapter(List<Location_data> list, Context context) {

        list_item = list;
        mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position ) {


        viewHolder.country_name.setText(list_item.get(position).getName());

        viewHolder.country_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView country_name;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            country_name = (TextView) itemLayoutView.findViewById(R.id.country_name);

        }
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

}