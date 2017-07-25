package com.example.balvinder.bookYourShow.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.balvinder.bookYourShow.model.ContactDetails;
import com.example.balvinder.bookYourShow.rest.ApiClient;
import com.example.balvinder.bookYourShow.util.CustomDialogClass;
import com.example.balvinder.bookYourShow.util.MyTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements View.OnTouchListener {
    private EditText phone,email,comment;
    private MyTextView submit;
    final  ContactDetails contactDetails = new ContactDetails();
    private Call<ApiResponse> call;
    ProgressDialog progress;
    Context context;
    private TextView title;
    private ImageView imageBack;
    TextView mobilemsg,emailmsg,commentmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        context=this;
        final ImageView advtick= (ImageView)findViewById(R.id.tickadv);
        final ImageView tickbulk= (ImageView)findViewById(R.id.tickbulk);
        final ImageView tickfeed= (ImageView)findViewById(R.id.tickfeed);

        imageBack=(ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title.setText("CONTACT US");

        mobilemsg=(TextView) findViewById(R.id.msgnumber);
        emailmsg=(TextView) findViewById(R.id.msgemail);
        commentmsg=(TextView) findViewById(R.id.msgcomment);


        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });

        final TextView advi = (TextView) findViewById(R.id.adv);
        final TextView feed = (TextView) findViewById(R.id.feedbackTextView);
        final TextView bulkbook = (TextView) findViewById(R.id.bulkbook);
        submit=(MyTextView) findViewById(R.id.submit);

        phone=(EditText) findViewById(R.id.phone) ;
        comment=(EditText) findViewById(R.id.comment) ;
        email=(EditText) findViewById(R.id.emailaddress) ;
        advi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(advi.getTypeface().isBold()){
                    advi.setTypeface(Typeface.DEFAULT);
                    advtick.setVisibility(View.INVISIBLE);
                }
                else{
                    advi.setTypeface(Typeface.DEFAULT_BOLD);
                    feed.setTypeface(Typeface.DEFAULT);
                    bulkbook.setTypeface(Typeface.DEFAULT);
                    advtick.setVisibility(View.VISIBLE);
                    tickbulk.setVisibility(View.INVISIBLE);
                    tickfeed.setVisibility(View.INVISIBLE);
                }
            }
        });
        bulkbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bulkbook.getTypeface().isBold()){
                    bulkbook.setTypeface(Typeface.DEFAULT);
                    tickbulk.setVisibility(View.INVISIBLE);
                }
                else{
                    bulkbook.setTypeface(Typeface.DEFAULT_BOLD);
                    feed.setTypeface(Typeface.DEFAULT);
                    advi.setTypeface(Typeface.DEFAULT);
                    tickbulk.setVisibility(View.VISIBLE);
                    tickfeed.setVisibility(View.INVISIBLE);
                    advtick.setVisibility(View.INVISIBLE);

                }
            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(feed.getTypeface().isBold()){
                    feed.setTypeface(Typeface.DEFAULT);
                    tickfeed.setVisibility(View.INVISIBLE);
                }
                else{
                    feed.setTypeface(Typeface.DEFAULT_BOLD);
                    advi.setTypeface(Typeface.DEFAULT);
                    bulkbook.setTypeface(Typeface.DEFAULT);
                    tickfeed.setVisibility(View.VISIBLE);
                    advtick.setVisibility(View.INVISIBLE);
                    tickbulk.setVisibility(View.INVISIBLE);

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText())) {
                    emailmsg.setText("Your email address is required");
                    email.setBackground(getResources().getDrawable(R.drawable.borderred));

                }
                if (TextUtils.isEmpty(comment.getText())) {
                    comment.setBackground(getResources().getDrawable(R.drawable.borderred));

                }
                if (TextUtils.isEmpty(phone.getText())) {
                    mobilemsg.setText("Your mobile number is required");
                    phone.setBackground(getResources().getDrawable(R.drawable.borderred));

                }

                else if (!(TextUtils.isEmpty(phone.getText())) && !(TextUtils.isEmpty(email.getText())) && !(TextUtils.isEmpty(comment.getText()))) {
                    Boolean emailValidate = Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches();
                    Boolean phoneValidate = Patterns.PHONE.matcher(phone.getText()).matches();

                    if (emailValidate && phoneValidate){

                        progress = new ProgressDialog(context);
                    progress.setMessage("Please Wait. ");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.show();


                    contactDetails.setComments(comment.getText().toString());
                    contactDetails.setContactOption("feedback");
                    contactDetails.setEmailId(email.getText().toString());
                    contactDetails.setPhoneNumber(phone.getText().toString());


                    call = ApiClient.getInstance().setContactUs(contactDetails);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            int code = response.body().getCode();

                            int successcode = 10001;
                            if (code == successcode) {

                                progress.dismiss();
                                CustomDialogClass cdd = new CustomDialogClass(ContactUsActivity.this);
                                cdd.show();

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
                    else{
                        emailmsg.setText("You entered an invalid email address");
                        mobilemsg.setText("You entered an invalid email address");
                    }
                }
            }
        });
        phone.setOnTouchListener(this);
        email.setOnTouchListener(this);
        comment.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackground(getResources().getDrawable(R.drawable.border));
        return false;
    }
}