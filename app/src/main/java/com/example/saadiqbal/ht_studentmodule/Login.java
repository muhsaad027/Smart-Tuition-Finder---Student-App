package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
Button loginpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginpage = (Button)findViewById(R.id.loginb1);
        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainAppScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
