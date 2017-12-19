package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Thanks extends AppCompatActivity {
    private static int timeq=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Thanks.this,MainAppScreen.class);
                startActivity(intent);
                finish();
            }
        },timeq);
    }
}
