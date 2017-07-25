package com.example.balvinder.bookYourShow.activity;

/**
 * Created by balvinder on 25/10/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.Data;
import com.example.balvinder.bookYourShow.model.GetToken;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppConstant;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MovieApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;

public class SplashActivity extends Activity {

    TextView dynamicText;
    private Data dynamicSplashText;
    private ProgressBar progressBar;
    private Call<ApiResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dynamicText = (TextView) findViewById(R.id.dynamicText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        generateAuthToken();

    }


    public void generateAuthToken() {
        String deviceid = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        String platform = "android";

        // Calling GetToken API
        GetToken token = new GetToken(deviceid, "", "", "", "", "", "", platform, "");

        call = ApiClient.getInstance().getToken(token);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().getCode();

                Log.i("API RESPONSE", response.message());
                if (response.body().getCode() == AppConstant.SUCCESSCODE) {
                    Data apiresponse = response.body().getData();

                    //Register Into Shared Preferences

                    MovieApplication.getPref().setString(AppPreferences.location,"Delhi-NCR");
                    MovieApplication.getPref().setString(AppPreferences.id,"1");
                    MovieApplication.getPref().setString(AppPreferences.AuthKey, apiresponse.getDt());
                    MovieApplication.getPref().setString(AppPreferences.DeviceId, apiresponse.getDid());

                    generateDynamicText();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.i("API RESPONSE FAIL", t.toString());

            }
        });
    }

    public void generateDynamicText() {

        //Calling API for Splash Dynamic Text

        call = ApiClient.getInstance().getSplashText();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {


                Log.i("DYNAMNIC TEXT API  ", response.message());
                if (response.body().getCode() == AppConstant.SUCCESSCODE) {
                    dynamicSplashText = response.body().getData();
                    timeOut();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("API RESPONSE FAIL", t.toString());
            }
        });

    }

    public void timeOut() {

        dynamicText.setText(dynamicSplashText.getSplashText().toString());
        progressBar.setVisibility(INVISIBLE);
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }

}

