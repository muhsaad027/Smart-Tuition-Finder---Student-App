package com.example.saadiqbal.ht_studentmodule;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CustomDialogClass extends Dialog   {

  public Activity c;
  public Dialog d;
  public Button reqsent;

  public CustomDialogClass(Activity a) {
    super(a);
    // TODO Auto-generated constructor stub
    this.c = a;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.tutor_layout);
    reqsent = (Button) findViewById(R.id.sendreq);
  }


}