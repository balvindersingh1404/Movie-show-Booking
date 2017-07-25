package com.example.balvinder.bookYourShow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.balvinder.bookYourShow.R;

public class PrivacyActivity extends AppCompatActivity {

    private ImageView back;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        webview=(WebView) findViewById(R.id.webview) ;
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/policy_pages/privacy.html");
    }
}
