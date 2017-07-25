package com.example.balvinder.bookYourShow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.Util;

public class GiftCardActivity extends AppCompatActivity {
    MyTextView minusthree,numberthree,addthree,minusfive,numberfive,addfive,minusten,numberten,addten,titletext,placeorder;
    int minteger = 0,tinteger = 0,finteger = 0;
    private int numberf,numberff,numberft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_card);
        minusthree=(MyTextView) findViewById(R.id.minusthree);
        addthree=(MyTextView) findViewById(R.id.addthree);
        numberthree=(MyTextView) findViewById(R.id.numberthree);
        minusfive=(MyTextView) findViewById(R.id.minusfive);
        addfive=(MyTextView) findViewById(R.id.addfive);
        numberfive=(MyTextView) findViewById(R.id.numberfive);
        minusten=(MyTextView) findViewById(R.id.minusten);
        addten=(MyTextView) findViewById(R.id.addten);
        numberten=(MyTextView) findViewById(R.id.numberten);
        titletext=(MyTextView) findViewById(R.id.titletext);
        Util.applyLetterSpacing(titletext,"GIFT CARD",1);
        placeorder=(MyTextView) findViewById(R.id.placeorder);
        Util.applyLetterSpacing(placeorder,"PLACE ORDER",1);
        ImageView backButton = (ImageView) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });
        MyTextView placeorder = (MyTextView) findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Can't Place Your Order", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void increasethree(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreasethree(View view) {
        if(minteger!=0){
            minteger = minteger - 1;}
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.numberthree);
        displayInteger.setText("" + number);
        TextView total = (TextView) findViewById(
                R.id.total);
        numberf=number*300;
        total.setText("Rs. " + (numberff+numberf+numberft));

    }
    public void increasefive(View view) {
        finteger = finteger + 1;
        displayf(finteger);

    }public void decreasefive(View view) {
        if(finteger!=0){
            finteger = finteger - 1;
        }
        displayf(finteger);
    }

    private void displayf(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.numberfive);
        displayInteger.setText("" + number);
        TextView total = (TextView) findViewById(
                R.id.total);
        numberff=number*500;
        total.setText("Rs. " + (numberff+numberf+numberft));
    }
    public void increaseten(View view) {
        tinteger = tinteger + 1;
        displayt(tinteger);

    }public void decreaseten(View view) {
        if(tinteger!=0){
            tinteger = tinteger - 1;
        }
        displayt(tinteger);
    }

    private void displayt(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.numberten);
        displayInteger.setText("" + number);
        TextView total = (TextView) findViewById(
                R.id.total);
        numberft=number*1000;
        total.setText("Rs. " + (numberff+numberf+numberft));
    }
}