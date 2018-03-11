package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CurrentTutionStudent extends Fragment {
RecyclerView rv;

    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";
    ArrayList<StudentCurrentTuitionsModel> data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_current_tution_student, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_order_cancel);
        rv.setHasFixedSize(true);
        get_order_cancelled_data();
        return rootView;
    }


    public void get_order_cancelled_data()
    {
        String phone =  loadPreferences();
        AndroidNetworking.get(URLStudents.URL_StudentCourses)
                .addQueryParameter("StdPhone", phone )
                .addQueryParameter("requestStatus", "ACTIVE" )
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        data = new ArrayList<>();

                        try {
                            JSONArray jsonArray = response.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {



                                StudentCurrentTuitionsModel currentTuitionsModel = new StudentCurrentTuitionsModel(jsonArray.getJSONObject(i));


                                data.add(currentTuitionsModel);
                            }

                            AdapterTuitionCancel adapter = new AdapterTuitionCancel(getActivity(),data);
                            rv.setAdapter(adapter);
                            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(llm);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), "" + anError, Toast.LENGTH_LONG).show();

                    }
                });
    }
    private String loadPreferences() {

        String tutphone;
        SharedPreferences settings;
        settings = getActivity().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        tutphone = settings.getString(PREF_UNAME, "");
        return tutphone;
    }

}
