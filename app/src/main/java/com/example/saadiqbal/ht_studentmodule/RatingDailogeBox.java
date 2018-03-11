package com.example.saadiqbal.ht_studentmodule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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

import java.util.ArrayList;

/**
 * Created by Saad Iqbal on 3/11/2018.
 */

public class RatingDailogeBox extends Dialog {

    public Context c;
    public Dialog d;
    public  Button finishTuition;
    public Button reqsent;
    StudentCurrentTuitionsModel tutor;
    public TextView t1, t2, t3, t4, t5;
    public RatingBar r;

    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";
    ArrayList<StudentCurrentTuitionsModel> data;

    public RatingDailogeBox(Context a, StudentCurrentTuitionsModel studentCurrentTuitionsModel) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.tutor = studentCurrentTuitionsModel;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rating_dailoge_box);
        r = (RatingBar) findViewById(R.id.ratingbar);
        finishTuition = (Button)findViewById(R.id.submit_data);
        finishTuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_order_cancelled_data(tutor.getReqID(),r.getRating());
            }
        });
    }
    public void get_order_cancelled_data(int reqId, final float rating)
    {
        String phone =  loadPreferences();
        AndroidNetworking.get(URLStudents.URL_FinishTuition)
                .addQueryParameter("StdPhone", phone )
                .addQueryParameter("reqId", String.valueOf(reqId))
                .addQueryParameter("rating", String.valueOf(rating))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        data = new ArrayList<>();
                        Log.d("AA","Onresponse : ");
                        RatingDailogeBox.this.dismiss();

                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("AA","Error : ");
                    }
                });
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
