package com.example.balvinder.bookYourShow.util;

import android.app.Application;
import android.content.Context;

public class MovieApplication extends Application {

    public static Context instance = null;
    public static AppPreferences preferences = null;

    public static Context getInstance() {
        if (null == instance) {
            instance = new MovieApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       instance=this;
    }

    public static AppPreferences getPref(){
        if (null == preferences) {
            preferences = new AppPreferences(getInstance());
        }
        return preferences;
    }
    }

