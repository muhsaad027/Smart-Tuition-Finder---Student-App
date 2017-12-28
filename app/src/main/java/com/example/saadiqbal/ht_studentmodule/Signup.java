package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity {
    EditText name,email,password,contactno;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        contactno = findViewById(R.id.contactno);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AndroidNetworking.post(URLStudents.URL_LOGIN)
                        .addBodyParameter("phoneno", contactno.getText().toString())
                        .addBodyParameter("pass", password.getText().toString())
                        .addBodyParameter("name", name.getText().toString())
                        .addBodyParameter("email", email.getText().toString())
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


                                if(!error)
                                {
                                    Toast.makeText(Signup.this,""+message,Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Signup.this,MainAppScreen.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Signup.this,""+message,Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });








            }
        });

    }
}
