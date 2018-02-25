package com.example.saadiqbal.ht_studentmodule;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomDialogClass extends Dialog   {

  public Activity c;
  public Dialog d;
  public Button reqsent;
  JSONObject tutor;

  public CustomDialogClass(Activity a, JSONObject jsonObject) {
    super(a);
    // TODO Auto-generated constructor stub
    this.c = a;
    this.tutor = jsonObject;


  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.tutor_layout);
    reqsent = (Button) findViewById(R.id.sendreq);

    try {
      Toast.makeText(c,""+tutor.getString("TutName"),Toast.LENGTH_LONG).show();

    } catch (JSONException e) {
      e.printStackTrace();
    }

  }


}