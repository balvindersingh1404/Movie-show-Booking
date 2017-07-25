package com.example.balvinder.bookYourShow.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by balvinder on 16/11/16.
 */

public class AppPreferences {
 static   SharedPreferences mPrefs ;
 static   SharedPreferences.Editor editor;

    public static final String APPPREFERENCES = "MyPrefs" ;

    public static final String AuthKey = "AuthKey";
    public static final String DeviceId ="DeviceId";
    public static final String name ="name";
    public static final String email ="email";
    public static final String picture ="picture";
    public static final String tag ="tag";
    public static final String latitude ="latitude";
    public static final String id ="id";

    public static String getLocation() {
        return location;
    }

    public static final String location ="location";


    public static String getLongitude() {
        return longitude;
    }

    public static String getLatitude() {
        return latitude;
    }

    public static final String longitude ="longitude";

    public static final String accessToken ="accessToken";


    public static String getSessionId() {
        return sessionId;
    }

    public static final String sessionId ="sessionId";


    public static String getId() {
        return id;
    }



    public static SharedPreferences getmPrefs() {
        return mPrefs;
    }

    public static void setmPrefs(SharedPreferences mPrefs) {
        AppPreferences.mPrefs = mPrefs;
    }

    public static String getTag() {
        return tag;
    }

    public static String getPicture() {
        return picture;
    }

    public static String getEmail() {
        return email;
    }

    public static String getName() {
        return name;
    }

    public AppPreferences(Context context){
         mPrefs = context.getSharedPreferences("PVR",context.MODE_PRIVATE);
        editor = mPrefs.edit();
    }
    public  void setString(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    public  String getString(String key) {

        return   mPrefs.getString(key,"");

    }
}
