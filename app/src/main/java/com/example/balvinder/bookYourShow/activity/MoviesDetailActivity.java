package com.example.balvinder.bookYourShow.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.MovieDetail;
import com.example.balvinder.bookYourShow.model.MovieDetailResponseModel;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppConstant;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.MovieApplication;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDetailActivity extends AppCompatActivity {

    Context context;
    ProgressDialog progress;
    MyTextView castData, directorData, plotData,cast,director,plot;
    Call<MovieDetailResponseModel> call;
      ImageView movieImage,back;
    private MyTextView titletext;
    private TextView likeCount;
    private CheckBox likeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        context=this;

        titletext=(MyTextView) findViewById(R.id.titletext);
        likeCount=(TextView) findViewById(R.id.likeCount);
        likeImage=(CheckBox) findViewById(R.id.likeImage);

        cast=(MyTextView) findViewById(R.id.cast);
        director=(MyTextView) findViewById(R.id.director);
        plot=(MyTextView) findViewById(R.id.plot);
        castData=(MyTextView) findViewById(R.id.castdata);
        directorData=(MyTextView) findViewById(R.id.directordata);
        plotData=(MyTextView) findViewById(R.id.plotdata);
        movieImage=(ImageView) findViewById(R.id.pic);
        back=(ImageView) findViewById(R.id.backimage123);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getDetails();


        likeImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    int count = Integer.parseInt(likeCount.getText().toString()) + 1;
                    likeCount.setText(Integer.toString(count));
                } else {

                    int count = Integer.parseInt(likeCount.getText().toString())-1;
                    likeCount.setText(Integer.toString(count));
                }
            }
        });

    }

    private void getDetails(){

        progress= new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
       // MovieApplication.getPref().setString(AppPreferences.longitude, currentLongitude+"");
        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        titletext.setText(name);

        final String url=b.getString("url");
        String language=b.getString("language");
        String movieId=b.getString("movieId");
        String cityId= MovieApplication.getPref().getString(AppPreferences.getId());


        //     @GET("/movies/details?movieId=NHO8955372&language=Hindi&cityId=1")

        call = ApiClient.getInstance().getMoviesDetail(movieId,language,cityId);
        call.enqueue(new Callback<MovieDetailResponseModel>() {
            @Override
            public void onResponse(Call<MovieDetailResponseModel> call, Response<MovieDetailResponseModel> response) {
                progress.hide();


                if(response.body().getCode()== AppConstant.SUCCESSCODE) {

                   MovieDetail movieDetails=response.body().getMovieDetail();

                    if(movieDetails!=null) {
                        progress.hide();
                        if(!url.isEmpty()) {
                            Picasso.with(context).load(url).into(movieImage);
                        }


                        cast.setText("Cast");
                        director.setText("Director");
                        plot.setText("Plot");
                        likeCount.setText(movieDetails.getLc());
                        castData.setText(movieDetails.getCa());
                        directorData.setText(movieDetails.getD());
                        plotData.setText(movieDetails.getP());

                    }

                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponseModel> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
