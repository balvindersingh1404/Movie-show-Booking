package com.example.balvinder.bookYourShow.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnTouchListener {

    String usrname = "";
    String usremail = "";
    String usrmobile = "";
    String usrpassword = "";
    ImageView passshow;
    ProgressDialog progress;
    Context context;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    private ImageView imageBack;
    private TextView title;
    private TextView namemsg;
    private TextView emailmsg;
    private TextView mobilmsg;
    private TextView passwordmsg;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private EditText password;
    private TextView register;
    private Call<ApiResponse> call;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        passshow = (ImageView) findViewById(R.id.closeEye);


        context = this;
        title = (TextView) findViewById(R.id.title);
        title.setText("REGISTER");
        imageBack = (ImageView) findViewById(R.id.back);

        namemsg = (TextView) findViewById(R.id.namemsg);
        emailmsg = (TextView) findViewById(R.id.emailmsg);
        mobilmsg = (TextView) findViewById(R.id.mobilemsg);
        passwordmsg = (TextView) findViewById(R.id.passwordmsg);


        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.phonenumber);
        password = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
        password.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passshow.setTag("eyeclose");

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText())) {
                    namemsg.setText("Your name is required");
                    name.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (TextUtils.isEmpty(email.getText())) {
                    emailmsg.setText("Your email address is required");
                    email.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (TextUtils.isEmpty(mobile.getText())) {
                    mobilmsg.setText("Your mobile number is required");
                    mobile.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (TextUtils.isEmpty(password.getText())) {
                    passwordmsg.setText("Your password is required");
                    password.setBackground(getResources().getDrawable(R.drawable.borderred));
                }

                if (!TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(mobile.getText()) && !TextUtils.isEmpty(name.getText())) {
                    Boolean emailValidate = Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches();
                    Boolean phoneValidate = Patterns.PHONE.matcher(mobile.getText()).matches();

                    if (emailValidate && phoneValidate) {
                        usrname = name.getText().toString();
                        usremail = email.getText().toString();
                        usrmobile = mobile.getText().toString();
                        usrpassword = password.getText().toString();
                        registerApiCall();
                    } else {
                        emailmsg.setText("You entered an invalid email address");
                        mobilmsg.setText("You entered an invalid email address");
                    }

                }
            }
        });

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

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        email.setOnTouchListener(this);
        name.setOnTouchListener(this);
        password.setOnTouchListener(this);
        mobile.setOnTouchListener(this);
    }


    public void back() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void registerApiCall() {

        progress = new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        final LoginDetails loginDetails = new LoginDetails(usrname, usremail, usrmobile, usrpassword);

        call = ApiClient.getInstance().registerUser(loginDetails);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().getCode();

                Log.i("API RESPONSE", response.message());
                if (response.body().getCode() == AppConstant.SUCCESSCODE) {
                    Data loginresponse = response.body().getData();
                    Log.i("LOGIN API CALL", loginresponse.getId());

                    MovieApplication.getPref().setString(AppPreferences.name, loginresponse.getUn());
                    MovieApplication.getPref().setString(AppPreferences.email, loginresponse.getEm());
                    MovieApplication.getPref().setString(AppPreferences.picture, "");
                    MovieApplication.getPref().setString(AppPreferences.tag, "register");
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

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackground(getResources().getDrawable(R.drawable.border));
        return false;
    }
}
