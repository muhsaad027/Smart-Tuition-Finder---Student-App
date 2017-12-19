package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qasim on 10/12/2017.
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {
    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;
    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);}
        public void add(ChatMessage object) {
            // TODO Auto-generated method stub

            MessageList.add(object);
            super.add(object);
        }

    public int getCount()
    {
        return this.MessageList.size();
    }

    public ChatMessage getItem(int index){

        return this.MessageList.get(index);
    }

    public View getView(int position, View ConvertView, ViewGroup parent){

        View v = ConvertView;
        if(v==null){

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.chat, parent,false);

        }

        layout = (LinearLayout)v.findViewById(R.id.Message1);
        ChatMessage messageobj = getItem(position);
        chatText =(TextView)v.findViewById(R.id.SingleMessage);
        chatText.setText(messageobj.message);
        chatText.setBackgroundResource(messageobj.left ? R.drawable.rounded_rectangle_orange :R.drawable.rounded_rectangle_blue);
        layout.setGravity(messageobj.left? Gravity.LEFT:Gravity.RIGHT);
        return v;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}

