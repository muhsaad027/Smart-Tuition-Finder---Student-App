package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.saadiqbal.ht_studentmodule.CoursePKG.Authentication;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity {
    EditText name,email,password,contactno,repass;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repass = findViewById(R.id.repassword);
        contactno = findViewById(R.id.contactno);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validations();
            }
        });

    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void validations() {
        if (name.getText().toString().isEmpty())
        {
            name.setError("Name is required!");
            requestFocus(name);
            return;
        }
        if (email.getText().toString().isEmpty())
        {
            email.setError("Email is required!");
            requestFocus(email);
            return;
        }
        if (password.getText().toString().isEmpty())
        {
            password.setError("Password is required!");
            requestFocus(password);
            return;
        }
        if (repass.getText().toString().isEmpty())
        {
            repass.setError("Re Enter the same Password!");
            requestFocus(repass);
            return;
        }
        if (contactno.getText().toString().isEmpty())
        {
            contactno.setError("Contact Information is Required!");
            requestFocus(contactno);
            return;
        }
        Intent intent = new Intent(Signup.this, Authentication.class);
        intent.putExtra("nameT", name.getText().toString());
        intent.putExtra("emailT", email.getText().toString());
        intent.putExtra("contactT", contactno.getText().toString());
        intent.putExtra("passT", password.getText().toString());
        startActivity(intent);
        finish();
    }
}
