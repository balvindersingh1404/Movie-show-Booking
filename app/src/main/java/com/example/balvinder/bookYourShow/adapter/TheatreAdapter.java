package com.example.balvinder.bookYourShow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.theatreData.C;
import com.example.balvinder.bookYourShow.model.theatreData.M;
import com.example.balvinder.bookYourShow.util.MyTextView;

import java.util.List;


public class TheatreAdapter extends ArrayAdapter<List<C>> {
    Context context;
   List<C> list;


    public TheatreAdapter(Context context, List<C> List) {
        super(context, R.layout.theatre_layout);
        this.list=List;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(list!=null)
        return list.size();
        else return  0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        C data = list.get(position);
        ViewHolder holder=null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.theatre_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(data!=null){
            if(data.getN()!=null && !data.getN().isEmpty()) {
                holder.title.setText(data.getN());
            }
            else{
                holder.title.setVisibility(View.INVISIBLE);
            }
            if(data.getD()!=null) {
                holder.distance.setText(data.getD());
            }
            else{
                holder.distance.setVisibility(View.INVISIBLE);
            }
            List<M> movieList = data.getM();

            if(movieList!=null &&movieList.size()>0){
                if(movieList.size()==1){
                    holder.theatre1.setText(movieList.get(0).getN());
                    holder.theatre1.setVisibility(View.VISIBLE);
                    holder.theatre2.setVisibility(View.GONE);
                    holder.theatre3.setVisibility(View.GONE);

                }
                else if(movieList.size()==2){
                    holder.theatre1.setText(movieList.get(0).getN());
                    holder.theatre2.setText(movieList.get(1).getN());
                    holder.theatre1.setVisibility(View.VISIBLE);
                    holder.theatre2.setVisibility(View.VISIBLE);
                    holder.theatre3.setVisibility(View.GONE);

                } else if(movieList.size()==3){
                    holder.theatre1.setText(movieList.get(0).getN());
                    holder.theatre2.setText(movieList.get(1).getN());
                    holder.theatre3.setText(movieList.get(2).getN());
                    holder.theatre1.setVisibility(View.VISIBLE);
                    holder.theatre2.setVisibility(View.VISIBLE);
                    holder.theatre3.setVisibility(View.VISIBLE);
                }
            }
        }
        return convertView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private MyTextView theatre1,theatre2,theatre3,distance;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            title=(TextView) itemLayoutView.findViewById(R.id.name);
            theatre1=(MyTextView) itemLayoutView.findViewById(R.id.theatre1);
            theatre2=(MyTextView) itemLayoutView.findViewById(R.id.theatre2);
            theatre3=(MyTextView) itemLayoutView.findViewById(R.id.theatre3);
            distance=(MyTextView) itemLayoutView.findViewById(R.id.distance);

        }
    }

}