package com.example.balvinder.bookYourShow.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.adapter.TheatreAdapter;
import com.example.balvinder.bookYourShow.model.theatreData.ApiResponseTheatre;
import com.example.balvinder.bookYourShow.model.theatreData.C;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.MovieApplication;
import com.example.balvinder.bookYourShow.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTheatresActivity extends AppCompatActivity {
    ImageView back;
    Context context;
    ProgressDialog progress;
    MyTextView titletext;
    List<C> data;
    Call<ApiResponseTheatre> call;
    private ListView lv;
    private ImageView  search;
    private String locationName;
    private MyTextView location;
    private EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_theatres);

        locationName = MovieApplication.getPref().getString(AppPreferences.location);
        location=(MyTextView) findViewById(R.id.locationName);
        location.setText(locationName);

        searchEditText=(EditText) findViewById(R.id.searchtheatre);
        searchEditText.setHint("Search for theatres in "+locationName);

        lv = (ListView) findViewById(R.id.theatrelist);
        context = this;

        callgettheatre();
        back = (ImageView) findViewById(R.id.back);
        titletext = (MyTextView) findViewById(R.id.titletext);
        Util.applyLetterSpacing(titletext, "ALL THEATRES", 1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        search=(ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectLocationActivity.class);
                startActivity(intent);

            }
        });
        onResume();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        locationName = MovieApplication.getPref().getString(AppPreferences.location);
        location.setText(locationName);
        searchEditText.setHint("Search for theatres in "+locationName);

    }

    private void callgettheatre() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        call = ApiClient.getInstance().gettheatres();
        call.enqueue(new Callback<ApiResponseTheatre>() {
            @Override
            public void onResponse(Call<ApiResponseTheatre> call, Response<ApiResponseTheatre> response) {
                progress.dismiss();
                data = response.body().getData().getC();
                TheatreAdapter theatreAdapter = new TheatreAdapter(context, data);
                lv.setAdapter(theatreAdapter);
            }

            @Override
            public void onFailure(Call<ApiResponseTheatre> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}