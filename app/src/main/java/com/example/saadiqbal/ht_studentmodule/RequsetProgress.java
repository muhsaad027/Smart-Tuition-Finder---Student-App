package com.example.saadiqbal.ht_studentmodule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class RequsetProgress extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requset_progress);
        b1 = (Button) findViewById(R.id.reqcencel);
        b2 = (Button)findViewById(R.id.contactto);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getBaseContext(), "Request Cencel",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RequsetProgress.this,MainAppScreen.class);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Can Contact Through Inbox Feature",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RequsetProgress.this,InboxActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
