package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saadiqbal.ht_studentmodule.CoursePKG.Authentication;

public class EnterNumberForget extends AppCompatActivity implements View.OnClickListener {
EditText username;
    Button sendbuton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_number_forget);
        username = (EditText)findViewById(R.id.loginusernameforgetpass);
        sendbuton = (Button)findViewById(R.id.sendcodeverify);
        sendbuton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sendcodeverify:
                Intent intent = new Intent(EnterNumberForget.this, AuthenticationForgetYourPassword.class);
                intent.putExtra("nameT", username.getText().toString());
                startActivity(intent);
                finish();
                break;
        }
    }
}
