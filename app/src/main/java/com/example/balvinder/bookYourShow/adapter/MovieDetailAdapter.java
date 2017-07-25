package com.example.balvinder.bookYourShow.adapter;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.MovieDetail;

import java.util.ArrayList;

public class MovieDetailAdapter extends ArrayAdapter<ArrayList<MovieDetail>> {
    Context context;
    MovieDetail movieDetail;


    public MovieDetailAdapter(Context context, MovieDetail movieDetail) {
        super(context, R.layout.single_card);
        this.movieDetail=movieDetail;
        this.context = context;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_movies_detail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

            holder.moviedetail.setText(movieDetail.getT());
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView thumbnail;
        private TextView movietitle,moviedetail;
        TextView likeCount;
        CheckBox likeImage;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            likeCount=(TextView)  itemLayoutView.findViewById(R.id.likeCount);
            thumbnail = (ImageView) itemLayoutView.findViewById(R.id.movieImage);
            movietitle=(TextView) itemLayoutView.findViewById(R.id.movieName);
            moviedetail=(TextView) itemLayoutView.findViewById(R.id.movieDetail);
            likeImage=(CheckBox) itemLayoutView.findViewById(R.id.likeImage);        }
    }

}