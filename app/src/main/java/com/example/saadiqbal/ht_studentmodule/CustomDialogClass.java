package com.example.saadiqbal.ht_studentmodule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomDialogClass extends Dialog implements View.OnClickListener {

    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";
    public Activity c;
    public Dialog d;
    public Button reqsent,cancel;
    JSONObject tutor;
    public TextView t1, t2, t3, t4, t5;
    public RatingBar r;
    double lonitude ,latitude;
    String courses;

    public CustomDialogClass(Activity a, JSONObject jsonObject,double longitude,double latitude,String courses) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.tutor = jsonObject;
        this.lonitude = longitude;
        this.latitude = latitude;
        this.courses = courses;
    }

    public CustomDialogClass(Activity a, JSONObject jsonObject)
    {
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
        cancel = (Button) findViewById(R.id.cencelreqestdai);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        reqsent = (Button) findViewById(R.id.sendreq);
        t1 = (TextView) findViewById(R.id.tv_tutor_name);
        t2 = (TextView)findViewById(R.id.tv_qualification);
        t3 = (TextView)findViewById(R.id.tv_timing);
        t4= (TextView)findViewById(R.id.tv_salary);
        t5 = (TextView)findViewById(R.id.tv_tuitionType);
        r = (findViewById(R.id.ratingbar));
        try {
            t2.setText(tutor.getString("TutQual"));
            t1.setText(tutor.getString("TutName"));
            t3.setText(tutor.getString("TeachingDays"));
            t5.setText("Teaching Place: "+tutor.getString("TeachingType"));
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
        reqsent.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendreq:
                reqsend();
                dismiss();
                break;
        }
    }
    public void reqsend() {
        String phone =  loadPreferences();
        try {
            AndroidNetworking.get(URLStudents.URL_CustomizeRequestTutor)
                .addQueryParameter("studentId", phone)
                .addQueryParameter("longitude", "" + lonitude)
                .addQueryParameter("latitude", "" +latitude)
                .addQueryParameter("courses", courses)
                    .addQueryParameter("tutorId",tutor.getString("TutPhone"))
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";
                        String reqID = "";

                        }


                    @Override
                    public void onError(ANError error) {
                        // handle error

                    }
                });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String loadPreferences() {

        String tutphone;
        SharedPreferences settings;
        settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        tutphone = settings.getString(PREF_UNAME, "");
        return tutphone;
    }
}