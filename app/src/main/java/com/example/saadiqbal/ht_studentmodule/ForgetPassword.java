package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPassword extends AppCompatActivity {

    Button confirm;
    EditText p1,p2;
    String pass,repasscheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        p1 = (EditText)findViewById(R.id.passwordforget) ;
        p2 = (EditText)findViewById(R.id.repasswordforget) ;
        confirm = (Button)findViewById(R.id.registerforget);
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validations();
            }
        });
    }
    public void validations() {
        pass = p1.getText().toString();
        repasscheck = p2.getText().toString();
        if (p1.getText().toString().isEmpty()) {
            p1.setError("Phone number is required!");
            requestFocus(p1);
            return;
        } else if (p2.getText().toString().isEmpty()) {
            p2.setError("Password is required!");
            requestFocus(p2);
            return;
        }
        else if(!pass.equals(repasscheck))
        {
            p1.setError("Password Doesn't match");
            p2.setError("Password Doesn't match");
            requestFocus(p1);
            requestFocus(p2);
            return;
        }
        Intent n = new Intent(ForgetPassword.this, Login.class);
        startActivity(n);
        finish();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
