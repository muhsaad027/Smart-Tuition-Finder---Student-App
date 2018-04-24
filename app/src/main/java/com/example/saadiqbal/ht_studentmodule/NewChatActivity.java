package com.example.saadiqbal.ht_studentmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewChatActivity extends AppCompatActivity {

    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    private NewChatAdapter adapter;
    private ArrayList<NewChatMessage> chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_chat);
        initControls();
    }

    private void initControls() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (Button) findViewById(R.id.chatSendButton);

        TextView meLabel = (TextView) findViewById(R.id.meLbl);
        TextView companionLabel = (TextView) findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        companionLabel.setText("My Buddy");// Hard Coded
        loadDummyHistory();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                NewChatMessage newChatMessage = new NewChatMessage();
                newChatMessage.setId(122);//dummy
                newChatMessage.setMessage(messageText);
                newChatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                newChatMessage.setMe(true);

                messageET.setText("");

                displayMessage(newChatMessage);
            }
        });
    }

    public void displayMessage(NewChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    private void loadDummyHistory(){

        chatHistory = new ArrayList<NewChatMessage>();

        NewChatMessage msg = new NewChatMessage();
        msg.setId(1);
        msg.setMe(false);
        msg.setMessage("Hi");
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg);
        NewChatMessage msg1 = new NewChatMessage();
        msg1.setId(2);
        msg1.setMe(false);
        msg1.setMessage("How r u doing???");
        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg1);

        adapter = new NewChatAdapter(NewChatActivity.this, new ArrayList<NewChatMessage>());
        messagesContainer.setAdapter(adapter);

        for(int i=0; i<chatHistory.size(); i++) {
            NewChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }
    }
}
