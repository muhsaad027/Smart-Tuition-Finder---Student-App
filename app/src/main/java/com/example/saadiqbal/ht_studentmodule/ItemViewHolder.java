package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ItemViewHolder extends RecyclerView.ViewHolder{

    public RelativeLayout regularLayout;
    public LinearLayout swipeLayout;
    public TextView listItem;
    public TextView undo;
    Context acontext;

    public ItemViewHolder(View view, final Context ctx) {
        super(view);

        acontext = ctx;
        regularLayout = (RelativeLayout) view.findViewById(R.id.regularLayout);
        listItem = (TextView) view.findViewById(R.id.list_item);
        swipeLayout = (LinearLayout) view.findViewById(R.id.swipeLayout);
        undo = (TextView) view.findViewById(R.id.undo);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(itemView.getContext(), ""+Integer.toString(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Intent chatActivity = new Intent(ctx,ChatActivity.class);
                acontext.startActivity(chatActivity);
//                acontext.startActivity(new Intent(acontext,TestActivity.class));
            }
        });

    }

}
