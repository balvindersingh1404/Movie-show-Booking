package com.example.balvinder.bookYourShow.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.balvinder.bookYourShow.R;

public class CustomDialogClass extends Dialog implements
    View.OnClickListener {

  public Activity c;
  public Dialog d;
  public Button ok;

  public CustomDialogClass(Activity a) {
    super(a);
    this.c = a;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.custom_dialogue);
    ok = (Button) findViewById(R.id.btn_ok);
    ok.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.btn_ok:
      c.finish();
      break;
    default:
      break;
    }
    dismiss();
  }
}