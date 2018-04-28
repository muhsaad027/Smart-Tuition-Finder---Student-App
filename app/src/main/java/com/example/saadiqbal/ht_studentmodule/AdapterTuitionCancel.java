package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterTuitionCancel extends RecyclerView.Adapter<AdapterTuitionCancel.MyViewHolder> {

    private ArrayList<StudentCurrentTuitionsModel> arrayList ;
    Context acontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public Button bt_finish;
        public CardView mCardView;
        public TextView mTextView;
        public TextView stdname,stdid,stddphone,stdstatus,reqtime;

        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view_order_cancel);
            bt_finish = v.findViewById(R.id.bt_finish);
            stdname= (TextView) v.findViewById(R.id.StdName);
            stdid = (TextView) v.findViewById(R.id.StdReqID);
            stddphone = (TextView) v.findViewById(R.id.StdPhone);
            stdstatus = (TextView) v.findViewById(R.id.StdStatus);
            reqtime= (TextView) v.findViewById(R.id.StdReqTime);


            bt_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int s = getAdapterPosition();
                    Log.d("IDD","id : "+s);
                    RatingDailogeBox rdb = new RatingDailogeBox(acontext,arrayList.get(s));
                    rdb.show();
                }
            });
        }

    }

    public AdapterTuitionCancel(Context context, ArrayList<StudentCurrentTuitionsModel> arrayList) {
        this.arrayList = arrayList;
        acontext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tuition_finish, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

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

    @Override
    public int getItemCount() { return arrayList.size(); }

}
