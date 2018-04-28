package com.example.saadiqbal.ht_studentmodule;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.saadiqbal.ht_studentmodule.Notification.SendRegistrationTokenFCM;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewChatActivity extends AppCompatActivity {

    private EditText messageET;
    private ListView messagesContainer;
    String messageText;
    String messagecoming,Datemesage,idchat,tutname,TutorPhone,phone;
    private Button sendBtn;
    private NewChatAdapter adapter;
    private ArrayList<NewChatMessage> chatHistory;
    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_chat);
        initControls();
        NotificationClose(this,0);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            if ((int) bundle.get("Type") == 1) {
                TutorPhone = (String) bundle.get("phonenumer");
            } else {


                messagecoming = (String) bundle.get("message");
                idchat = (String) bundle.get("chatId");
                TutorPhone = (String) bundle.get("phonenumer");
                // messageId
                // 125
                // Toast.makeText(this,"Student  "+idchat,Toast.LENGTH_SHORT).show();
                //

                NewChatMessage msg = new NewChatMessage();
                //id
                //setmefalse
                //dateTime
                int in = Integer.valueOf(idchat);
                msg.setId(in);
                msg.setMe(false);
                msg.setMessage(messagecoming);
                msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                displayMessage(msg);
            }
            Log.v("Chatactivity", "Message _______ " + messagecoming);

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            messagecoming = (String) bundle.get("message");
            idchat = (String) bundle.get("chatId");
            TutorPhone = (String) bundle.get("phonenumer");
            NotificationClose(this,0);
            // messageId
            // 125
            // Toast.makeText(this,"Student  "+idchat,Toast.LENGTH_SHORT).show();
            //

            NewChatMessage msg = new NewChatMessage();
            //id
            //setmefalse
            //dateTime
            int in = Integer.valueOf(idchat);
            msg.setId(in);
            msg.setMe(false);
            msg.setMessage(messagecoming);
            msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
            displayMessage(msg);
        }
        Log.v("Chatactivity","On new inten Message _______ "+messagecoming);

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
                 messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                NewChatMessage newChatMessage = new NewChatMessage();
                newChatMessage.setId(122);//dummy
                newChatMessage.setMessage(messageText);
                newChatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                newChatMessage.setMe(true);

                messageET.setText("");
                datasend();
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
        //id
        //setmefalse
        //dateTime

      /*  msg.setId(1);
        msg.setMe(false);
        msg.setMessage("Hi");
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg);
        NewChatMessage msg1 = new NewChatMessage();
        msg1.setId(2);
        msg1.setMe(false);
        msg1.setMessage("How r u doing???");
        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg1);*/

        adapter = new NewChatAdapter(NewChatActivity.this, new ArrayList<NewChatMessage>());
        messagesContainer.setAdapter(adapter);

        for(int i=0; i<chatHistory.size(); i++) {
            NewChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }
    }
    public void datasend() {
     phone = loadPreferences();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            TutorPhone = (String) bundle.get("phonenumer");
        }
            String text = TutorPhone;
        AndroidNetworking.post(URLStudents.URL_SendMessage)

                .addBodyParameter("StdPhone", phone)
                .addBodyParameter("TutPhone", text)
                .addBodyParameter("Message", messageText )
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";

                        try {
                            message = response.getString("message");
                            error = response.getBoolean("error");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (!error) {
                            SendRegistrationTokenFCM.sendRegistrationToServer(NewChatActivity.this, FirebaseInstanceId.getInstance().getToken(), phone);
                            Toast.makeText(NewChatActivity.this, "" + message, Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(NewChatActivity.this, "" + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    private String loadPreferences() {

        String tutphone;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        tutphone = settings.getString(PREF_UNAME, "");
        return tutphone;
    }
    public static void NotificationClose(Context ctx,int notifyID)
    {
        String a = Context.NOTIFICATION_SERVICE;
        NotificationManager m = (NotificationManager)ctx.getSystemService(a);
        m.cancel(notifyID);
    }
}
