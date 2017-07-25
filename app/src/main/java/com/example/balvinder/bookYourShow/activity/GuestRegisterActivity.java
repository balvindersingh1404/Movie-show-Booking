package com.example.balvinder.bookYourShow.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;

public class GuestRegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText emailaddress;
    private EditText phonenumber;
    private TextView contine ;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_register);

        name = (EditText) findViewById(R.id.name);
        emailaddress = (EditText) findViewById(R.id.email);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        contine = (TextView) findViewById(R.id.continuegmail);
        back=(ImageView) findViewById(R.id.backhome);

        contine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCheck();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

    }


    public void loginCheck() {
        if (name.getText().toString().equals("guest") && emailaddress.getText().toString().equals("guest@osscube.com")&& phonenumber.getText().toString().equals("7838469466")) {

            Intent i = new Intent(GuestRegisterActivity.this, LandingActivity.class);
            startActivity(i);
        } else {

            Intent i = new Intent(GuestRegisterActivity.this, GuestRegisterActivity.class);
            startActivity(i);
        }
    }

    public void back() {
        finish();

    }
}