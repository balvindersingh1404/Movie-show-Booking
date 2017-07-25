package com.example.balvinder.bookYourShow.adapter;

/**
 * Created by balvinder on 5/12/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.activity.MoviesDetailActivity;
import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MfyAdapter extends RecyclerView.Adapter<MfyAdapter.MyViewHolder> {

    private Context mContext;
    private  List<Datum> datumList;

    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, detail;
        public ImageView thumbnail;
        public  CheckBox likeImage;
        TextView likeCount;

        LinearLayout datanotfound;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.movieName);
            detail = (TextView) view.findViewById(R.id.movieDetail);
            thumbnail = (ImageView) view.findViewById(R.id.movieImage);
             likeImage=(CheckBox) view.findViewById(R.id.likeImage);
             likeCount=(TextView) view.findViewById(R.id.likeCount);
            datanotfound=(LinearLayout) view.findViewById(R.id.datanotfound);


        }
    }


    public MfyAdapter(Context mContext, List<Datum> datumList) {
        this.mContext = mContext;
        this.datumList = datumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==2){
       itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.multiple_card, parent, false);
        }
        else{
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_card, parent, false);
        }
        return new MyViewHolder(itemView);

    }

    @Override
    public int getItemViewType(int position) {
        if(datumList.get(position)!=null) {
            if (datumList.get(position).getMf().equals("true")) {
                return 2;
            }
        }

        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Datum datum = datumList.get(position);

            holder.title.setText(datum.getN());
            holder.detail.setText(datum.getSh());
            String url=datum.getI();
            if(url!=null && !TextUtils.isEmpty(url))
            Picasso.with(mContext).load(url).into(holder.thumbnail);
            holder.likeImage.setChecked(Boolean.parseBoolean(datum.getUl().toString()));
            holder.likeCount.setText(datum.getLc());


        holder.likeImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    int count=Integer.parseInt(datum.getLc())+1;
                    holder.likeCount.setText(Integer.toString(count));
                }
                else{
                    int count=Integer.parseInt(datum.getLc());
                    holder.likeCount.setText(Integer.toString(count));
                }
            }
        });


        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=datum.getId();
                String url=datum.getI();
                String name=datum.getN();

           //     @GET("/movies/details?movieId=NHO8955372&language=Hindi&cityId=1")

                String language=datum.getLng();
                Intent in = new Intent(mContext,MoviesDetailActivity.class);
                in.putExtra("movieId",id);
                in.putExtra("url",url);
                in.putExtra("name",name);
                in.putExtra("language",language);
                mContext.startActivity(in);
            }
        });
    }



    @Override
    public int getItemCount() {
        return datumList.size();
    }

}

