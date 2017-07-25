package com.example.balvinder.bookYourShow.activity;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.model.ApiResponse;
import com.example.balvinder.bookYourShow.model.LoginDetails;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.example.balvinder.bookYourShow.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnTouchListener {
    private MyTextView haveotp,title,otpmsg,passwordmsg,otpButton,signinButton;
MyTextView emailmsg,mobilemsg;
    TextView resendotp,alreadyotp;
    EditText otp;
    Context context;
    ProgressDialog progress;
    ImageView pass,backButton,check;
    EditText newpassword,email,mobile;
    Call<ApiResponse> call;
    LoginDetails loginDetails = new LoginDetails();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        context=this;
        otpButton=(MyTextView) findViewById(R.id.sendotp);
        signinButton=(MyTextView) findViewById(R.id.signin) ;

        emailmsg=(MyTextView) findViewById(R.id.emailmsg);
        mobilemsg=(MyTextView) findViewById(R.id.mobilemsg);




        title=(MyTextView) findViewById(R.id.forgotpasswordtitle);
        Util.applyLetterSpacing(title,"FORGOT PASSWORD",1);
        backButton = (ImageView) findViewById(R.id.back);
        check = (ImageView) findViewById(R.id.imageView23);
        check.setTag("close");

        otpButton.setTag("SEND OTP");
        resendotp=(TextView) findViewById(R.id.resendotp);
        passwordmsg=(MyTextView) findViewById(R.id.passwordmsg);
        otpmsg=(MyTextView) findViewById(R.id.otpmsg);
        pass=(ImageView) findViewById(R.id.pass);
        pass.setTag("eyeclose");
        alreadyotp=(TextView) findViewById(R.id.alreadyotp);
        alreadyotp.setTag("close");
        newpassword=(EditText) findViewById(R.id.newpassword);
        email=(EditText) findViewById(R.id.email);
        mobile=(EditText) findViewById(R.id.mob);
        otp=(EditText) findViewById(R.id.otp);

        email.setOnTouchListener(this);
        mobile.setOnTouchListener(this);
        otp.setOnTouchListener(this);
        pass.setOnTouchListener(this);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
        alreadyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(alreadyotp.getTag().equals("close")){
                    check.setImageResource(R.drawable.check_otp);
                    pass.setVisibility(View.VISIBLE);
                    newpassword.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    otp.setVisibility(View.VISIBLE);
                    resendotp.setVisibility(View.VISIBLE);
                    passwordmsg.setVisibility(View.VISIBLE);
                    otpmsg.setVisibility(View.VISIBLE);
                    alreadyotp.setTag("open");
                    otpButton.setVisibility(View.GONE);

                    signinButton.setVisibility(View.VISIBLE);
                    Util.applyLetterSpacing(signinButton,"SIGN IN",1);
                }
                else{
                    pass.setVisibility(View.GONE);
                    check.setImageResource(R.drawable.uncheck_otp);
                    newpassword.setVisibility(View.GONE);
                    pass.setVisibility(View.GONE);
                    otp.setVisibility(View.GONE);
                    passwordmsg.setVisibility(View.GONE);
                    otpmsg.setVisibility(View.GONE);
                    resendotp.setVisibility(View.GONE);
                    alreadyotp.setTag("close");
                    otpButton.setVisibility(View.VISIBLE);
                    signinButton.setVisibility(View.GONE);
                    Util.applyLetterSpacing(otpButton,"SEND OTP",1);
                }
            }
        });

        otpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(email.getText())){
                    emailmsg.setText("Your email address is required");
                    email.setBackground(getResources().getDrawable(R.drawable.borderred));}

                if (TextUtils.isEmpty(mobile.getText())){
                    mobilemsg.setText("Your mobile number is required");
                    mobile.setBackground(getResources().getDrawable(R.drawable.borderred));}


                if (!TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(mobile.getText())) {
                    Boolean emailValidate = Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches();
                    Boolean phoneValidate = Patterns.PHONE.matcher(mobile.getText()).matches();

                    if (emailValidate && phoneValidate){

                        progress = new ProgressDialog(context);
                    progress.setMessage("Please Wait. ");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.show();

                    loginDetails.setEmail(email.getText().toString());
                    loginDetails.setMobile_no(mobile.getText().toString());
                    call = ApiClient.getInstance().getOtp(loginDetails);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            progress.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("error", t.toString());
                        }
                    });
                    pass.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_otp);
                    newpassword.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    otp.setVisibility(View.VISIBLE);
                    passwordmsg.setVisibility(View.VISIBLE);
                    otpmsg.setVisibility(View.VISIBLE);
                    resendotp.setVisibility(View.VISIBLE);
                    check.setTag("open");
                    signinButton.setVisibility(View.VISIBLE);
                    otpButton.setVisibility(View.GONE);
                    Util.applyLetterSpacing(signinButton, "SIGN IN", 1);

                }
                    else{
                        emailmsg.setText("You entered an invalid email address");
                        mobilemsg.setText("You entered an invalid email address");
                    }
            }
            }
        });


        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(email.getText()))
                    email.setBackground(getResources().getDrawable(R.drawable.borderred));

                if (TextUtils.isEmpty(mobile.getText()))
                    mobile.setBackground(getResources().getDrawable(R.drawable.borderred));

                if (TextUtils.isEmpty(newpassword.getText()))
                    newpassword.setBackground(getResources().getDrawable(R.drawable.borderred));

                if (TextUtils.isEmpty(otp.getText()))
                    otp.setBackground(getResources().getDrawable(R.drawable.borderred));

                if(!TextUtils.isEmpty(email.getText())&&!TextUtils.isEmpty(mobile.getText())&&!TextUtils.isEmpty(newpassword.getText())&&!TextUtils.isEmpty(otp.getText())) {
                    progress = new ProgressDialog(context);
                    progress.setMessage("Please Wait. ");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.show();

                    loginDetails.setEmail(email.getText().toString());
                    loginDetails.setMobile_no(mobile.getText().toString());
                    loginDetails.setPassword(newpassword.getText().toString());
                    loginDetails.setOtp(otp.getText().toString());

                    call = ApiClient.getInstance().getnewpassword(loginDetails);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            progress.dismiss();
                            int code = response.body().getCode();
                            int successcode = 10001;
                            if (code == successcode) {
                                Intent in = new Intent(getApplicationContext(), LandingActivity.class);
                                startActivity(in);
                            } else {
                                Context context = getApplicationContext();
                                CharSequence text = response.body().getMessage();
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Log.e("error", t.toString());
                        }
                    });
                }
                }

        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.getTag().equals("close")){
                    pass.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_otp);
                    newpassword.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    otp.setVisibility(View.VISIBLE);
                    passwordmsg.setVisibility(View.VISIBLE);
                    otpmsg.setVisibility(View.VISIBLE);
                    resendotp.setVisibility(View.VISIBLE);
                    check.setTag("open");
                    signinButton.setVisibility(View.VISIBLE);
                    otpButton.setVisibility(View.GONE);
                    Util.applyLetterSpacing(signinButton,"SIGN IN",1);
                }
                else{
                    pass.setVisibility(View.GONE);
                    newpassword.setVisibility(View.GONE);
                    check.setImageResource(R.drawable.uncheck_otp);
                    pass.setVisibility(View.GONE);
                    otp.setVisibility(View.GONE);
                    passwordmsg.setVisibility(View.GONE);
                    otpmsg.setVisibility(View.GONE);
                    resendotp.setVisibility(View.GONE);
                    check.setTag("close");
                    signinButton.setVisibility(View.GONE);
                    otpButton.setVisibility(View.VISIBLE);
                    Util.applyLetterSpacing(otpButton,"SEND OTP",1);
                }
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getTag().equals("eyeclose")){
                    pass.setImageResource(R.drawable.eye_open);
                    newpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pass.setTag("eyeopen");
                }
                else{
                    pass.setImageResource(R.drawable.eye_close);
                    newpassword.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass.setTag("eyeclose");
                }
            }
        });

        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(email.getText()))
                    email.setBackground(getResources().getDrawable(R.drawable.borderred));

                if (TextUtils.isEmpty(mobile.getText()))
                    mobile.setBackground(getResources().getDrawable(R.drawable.borderred));


                if(!TextUtils.isEmpty(email.getText())&&!TextUtils.isEmpty(mobile.getText())) {
                    progress = new ProgressDialog(context);
                    progress.setMessage("Please Wait. ");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.show();

                    loginDetails.setEmail(email.getText().toString());
                    loginDetails.setMobile_no(mobile.getText().toString());
                    call = ApiClient.getInstance().getOtp(loginDetails);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            progress.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("error", t.toString());
                        }
                    });
                    pass.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_otp);
                    newpassword.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    otp.setVisibility(View.VISIBLE);
                    passwordmsg.setVisibility(View.VISIBLE);
                    otpmsg.setVisibility(View.VISIBLE);
                    resendotp.setVisibility(View.VISIBLE);
                    check.setTag("open");
                    signinButton.setVisibility(View.VISIBLE);
                    otpButton.setVisibility(View.GONE);
                    Util.applyLetterSpacing(signinButton, "SIGN IN", 1);
                }

            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackground(getResources().getDrawable(R.drawable.border));
        return false;
    }
}
