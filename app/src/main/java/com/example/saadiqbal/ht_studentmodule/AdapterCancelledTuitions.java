package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Saad Iqbal on 3/11/2018.
 */

public class AdapterCancelledTuitions extends RecyclerView.Adapter<AdapterCancelledTuitions.MyViewHolder> {
    private ArrayList<StudentCurrentTuitionsModel> arrayList ;
    Context acontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardView;
        public TextView mTextView;
        public TextView stdname,stdid,stddphone,stdstatus,reqtime;

        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view_order_complete);
            stdname= (TextView) v.findViewById(R.id.StdName);
            stdid = (TextView) v.findViewById(R.id.StdReqID);
            stddphone = (TextView) v.findViewById(R.id.StdPhone);
            stdstatus = (TextView) v.findViewById(R.id.StdStatus);
            reqtime= (TextView) v.findViewById(R.id.StdReqTime);

        }


    }

    public AdapterCancelledTuitions(Context context, ArrayList<StudentCurrentTuitionsModel> arrayList) {
        this.arrayList = arrayList;
        acontext = context;
    }
    public AdapterCancelledTuitions.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tuition_complete, parent, false);
        AdapterCancelledTuitions.MyViewHolder vh = new AdapterCancelledTuitions.MyViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(AdapterCancelledTuitions.MyViewHolder holder, int position){

        StudentCurrentTuitionsModel current = arrayList.get(position);
        Log.v("Adcancel","ID : "+ current.getStudentID()+" NAME : "+current.getStudentName()+
                "Phone : "+current.getStudentPhone()+" Status : "+current.getStudentStatus()+" Request : "+current.getStduentRequesTime());
        holder.stdname.setText(current.getStudentName());
        holder.stdid.setText(current.getStudentID());
        holder.stddphone.setText(current.getStudentPhone());
        holder.stdstatus.setText(current.getStudentStatus());
        holder.reqtime.setText(current.getStduentRequesTime());
        //holder.tv_cancelby.setText(""+current.getCanceledBy());
        //holder.tv_jobcreationtime.setText(current.getJobCreationTime());
    }

    public int getItemCount() { return arrayList.size(); }

}
