package com.example.balvinder.bookYourShow.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.Data;
import com.example.balvinder.bookYourShow.model.LoginDetails;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.AppConstant;
import com.example.balvinder.bookYourShow.util.AppPreferences;
import com.example.balvinder.bookYourShow.util.MovieApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends Activity implements View.OnTouchListener {


    String email = "";
    String mobile = "";
    String pwd = "";
    TextView emailmsg, mobilmsg, passwordmsg;
    ImageView passshow;
    ProgressDialog progress;
    Context context;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    private EditText username;
    private EditText mobileNo;
    private EditText password;
    private TextView signin;
    private ImageView imageBack;
    private TextView forgotPassword;
    private Call<ApiResponse> call;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        context = this;


        passshow = (ImageView) findViewById(R.id.closeEye);

        title = (TextView) findViewById(R.id.title);
        title.setText("SIGN IN");


        username = (EditText) findViewById(R.id.email);
        mobileNo = (EditText) findViewById(R.id.phonenumber);
        password = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
        password.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passshow.setTag("eyeclose");

        emailmsg = (TextView) findViewById(R.id.emailmsg);
        mobilmsg = (TextView) findViewById(R.id.mobilemsg);
        passwordmsg = (TextView) findViewById(R.id.passwordmsg);

        signin = (TextView) findViewById(R.id.signin);
        imageBack = (ImageView) findViewById(R.id.back);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);


        passshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passshow.getTag().equals("eyeclose")) {
                    passshow.setImageResource(R.drawable.eye_open);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passshow.setTag("eyeopen");
                } else {
                    passshow.setImageResource(R.drawable.eye_close);
                    password.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passshow.setTag("eyeclose");
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText())) {
                    emailmsg.setText("Your email address is required");
                    username.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (TextUtils.isEmpty(mobileNo.getText())) {
                    mobilmsg.setText("Your mobile number is required");
                    mobileNo.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (TextUtils.isEmpty(password.getText())) {
                    passwordmsg.setText("Your password is required");
                    password.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(mobileNo.getText()) && !TextUtils.isEmpty(password.getText())) {
                    Boolean emailValidate = Patterns.EMAIL_ADDRESS.matcher(username.getText()).matches();
                    Boolean phoneValidate = Patterns.PHONE.matcher(mobileNo.getText()).matches();

                    if (emailValidate && phoneValidate) {

                        email = username.getText().toString();
                        mobile = mobileNo.getText().toString();
                        pwd = password.getText().toString();
                        loginApiCall();
                    } else {
                        emailmsg.setText("You entered an invalid email address");
                        mobilmsg.setText("You entered an invalid email address");
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
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });

        username.setOnTouchListener(this);
        mobileNo.setOnTouchListener(this);
        password.setOnTouchListener(this);

    }


    public void forgotPassword() {
        Intent i = new Intent(SigninActivity.this, ForgotPasswordActivity.class);
        startActivity(i);
    }


    public void loginApiCall() {
        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        LoginDetails loginDetails = new LoginDetails(email, mobile, pwd);

        call = ApiClient.getInstance().getToken(loginDetails);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().getCode();

                Log.i("API RESPONSE", response.message());
                if (response.body().getCode() == AppConstant.SUCCESSCODE) {
                    Data loginresponse = response.body().getData();
                    Log.i("LOGIN API CALL", loginresponse.getId());

                    MovieApplication.getPref().setString(AppPreferences.id, loginresponse.getId());
                    MovieApplication.getPref().setString(AppPreferences.name, loginresponse.getUn());
                    MovieApplication.getPref().setString(AppPreferences.email, loginresponse.getEm());
                    MovieApplication.getPref().setString(AppPreferences.tag, "signin");
                    MovieApplication.getPref().setString(AppPreferences.sessionId, loginresponse.getSid());


                    LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                    try {
                        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    } catch (Exception ex) {
                    }

                    try {
                        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    } catch (Exception ex) {
                    }


                    if (!gps_enabled && !network_enabled) {
                        Intent i = new Intent(getApplicationContext(), EnableLocationActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getApplicationContext(), LandingActivity.class);
                        startActivity(i);
                    }

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.i("API RESPONSE FAIL", t.toString());

            }
        });

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackground(getResources().getDrawable(R.drawable.border));
        return false;
    }
}