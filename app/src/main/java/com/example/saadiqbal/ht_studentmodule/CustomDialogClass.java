package com.example.saadiqbal.ht_studentmodule;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomDialogClass extends Dialog {

    public Activity c;
    public Dialog d;
    public Button reqsent;
    JSONObject tutor;
    public TextView t1, t2, t3, t4, t5;
    public RatingBar r;


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

        /*
        TutorId
        StudenId
        Longitude
        Latitude
        CourseName
         */
        reqsent = (Button) findViewById(R.id.sendreq);
        t1 = (TextView) findViewById(R.id.tv_tutor_name);
        t2 = (TextView)findViewById(R.id.tv_qualification);
        t3 = (TextView)findViewById(R.id.tv_timing);
        t4= (TextView)findViewById(R.id.tv_salary);
        r = (findViewById(R.id.ratingbar));
        try {
            t2.setText(tutor.getString("TutQual"));
            t1.setText(tutor.getString("TutName"));
            t3.setText(tutor.getString("TeachingDays"));
            t4.setText(tutor.getString(""));
            r.setRating((float) tutor.getDouble("TutRating"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Toast.makeText(c, "" + tutor.getString("TutName"), Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}