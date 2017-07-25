package com.example.balvinder.bookYourShow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.balvinder.bookYourShow.R;

public class TermsAndConditionsActivity extends AppCompatActivity {

    private Button booking;
    private Button contest;
    private ImageView back;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        booking = (Button) findViewById(R.id.online);
        contest = (Button) findViewById(R.id.contest);
        back = (ImageView) findViewById(R.id.back);
        webview=(WebView) findViewById(R.id.webview) ;
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/policy_pages/online_booking.html");

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("file:///android_asset/policy_pages/online_booking.html");
                contest.setBackgroundColor(getResources().getColor(R.color.buttonOnDeselect));
                booking.setBackgroundColor(getResources().getColor(R.color.buttonOnSelect));
                booking.setTextColor(getResources().getColor(R.color.textOnSelect));
                contest.setTextColor(getResources().getColor(R.color.textOnDeselect));


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });


        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("file:///android_asset/policy_pages/contest_and_promotion.html");
                booking.setBackgroundColor(getResources().getColor(R.color.buttonOnDeselect));
                contest.setBackgroundColor(getResources().getColor(R.color.buttonOnSelect));
                contest.setTextColor(getResources().getColor(R.color.textOnSelect));
                booking.setTextColor(getResources().getColor(R.color.textOnDeselect));

            }
        });

    }

}
