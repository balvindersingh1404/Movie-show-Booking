package com.example.balvinder.bookYourShow.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

/**
 * Created by balvinder on 17/11/16.
 */

public class Progress {


    private final ProgressDialog dialog;

    public Progress(View.OnClickListener context) {
            dialog = new ProgressDialog((Context) context);;

        }


    public void showProgressBar() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Please Wait..");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();


        }
    }


