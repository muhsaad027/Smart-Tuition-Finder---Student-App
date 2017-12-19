package com.example.saadiqbal.ht_studentmodule;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


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
}
