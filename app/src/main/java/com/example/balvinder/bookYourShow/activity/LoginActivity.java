package com.example.balvinder.bookYourShow.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends FragmentActivity implements  GoogleApiClient.OnConnectionFailedListener {


    private GoogleApiClient client;
    private TextView textView;
    private TextView login;
    private TextView register;
    private ImageView fbLogin;
    private ImageView googleLogin;
    String socialType,userId,accessToken,email;
    LoginDetails loginDetails;
    String personPhotoUrl;
    private String socialEmail="",socialName="",socialID="";
    Bundle bundle = new Bundle();
    ProgressDialog progress;
    Context context;

    boolean gps_enabled = false;
    boolean network_enabled = false;

    private CallbackManager callbackManager;
    List<String> permissions;

    private Call<ApiResponse> call;

    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;
    private int AUTH_CODE_REQUEST_CODE=1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;


        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        textView = (TextView) findViewById(R.id.textView9);
        login = (TextView) findViewById(R.id.emailmsg);
        register = (TextView) findViewById(R.id.textView2);
        fbLogin = (ImageView) findViewById(R.id.imageView2);
        googleLogin = (ImageView) findViewById(R.id.imageView3);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick();
            }
        });
        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLogin();
            }
        });
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



    }

    //redirect to landing page
    void onclick() {

        //Register Into Shared Preferences
        MovieApplication.getPref().setString(AppPreferences.name, "HELLO");
        MovieApplication.getPref().setString(AppPreferences.email, "null");
        MovieApplication.getPref().setString(AppPreferences.picture, "");
        MovieApplication.getPref().setString(AppPreferences.tag, "skip");



        if(!gps_enabled && !network_enabled) {
            Intent i = new Intent(getApplicationContext(), EnableLocationActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }

    }


    //redirect to login screen
    void login() {

        Intent i = new Intent(LoginActivity.this, SigninActivity.class);
        startActivity(i);
    }

    //redirect to register screen
    void register() {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    //redirect to fbLogin screen
    void fbLogin() {

        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile","email", "user_birthday"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        final GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject fObject,
                                            GraphResponse response) {

                                        Log.i("Facebookdata", fObject.toString());
                                        socialType="fb";
                                        accessToken=loginResult.getAccessToken().getToken();

                                        try {

                                            Intent intent = new Intent(getApplicationContext(), FacebookLoginActivity.class);
                                            //Create a bundle object
                                            MovieApplication.getPref().setString(AppPreferences.id, fObject.getString("id").toString());
                                            MovieApplication.getPref().setString(AppPreferences.name, fObject.getString("name").toString());
                                            MovieApplication.getPref().setString(AppPreferences.email, fObject.getString("email").toString());
                                            MovieApplication.getPref().setString(AppPreferences.tag, "fb");

                                            JSONObject pictureJSON=null;
                                            String  url=null;
                                            try {
                                                    pictureJSON = new JSONObject((fObject.getString("picture").toString()));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    if (MovieApplication.getPref().getString(AppPreferences.tag).equals("fb")) {
                                                        url = pictureJSON.getJSONObject("data").getString("url");
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                            MovieApplication.getPref().setString(AppPreferences.picture,url);;
                                            userId=fObject.getString("id").toString();
                                            email = fObject.optString("email");

                                            //SOCIAL API CALL
                                            socialLoginApiCall();
                                            //start the DisplayActivity
                                            startActivity(intent);

                                        } catch (Exception e) {

                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,birthday,gender,picture.type(square).width(200).height(200)");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        System.out.println("enter_in_fb_oncancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        if (exception != null) {
                        }
                        System.out.println("enter_in_fb_onerror");

                    }
                });
        permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("email");
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
    }


    //Google Signin
    private void signIn() {
        progress= new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
        else if (requestCode == AUTH_CODE_REQUEST_CODE) {
            if (data != null && resultCode == -1) {
                new RetrieveTokenTask().execute(socialEmail, socialName, socialID);
            }
        }
            //If not request code is RC_SIGN_IN it must be facebook
      else   {      callbackManager.onActivityResult(requestCode, resultCode, data);}
        }



  //  After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            MovieApplication.getPref().setString(AppPreferences.name, personName);
            MovieApplication.getPref().setString(AppPreferences.email, email);
            MovieApplication.getPref().setString(AppPreferences.tag, "google");
            MovieApplication.getPref().setString(AppPreferences.picture, personPhotoUrl);


            Intent intent = new Intent(getApplicationContext(), GoogleLoginActivity.class);
            startActivity(intent);


        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    //SOCIAL LOGIN API CALL
    public void socialLoginApiCall(){
        progress= new ProgressDialog(context);
        progress.setMessage("Please Wait. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        loginDetails = new LoginDetails(socialType, userId, accessToken, email, "", "", "");

        call=  ApiClient.getInstance().socialLogin(loginDetails);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                response.body().getCode();

                Log.i("API RESPONSE",response.message());
                if(response.body().getCode()== AppConstant.SUCCESSCODE)
                {
                    Data loginresponse = response.body().getData();
                    Log.i("LOGIN API CALL",loginresponse.getId());


                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.i("API RESPONSE FAIL",t.toString());

            }
        });

    }



    private class RetrieveTokenTask extends AsyncTask<String, Void, String> {
        String personName, accountName, id,socialName;

        @Override
        protected String doInBackground(String... params) {
            String accessToken = "";
            try {
                this.accountName = params[0];
                this.socialName = this.personName = params[1];
                this.id = params[2];
                accessToken = GoogleAuthUtil.getToken(
                        getApplicationContext(),
                        accountName, "oauth2:"
                                + Scopes.PLUS_LOGIN + " "
                                + Scopes.PROFILE);
                System.out.println("Access token==" + accessToken);
            } catch (UserRecoverableAuthException e) {
                startActivityForResult(e.getIntent(), AUTH_CODE_REQUEST_CODE);
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";

            }
            return accessToken;
        }

        @Override
        protected void onPostExecute(String accessToken) {
            super.onPostExecute(accessToken);
            if (accessToken != null && !accessToken.equals("")) {
                MovieApplication.getPref().setString(AppPreferences.accessToken, accessToken);
                Intent intent = new Intent(getApplicationContext(), GoogleLoginActivity.class);
               startActivity(intent);

            }


        }
    }



}


