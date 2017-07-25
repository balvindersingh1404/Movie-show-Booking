package com.example.balvinder.bookYourShow.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.adapter.OfferAdapter;
import com.example.balvinder.bookYourShow.model.OfferResponseModel;
import com.example.balvinder.bookYourShow.model.Offer_data;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersActivity extends AppCompatActivity {
    ImageView back;
    Context context;
    ProgressDialog progress;
    MyTextView titletext;
    private ListView lv;
    Call<OfferResponseModel> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        lv = (ListView) findViewById(R.id.offerslist);
        context = this;
        callgetoffers();
        back = (ImageView) findViewById(R.id.back);
        titletext = (MyTextView) findViewById(R.id.titletext);
        Util.applyLetterSpacing(titletext, "OFFERS", 1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void callgetoffers() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        call = ApiClient.getInstance().getoffers();
        call.enqueue(new Callback<OfferResponseModel>() {
            @Override
            public void onResponse(Call<OfferResponseModel> call, Response<OfferResponseModel> response) {
                progress.dismiss();

                ArrayList<Offer_data> data = (ArrayList<Offer_data>) response.body().getData();
                OfferAdapter adapter = new OfferAdapter(context, data);
                lv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OfferResponseModel> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

}