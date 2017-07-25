package com.example.balvinder.bookYourShow.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.Data;
import com.example.balvinder.bookYourShow.model.LoginDetails;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppConstant;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MovieApplication;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacebookLoginActivity extends Activity implements View.OnTouchListener {

    private EditText name;
    private EditText emailaddress;
    private EditText phonenumber;
    private MyTextView contine;
    private ImageView imageBack;
    private TextView title;

    private TextView namemsg;
    private TextView emailmsg;
    private TextView mobilemsg;

    String usrname;
    String usremail;
    String usrmobile;
    ProgressDialog progress;
    Context context;
     boolean gps_enabled = false;
      boolean network_enabled = false;

    private Call<ApiResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        context=this;
        title = (TextView) findViewById(R.id.title);
        title.setText("FACEBOOK LOGIN");
        name = (EditText) findViewById(R.id.name);
        emailaddress = (EditText) findViewById(R.id.email);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        contine = (MyTextView) findViewById(R.id.continuefacebook);
        imageBack = (ImageView) findViewById(R.id.back);

        namemsg = (TextView) findViewById(R.id.namemsg);
        emailmsg = (TextView) findViewById(R.id.emailmsg);
        mobilemsg = (TextView) findViewById(R.id.mobilemsg);

        name.setOnTouchListener(this);
        emailaddress.setOnTouchListener(this);
        phonenumber.setOnTouchListener(this);


        name.setText(MovieApplication.getPref().getString(AppPreferences.name));
        emailaddress.setText(MovieApplication.getPref().getString(AppPreferences.email));

        contine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText())){
                    namemsg.setText("Your name is required");
                    name.setBackground(getResources().getDrawable(R.drawable.borderred));}

                if (TextUtils.isEmpty(emailaddress.getText())){
                    emailmsg.setText("Your email address is required");
                    emailaddress.setBackground(getResources().getDrawable(R.drawable.borderred));}

                if (TextUtils.isEmpty(phonenumber.getText())){
                    mobilemsg.setText("Your mobile number is required");
                    phonenumber.setBackground(getResources().getDrawable(R.drawable.borderred));}

                usrname = name.getText().toString();
                usremail = emailaddress.getText().toString();
                usrmobile = phonenumber.getText().toString();
                if (!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(emailaddress.getText()) && !TextUtils.isEmpty(phonenumber.getText())) {
                    {
                        Boolean emailValidate = Patterns.EMAIL_ADDRESS.matcher(emailaddress.getText()).matches();
                        Boolean phoneValidate = Patterns.PHONE.matcher(phonenumber.getText()).matches();

                        if (emailValidate && phoneValidate) {


                            registerApiCall();
                        } else {
                            emailmsg.setText("You entered an invalid email address");
                            mobilemsg.setText("You entered an invalid email address");
                        }
                    }
                }

            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });



        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.balvinder.pvr", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }


    public void registerApiCall(){
        progress= new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        LoginDetails loginDetails=new LoginDetails(usrname,usremail,usrmobile,"");

        call=  ApiClient.getInstance().registerUser(loginDetails);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().getCode();

                Log.i("API RESPONSE",response.message());
                if(response.body().getCode()== AppConstant.SUCCESSCODE)
                {
                    Data loginresponse = response.body().getData();
                    Log.i("LOGIN API CALL",loginresponse.getId());


                    LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

                    try {
                        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    } catch(Exception ex) {}

                    try {
                        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    } catch(Exception ex) {}



        if(!gps_enabled && !network_enabled) {
            Intent i = new Intent(getApplicationContext(), EnableLocationActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }


                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.i("API RESPONSE FAIL",t.toString());

            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackground(getResources().getDrawable(R.drawable.border));
        return false;
    }
}

