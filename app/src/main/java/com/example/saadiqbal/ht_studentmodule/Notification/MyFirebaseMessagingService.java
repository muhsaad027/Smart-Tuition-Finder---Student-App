package com.example.saadiqbal.ht_studentmodule.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.example.saadiqbal.ht_studentmodule.Login;
import com.example.saadiqbal.ht_studentmodule.MainAppScreen;
import com.example.saadiqbal.ht_studentmodule.NewChatActivity;
import com.example.saadiqbal.ht_studentmodule.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

        Log.d(TAG, "From: " + remoteMessage.getData().toString());
        try {
            JSONObject notificationObject = new JSONObject(remoteMessage.getData().toString());

         addNotification(notificationObject.getJSONObject("data"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

       // Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification());

    }

   private void addNotification(JSONObject notificationObject) throws JSONException {
//        builder.setColor(Integer.parseInt(remoteMessage.getNotification().getColor()));
        // Add as notification


       NotificationCompat.Builder builder =
               new NotificationCompat.Builder(this)
                       .setSmallIcon(R.mipmap.ic_launcher)
                       .setContentTitle(""+notificationObject.getString("title"))
                       .setContentText(""+notificationObject.getString("message"));

       switch (notificationObject.getString("type"))
       {
           case "Message":
               Intent notificationIntentss = new Intent(this, NewChatActivity.class);
               notificationIntentss.putExtra("title", notificationObject.getString("title"));
               notificationIntentss.putExtra("type", notificationObject.getString("type"));
               notificationIntentss.putExtra("chatId", notificationObject.getString("chatId"));
               notificationIntentss.putExtra("message", notificationObject.getString("message"));
               notificationIntentss.putExtra("tutname", notificationObject.getString("tutname"));
               notificationIntentss.putExtra("createdat", notificationObject.getString("createdat"));


               PendingIntent contentIntentss = PendingIntent.getActivity(this, 0, notificationIntentss,
                       PendingIntent.FLAG_UPDATE_CURRENT);
               builder.setContentIntent(contentIntentss);
               builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

               NotificationManager managerss = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
               managerss.notify(0, builder.build());
               break;
           default:
               Intent notificationIntent = new Intent(this, MainAppScreen.class);
               notificationIntent.putExtra("title",notificationObject.getString("title"));
               notificationIntent.putExtra("phoneNo",notificationObject.getString("phoneNo"));
               notificationIntent.putExtra("type",notificationObject.getString("type"));
               notificationIntent.putExtra("reqId",notificationObject.getString("reqId"));
               notificationIntent.putExtra("timestamp",notificationObject.getString("timestamp"));


               PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent,
                       PendingIntent.FLAG_UPDATE_CURRENT);
               builder.setContentIntent(contentIntent);
               builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

//        builder.setColor(Integer.parseInt(remoteMessage.getNotification().getColor()));
               // Add as notification
               NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
               manager.notify(1, builder.build());
               break;
       }

    }
}
