package com.example.balvinder.bookYourShow.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.SearchResponseModel;
import com.example.balvinder.bookYourShow.model.Search_data;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.MovieApplication;
import com.example.balvinder.bookYourShow.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    final ArrayList<String> moviedata = new ArrayList<>();
    final ArrayList<String> theatredata = new ArrayList<>();
    final ArrayList<String> releasedata = new ArrayList<>();
    LinearLayout datanotfound;
    Context context;
    ProgressDialog progress;
    private TextView movies, theatres, latestrelease, cancel;
    private SearchAdapter adapter;
    private ListView lv;
    Call<SearchResponseModel> call;
    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        movies = (TextView) findViewById(R.id.movies);
        datanotfound = (LinearLayout) findViewById(R.id.datanotfound);
        theatres = (TextView) findViewById(R.id.theatres);
        cancel = (TextView) findViewById(R.id.cancel);
        lv = (ListView) findViewById(R.id.listsearch);
        context = this;
        id= MovieApplication.getPref().getString("id");
        latestrelease = (TextView) findViewById(R.id.latestrelease);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatres.setTextColor(0xFF000000);
                theatres.setBackgroundColor(0xFFFFFFFF);
                latestrelease.setTextColor(0xFF000000);
                latestrelease.setBackgroundColor(0xFFFFFFFF);
                movies.setTextColor(Color.parseColor("#ffcb05"));
                movies.setBackgroundColor(0xFF000000);
                ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.search_list_item, R.id.label, moviedata);
                if (moviedata.size() == 0) {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                } else {
                    lv.setVisibility(View.VISIBLE);
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setAdapter(adapter);
                }
            }
        });

        theatres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.setTextColor(0xFF000000);
                movies.setBackgroundColor(0xFFFFFFFF);
                latestrelease.setTextColor(0xFF000000);
                latestrelease.setBackgroundColor(0xFFFFFFFF);
                theatres.setTextColor(Color.parseColor("#ffcb05"));
                theatres.setBackgroundColor(0xFF000000);
                ArrayAdapter adapter1 = new ArrayAdapter<String>(context, R.layout.search_list_item, R.id.label, theatredata);
                if (theatredata.size() == 0) {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                } else {
                    lv.setVisibility(View.VISIBLE);
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setAdapter(adapter1);
                }
            }
        });
        latestrelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theatres.setTextColor(0xFF000000);
                theatres.setBackgroundColor(0xFFFFFFFF);
                latestrelease.setTextColor(Color.parseColor("#ffcb05"));
                latestrelease.setBackgroundColor(0xFF000000);
                movies.setTextColor(0xFF000000);
                movies.setBackgroundColor(0xFFFFFFFF);
                ArrayAdapter adapter2 = new ArrayAdapter<String>(context, R.layout.search_list_item, R.id.label, releasedata);
                if (releasedata.size() == 0) {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                } else {
                    lv.setVisibility(View.VISIBLE);
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setAdapter(adapter2);
                }
            }
        });

        getMoviesdetail();

    }

    private void getMoviesdetail() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();



        call=ApiClient.getInstance().getMoviedetails(id);
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                progress.hide();
                List<Search_data> data = (List<Search_data>) response.body().getData();

                for (Search_data searchData : data) {
                    if (searchData.getT().equalsIgnoreCase("M")) {
                        moviedata.add(searchData.getN());
                    }
                    if (searchData.getT().equalsIgnoreCase("T")) {
                        theatredata.add(searchData.getN());
                    } else {
                        releasedata.add(searchData.getN());
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.search_list_item, R.id.label, moviedata);
                if (moviedata.size() == 0) {
                    lv.setVisibility(View.INVISIBLE);
                    datanotfound.setVisibility(View.VISIBLE);
                } else {
                    lv.setVisibility(View.VISIBLE);
                    datanotfound.setVisibility(View.INVISIBLE);
                    lv.setAdapter(adapter);
                }

                Log.e("data", response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}


