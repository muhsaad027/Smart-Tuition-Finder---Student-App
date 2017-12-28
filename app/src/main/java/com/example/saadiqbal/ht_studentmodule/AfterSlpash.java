package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfterSlpash extends AppCompatActivity {
Button loginpage,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_slpash);
        loginpage = (Button)findViewById(R.id.logg);
        signup = (Button)findViewById(R.id.singup);
        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterSlpash.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterSlpash.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
