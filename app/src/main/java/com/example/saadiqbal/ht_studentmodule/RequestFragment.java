package com.example.saadiqbal.ht_studentmodule;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment implements View.OnClickListener {


    public RequestFragment() {
        // Required empty public constructor
    }

    Button btn_cancel,btn_cont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);
        btn_cancel = rootView.findViewById(R.id.reqcencel);
        btn_cont = rootView.findViewById(R.id.contactto);
        btn_cont.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.reqcencel:
                rejectData();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(0, R.anim.slide_out)
                        .remove(this)
                        .commit();
                break;
            case R.id.contactto:
                Intent as = new Intent(getActivity().getApplication(), InboxActivity.class);
                startActivity(as);
                break;
        }
    }
    public void rejectData() {
       /* Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            phone = (String) bundle.get("phonenumber");
        }*/
        SharedPreferences shared = getActivity().getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
         String channel = (shared.getString(Login.PREF_UNAME, ""));
        Log.v(""+this.getClass().getSimpleName(),"Channel: "+channel);

        Bundle b = getArguments();
        String reqId = b.getString("reqID");

        if (reqId == null || channel == null) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            return;
        }
        AndroidNetworking.get(URLStudents.URL_StrudnetRequestCencel)
                .addQueryParameter("StdPhone", channel)
                .addQueryParameter("reqId", ""+reqId)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        Integer message = 0;

                        try {
                            Log.v(""+this.getClass().getSimpleName(),"Respnse: "+response);

                            message = (response.getInt("success"));
                            Log.v(""+this.getClass().getSimpleName(),"message: "+message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (message == 1) {
                            //Toast.makeText(MainScreen.this, "" + message, Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(MainScreen.this, "" + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        //logDebug("Error   " + error);
                        Log.v(""+this.getClass().getSimpleName(),"error: "+ error.getLocalizedMessage());


                    }
                });

    }
}
