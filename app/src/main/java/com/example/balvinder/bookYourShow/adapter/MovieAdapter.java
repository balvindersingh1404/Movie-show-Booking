package com.example.balvinder.bookYourShow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.activity.MoviesDetailActivity;
import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.Movie_data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<ArrayList<Movie_data>> {
    Context context;
    ArrayList<Movie_data> arrayList;


    public MovieAdapter(Context context, ArrayList<Movie_data> arrayList) {
        super(context, R.layout.single_card);
        this.arrayList=arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie_data data = arrayList.get(position);
        ViewHolder holder=null;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.coming_soon_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(data!=null){
            if(data.getImage()!=null &&!TextUtils.isEmpty(data.getImage()))
                Picasso.with(context).load(data.getImage()).into(holder.thumbnail);
            holder.movietitle.setText(data.getName());
            holder.moviedetail.setText(data.getCaption());
            if(!TextUtils.isEmpty(data. getLike_count()))
            holder.likeCount.setText(data.getLike_count());
            final ViewHolder finalHolder = holder;


            holder.likeImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        int count=Integer.parseInt(data.getLike_count())+1;
                        finalHolder.likeCount.setText(Integer.toString(count));
                    }
                    else{
                        int count=Integer.parseInt(data.getLike_count());
                        finalHolder.likeCount.setText(Integer.toString(count));
                    }
                }
            });

            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String movieId= data.getMasterMovieId();
                    String url=data.getImage();
                    String name=data.getName();
                    String language=data.getLanguage();

//                    String id=datum.getId();
//                    String url=datum.getI();
//                    String name=datum.getN();


                    Intent in = new Intent(context,MoviesDetailActivity.class);
                    in.putExtra("movieId",movieId);
                    in.putExtra("url",url);
                    in.putExtra("name",name);
                    in.putExtra("language",language);
                    context.startActivity(in);

                }
            });


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