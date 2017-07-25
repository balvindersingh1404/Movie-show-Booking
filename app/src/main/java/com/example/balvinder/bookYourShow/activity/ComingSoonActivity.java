package com.example.balvinder.bookYourShow.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.adapter.MovieAdapter;
import com.example.balvinder.bookYourShow.model.MovieResponseModel;
import com.example.balvinder.bookYourShow.model.Movie_data;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComingSoonActivity extends AppCompatActivity {
    ImageView back;
    Context context;
    ProgressDialog progress;
    MyTextView title;
    LinearLayout datanotfound;
    Call<MovieResponseModel> call;
    String language = "";
    private MyTextView all, hindi, english, regional;
    private MovieAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        all = (MyTextView) findViewById(R.id.all);
        hindi = (MyTextView) findViewById(R.id.hindi);
        lv = (ListView) findViewById(R.id.comingsoonsearch);
        context = this;
        datanotfound = (LinearLayout) findViewById(R.id.datanotfound);
        callgetmovies();
        back = (ImageView) findViewById(R.id.back);
        title = (MyTextView) findViewById(R.id.titletext);
        Util.applyLetterSpacing(title, "COMING SOON", 1);
        regional = (MyTextView) findViewById(R.id.regional);
        english = (MyTextView) findViewById(R.id.english);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hindi.setTextColor(0xFF000000);
                hindi.setBackgroundColor(0xFFFFFFFF);
                english.setTextColor(0xFF000000);
                english.setBackgroundColor(0xFFFFFFFF);
                regional.setTextColor(0xFF000000);
                regional.setBackgroundColor(0xFFFFFFFF);
                all.setTextColor(Color.parseColor("#ffcb05"));
                all.setBackgroundColor(0xFF000000);
                callgetmovies();
            }
        });

        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setTextColor(0xFF000000);
                all.setBackgroundColor(0xFFFFFFFF);
                english.setTextColor(0xFF000000);
                english.setBackgroundColor(0xFFFFFFFF);
                regional.setTextColor(0xFF000000);
                regional.setBackgroundColor(0xFFFFFFFF);
                hindi.setTextColor(Color.parseColor("#ffcb05"));
                hindi.setBackgroundColor(0xFF000000);
                language = "hi";
                callLanguageMovies();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hindi.setTextColor(0xFF000000);
                hindi.setBackgroundColor(0xFFFFFFFF);
                english.setTextColor(Color.parseColor("#ffcb05"));
                english.setBackgroundColor(0xFF000000);
                all.setTextColor(0xFF000000);
                regional.setTextColor(0xFF000000);
                regional.setBackgroundColor(0xFFFFFFFF);
                all.setBackgroundColor(0xFFFFFFFF);
                language = "en";
                callLanguageMovies();
            }
        });
        regional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hindi.setTextColor(0xFF000000);
                hindi.setBackgroundColor(0xFFFFFFFF);
                regional.setTextColor(Color.parseColor("#ffcb05"));
                regional.setBackgroundColor(0xFF000000);
                all.setTextColor(0xFF000000);
                all.setBackgroundColor(0xFFFFFFFF);
                english.setTextColor(0xFF000000);
                english.setBackgroundColor(0xFFFFFFFF);
                language = "ot";
                callLanguageMovies();
            }
        });
    }

    private void callgetmovies() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        call = ApiClient.getInstance().getmovies();
        call.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                progress.dismiss();
                if (response.body().getCode() == 10001) {
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    ArrayList<Movie_data> data = (ArrayList<Movie_data>) response.body().getData();
                    MovieAdapter adapter = new MovieAdapter(context, data);
                    lv.setAdapter(adapter);
                } else {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void callLanguageMovies() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();


        call = ApiClient.getInstance().getComingSoonMovies(language);
        call.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                progress.dismiss();
                if (response.body().getCode() == 10001) {
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    ArrayList<Movie_data> data = (ArrayList<Movie_data>) response.body().getData();
                    MovieAdapter adapter = new MovieAdapter(context, data);
                    lv.setAdapter(adapter);
                } else {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

}