package com.example.balvinder.bookYourShow.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;

public class TCPrivacyActivity extends AppCompatActivity {

    private ImageView back;
    private TextView privacy;
    private ImageView privacyInfo;
    private TextView termsOfUse;
    private ImageView termsOfUseInfo;
    private TextView termsAndConditions;
    private ImageView termsAndConditionsInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcprivacy);

        back=(ImageView) findViewById(R.id.back);


        privacy=(TextView) findViewById(R.id.privacy);
        privacyInfo=(ImageView) findViewById(R.id.privacyInfo);

        termsOfUse=(TextView) findViewById(R.id.termsOfUse);
        termsOfUseInfo=(ImageView) findViewById(R.id.termsOfUseInfo);

        termsAndConditions=(TextView) findViewById(R.id.termsAndConditions);
        termsAndConditionsInfo=(ImageView) findViewById(R.id.termsAndConditionsInfo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, PrivacyActivity.class);
                startActivity(i);
            }
        });


        privacyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, PrivacyActivity.class);
                startActivity(i);
            }
        });


        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, TermOfUseActivity.class);
                startActivity(i);
            }
        });



        termsOfUseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, TermOfUseActivity.class);
                startActivity(i);
            }
        });


        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, TermsAndConditionsActivity.class);
                startActivity(i);
            }
        });



        termsAndConditionsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TCPrivacyActivity.this, TermsAndConditionsActivity.class);
                startActivity(i);
            }
        });

    }
}
